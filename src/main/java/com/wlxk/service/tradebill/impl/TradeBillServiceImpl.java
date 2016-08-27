package com.wlxk.service.tradebill.impl;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;
import com.wlxk.controller.tradebill.vo.*;
import com.wlxk.domain.tradebill.*;
import com.wlxk.repository.tradebill.TradeBillRepository;
import com.wlxk.repository.tradebill.specs.TradeBillSpecs;
import com.wlxk.service.tradebill.*;
import com.wlxk.support.exception.TmsDataValidationException;
import com.wlxk.support.util.CommonProperty;
import com.wlxk.support.util.PageUtil;
import com.wlxk.support.util.ResultsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.List;

/**
 * Created by malin on 2016/7/22.
 */
@Service
public class TradeBillServiceImpl implements TradeBillService {

    private final static Logger logger = LoggerFactory.getLogger(TradeBillServiceImpl.class);

    // 交易单 repository
    @Autowired(required = false)
    private TradeBillRepository repository;
    // 货物 service
    @Autowired
    private GoodsService goodsService;
    // 挂账 service
    @Autowired
    private LossesService lossesService;
    // 交易单操作记录 service
    @Autowired
    private TradeBillOperationRecordService operationRecordService;
    // 审核记录 service
    @Autowired
    private TradeBillReviewService tradeBillReviewService;

    @Override
    public TradeBill save(TradeBill tradeBill) {
        return repository.save(tradeBill);
    }

    @Override
    public void save(List<TradeBill> list) {
        repository.save(list);
    }

    @Override
    public TradeBill getOneById(String tradeBillId) {
        return repository.findOne(tradeBillId);
    }

    @Override
    public TradeBill getOneByTradeBillNo(String tradeBillNO) {
        return repository.findByTradeBillNo(tradeBillNO);
    }

    @Override
    public List<TradeBill> getListByIdList(List<String> idList) {
        return repository.findByIdIn(idList);
    }

    @Override
    public List<TradeBill> getListByContractId(String contractId) {
        return repository.findByContractId(contractId);
    }

    @Transactional
    @Override
    public Map openTradeBill(OpenTradeBillVo vo) {
        try {
            logger.info("1. 数据校验");
            checkOpenTradeBillData(vo);

            logger.info("1. 添加运单");
            TradeBill tradeBill = save(vo.getTradeBill());
            String tradeBillId = tradeBill.getId();
            String tradeBillNo = tradeBill.getTradeBillNo();

            logger.info("2. 添加商品");
            vo.getGoodsList().forEach(goods -> {
                goods.setTradeBillId(tradeBillId);
                goods.setTradeBillNo(tradeBillNo);
            });
            goodsService.save(vo.getGoodsList());

            logger.info("3. 添加运单费用");
            vo.getLossesList().forEach(losses -> {
                losses.setTradeBillId(tradeBillId);
                losses.setTradeBillNo(tradeBillNo);
            });
            lossesService.save(vo.getLossesList());

            logger.info("4. 添加运单审核");
            TradeBillReview tradeBillReview = TradeBillReview.newDefaultInstance(tradeBillId);
            tradeBillReviewService.save(tradeBillReview);

            logger.info("5. 添加运单操作记录");
            operationRecordService.save(TradeBillOperationRecord.newInstance(tradeBill.getId(), vo.getOperationById(), vo.getOperationByName(), vo.getDescription()));

        } catch (TmsDataValidationException e) {
            logger.error("数据校验异常!", e);
            throw e;
        } catch (Exception e) {
            logger.error("开单失败!", e);
            throw e;
        }
        return ResultsUtil.getSuccessResultMap("开单成功!");
    }

    private void checkOpenTradeBillData(OpenTradeBillVo vo) {
        if (Objects.isNull(vo)) {
            throw new TmsDataValidationException("开单请求主体对象不能空!");
        }
        if (Objects.isNull(vo.getTradeBill())) {
            throw new TmsDataValidationException("开单交易单数据不能为空!");
        }
        if (Objects.isNull(vo.getGoodsList())) {
            throw new TmsDataValidationException("开单商品数据不能为空!");
        }
        if (Objects.isNull(vo.getLossesList())) {
            throw new TmsDataValidationException("开单挂账信息不能空!");
        }
        if (Strings.isNullOrEmpty(vo.getOperationById())) {
            throw new TmsDataValidationException("操作人ID不能为空!");
        }
        if (Strings.isNullOrEmpty(vo.getOperationByName())) {
            throw new TmsDataValidationException("操作人名称不能为空!");
        }
        if (Strings.isNullOrEmpty(vo.getDescription())) {
            throw new TmsDataValidationException("操作说明不能为空!");
        }
    }

    @Transactional
    @Override
    public Map reviewTradeBill(ReviewTradeBillVo vo) {
        try {
            logger.info("1. 数据校验");
            checkReviewTradeBill(vo);

            logger.info("2. " + (vo.getCommand() == CommonProperty.ReviewCommand.AUDITED_COMMAND ? "审核" : "取消审核"));
            TradeBillReview tradeBillReview = tradeBillReviewService.getOneTradeBillReview(vo.getBusinessId());
            if (vo.getCommand().equals(CommonProperty.ReviewCommand.CANNEL_COMMAND)) {
                tradeBillReview.setStatus(CommonProperty.ReviewStatus.UNAUDITED);
            } else if (vo.getCommand().equals(CommonProperty.ReviewCommand.AUDITED_COMMAND)) {
                tradeBillReview.setStatus(CommonProperty.ReviewStatus.AUDITED);
            }
            tradeBillReview.setReviewById(vo.getOperationById());
            tradeBillReview.setReviewByName(vo.getOperationByName());
            tradeBillReview.setDescription(vo.getDescription());
            tradeBillReview.setModifyById(vo.getOperationById());
            tradeBillReview.setModifyByName(vo.getOperationByName());
            tradeBillReview.setModifyByDate(Calendar.getInstance().getTime());
            tradeBillReviewService.save(tradeBillReview);

            logger.info("3. 新增操作记录");
            operationRecordService.save(TradeBillOperationRecord.newInstance(vo.getBusinessId(), vo.getOperationById(), vo.getOperationByName(), vo.getDescription()));

        } catch (TmsDataValidationException e) {
            logger.error("数据校验异常!", e);
            throw e;
        } catch (Exception e) {
            logger.error((vo.getCommand() == CommonProperty.ReviewCommand.AUDITED_COMMAND ? "审核" : "取消审核") + "失败!", e);
            throw e;
        }
        return ResultsUtil.getSuccessResultMap((vo.getCommand() == CommonProperty.ReviewCommand.AUDITED_COMMAND ? "审核" : "取消审核") + "成功!");
    }

    private void checkReviewTradeBill(ReviewTradeBillVo vo) {
        if (Objects.isNull(vo)) {
            throw new TmsDataValidationException("交易单审核请求主体对象不能空!");
        }
        if (Strings.isNullOrEmpty(vo.getBusinessId())) {
            throw new TmsDataValidationException("交易单编号不能为空!");
        }
        if (Strings.isNullOrEmpty(vo.getOperationById())) {
            throw new TmsDataValidationException("操作人ID不能为空!");
        }
        if (Strings.isNullOrEmpty(vo.getOperationByName())) {
            throw new TmsDataValidationException("操作人名称不能为空!");
        }
        if (Strings.isNullOrEmpty(vo.getDescription())) {
            throw new TmsDataValidationException("操作说明不能为空!");
        }
        if (Objects.isNull(vo.getCommand())) {
            throw new TmsDataValidationException("命令不能为空!");
        }
        if (!Ints.contains(CommonProperty.ReviewCommand.COMMANDS, vo.getCommand())) {
            throw new TmsDataValidationException("命令无效!");
        }
    }

    @Transactional
    @Override
    public Map updateTradeBill(UpdateTradeBillVo vo) {
        try {
            logger.info("1. 数据校验");
            checkUpdateTradeBill(vo);

            logger.info("2. 更新交易单");
            TradeBill tradeBill = vo.getTradeBill();
            tradeBill.setModifyById(vo.getOperationById());
            tradeBill.setModifyByName(vo.getOperationByName());
            tradeBill.setModifyByDate(Calendar.getInstance().getTime());
            save(tradeBill);

            logger.info("3. 商品操作");
            if (!Objects.isNull(vo.getGoodsList())) {
                logger.info("删除商品");
                goodsService.deleteListByTradeBillId(tradeBill.getId());

                logger.info("新增商品");
                goodsService.save(vo.getGoodsList());
            } else {
                logger.info("无商品操作");
            }

            logger.info("4. 费用操作");
            if (!Objects.isNull(vo.getLossesList())) {
                logger.info("删除费用信息");
                lossesService.deleteListByTradeBillId(tradeBill.getId());

                logger.info("新增费用信息");
                vo.getLossesList().forEach(losses -> {
                    losses.setTradeBillId(tradeBill.getId());
                    losses.setTradeBillNo(tradeBill.getTradeBillNo());
                });
                lossesService.save(vo.getLossesList());
            } else {
                logger.info("无费用操作");
            }

            logger.info("5. 新增操作记录");
            operationRecordService.save(TradeBillOperationRecord.newInstance(tradeBill.getId(), vo.getOperationById(), vo.getOperationByName(), vo.getDescription()));
        } catch (TmsDataValidationException e) {
            logger.error("数据校验异常!", e);
            throw e;
        } catch (Exception e) {
            logger.error("修改失败!", e);
            throw e;
        }
        return ResultsUtil.getSuccessResultMap("修改成功!");
    }

    private void checkUpdateTradeBill(UpdateTradeBillVo vo) {
        if (Objects.isNull(vo)) {
            throw new TmsDataValidationException("请求主体对象不能空!");
        }
        if (Objects.isNull(vo.getTradeBill())) {
            throw new TmsDataValidationException("交易单数据不能为空!");
        }
        if (Strings.isNullOrEmpty(vo.getOperationById())) {
            throw new TmsDataValidationException("修改人ID不能为空!");
        }
        if (Strings.isNullOrEmpty(vo.getOperationByName())) {
            throw new TmsDataValidationException("修改人名称不能为空!");
        }
        if (Strings.isNullOrEmpty(vo.getDescription())) {
            throw new TmsDataValidationException("说明不能为空!");
        }
    }

    @Transactional
    @Override
    public Map disuseTradeBill(DisuseTradeBillVo vo) {
        String tradeBillId = null;
        try {
            logger.info("1. 作废交易单数据校验");
            checkDisuseTradeBill(vo);

            logger.info("2. " + (vo.getCommand() == 1 ? "作废" : "取消作废") + "交易单");
            tradeBillId = vo.getBusinessId();
            TradeBill tradeBill = getOneById(tradeBillId);
            if (vo.getCommand().equals(CommonProperty.DisuseCommand.DISUSE)) {
                tradeBill.setDeleteFlag(CommonProperty.DeleteFlag.DELETE_OFF);
            } else if (vo.getCommand().equals(CommonProperty.DisuseCommand.DISUSE_CANCEL)) {
                tradeBill.setDeleteFlag(CommonProperty.DeleteFlag.DELETE_ON);
            }
            tradeBill.setModifyById(vo.getOperationById());
            tradeBill.setModifyByName(vo.getOperationByName());
            tradeBill.setModifyByDate(Calendar.getInstance().getTime());

            save(tradeBill);

            logger.info("3. " + (vo.getCommand() == 1 ? "作废" : "取消作废") + "商品列表");
            List<Goods> goodsList = goodsService.getListByTradeBillId(tradeBillId);
            goodsList.forEach(goods -> {
                if (vo.getCommand().equals(CommonProperty.DisuseCommand.DISUSE)) {
                    goods.setDeleteFlag(CommonProperty.DeleteFlag.DELETE_OFF);
                } else if (vo.getCommand().equals(CommonProperty.DisuseCommand.DISUSE_CANCEL)) {
                    goods.setDeleteFlag(CommonProperty.DeleteFlag.DELETE_ON);
                }
                goods.setModifyById(vo.getOperationById());
                goods.setModifyByName(vo.getOperationByName());
                goods.setModifyByDate(Calendar.getInstance().getTime());
            });
            goodsService.save(goodsList);

            logger.info("4. " + (vo.getCommand() == 1 ? "作废" : "取消作废") + "运单的费用信息");
            List<Losses> lossesList = lossesService.getListByTradeBillId(tradeBillId);
            lossesList.forEach(losses -> {
                if (vo.getCommand().equals(CommonProperty.DisuseCommand.DISUSE)) {
                    losses.setDeleteFlag(CommonProperty.DeleteFlag.DELETE_OFF);
                } else if (vo.getCommand().equals(CommonProperty.DisuseCommand.DISUSE_CANCEL)) {
                    losses.setDeleteFlag(CommonProperty.DeleteFlag.DELETE_ON);
                }
                losses.setModifyById(vo.getOperationById());
                losses.setModifyByName(vo.getOperationByName());
                losses.setModifyByDate(Calendar.getInstance().getTime());
            });
            lossesService.save(lossesList);

            /*
            logger.info("5. "+(vo.getCommand() == 1 ? "作废" : "取消作废")+"运单的审核信息");
            List<TradeBillReview> tradeBillReviewList = tradeBillReviewService.getListByTradeBillId(tradeBillId);
            tradeBillReviewList.forEach(tradeBillReview -> {
                tradeBillReview.setDeleteFlag(CommonProperty.DeleteFlag.DELETE_OFF);
            });
            tradeBillReviewService.save(tradeBillReviewList);*/

            logger.info("5. 新增操作信息");
            operationRecordService.save(TradeBillOperationRecord.newInstance(vo.getBusinessId(), vo.getOperationById(), vo.getOperationByName(), vo.getDescription()));

        } catch (TmsDataValidationException e) {
            logger.error("数据校验异常!", e);
            throw e;
        } catch (Exception e) {
            logger.error((vo.getCommand() == 1 ? "作废" : "取消作废") + "失败!", e);
            throw e;
        }
        return ResultsUtil.getSuccessResultMap((vo.getCommand() == 1 ? "作废" : "取消作废") + "成功!");
    }

    private void checkDisuseTradeBill(DisuseTradeBillVo vo) {
        if (Objects.isNull(vo)) {
            throw new TmsDataValidationException("请求主体对象不能为空!");
        }
        if (Strings.isNullOrEmpty(vo.getBusinessId())) {
            throw new TmsDataValidationException("业务ID不能为空!");
        }
        if (Strings.isNullOrEmpty(vo.getOperationById())) {
            throw new TmsDataValidationException("操作人ID不能为空!");
        }
        if (Strings.isNullOrEmpty(vo.getOperationByName())) {
            throw new TmsDataValidationException("操作人名称不能为空!");
        }
        if (Strings.isNullOrEmpty(vo.getDescription())) {
            throw new TmsDataValidationException("说明不能为空!");
        }
        if (Objects.isNull(vo.getCommand())) {
            throw new TmsDataValidationException("命令不能为空!");
        }
        if (!Ints.contains(CommonProperty.DisuseCommand.COMMANDS, vo.getCommand())) {
            throw new TmsDataValidationException("命令无效!");
        }
    }

    @Override
    public Page<TradeBill> getTradeBillPage(QueryTradeBillVo vo) {
        PageRequest request = new PageRequest(vo.getPage(), vo.getSize(), new Sort(vo.getDirection(), vo.getSortList()));
        return repository.findAll(TradeBillSpecs.tradeBillPageSpecs(vo.getParams()), request);
    }

    @Override
    public Map getTradeBillViewPage(QueryTradeBillVo vo) {
        Page<TradeBill> tradeBillPage = getTradeBillPage(vo);

        List<TradeBillView> content = Lists.newArrayList();
        tradeBillPage.getContent().forEach(tradeBill -> {
            String tradeBillId = tradeBill.getId();
            List<Goods> goodsList = goodsService.getListByTradeBillId(tradeBillId);
            List<Losses> lossesList = lossesService.getListByTradeBillId(tradeBillId);
            List<TradeBillReview> tradeBillViewList = tradeBillReviewService.getListByTradeBillId(tradeBillId);
            List<TradeBillOperationRecord> tradeBillOperationRecordList = operationRecordService.getListByTradeBillId(tradeBillId);
            content.add(TradeBillView.newInstance(tradeBill, goodsList, lossesList, tradeBillViewList, tradeBillOperationRecordList));
        });
        return ResultsUtil.getSuccessResultMap(PageUtil.newInstancePage(tradeBillPage, content));
    }

    @Override
    public Map byTradeBillNo(String tradeBillNo) {
        String tradeBillId = "";
        try {
            TradeBill tradeBill = getOneByTradeBillNo(tradeBillNo);
            tradeBillId = tradeBill.getId();
            List<Goods> goodsList = goodsService.getListByTradeBillId(tradeBillId);
            List<Losses> lossesList = lossesService.getListByTradeBillId(tradeBillId);
            List<TradeBillReview> tradeBillViewList = tradeBillReviewService.getListByTradeBillId(tradeBillId);
            List<TradeBillOperationRecord> tradeBillOperationRecordList = operationRecordService.getListByTradeBillId(tradeBillId);

            return ResultsUtil.getSuccessResultMap(TradeBillView.newInstance(tradeBill, goodsList, lossesList, tradeBillViewList, tradeBillOperationRecordList));
        } catch (TmsDataValidationException e) {
            logger.error("数据校验异常!", e);
            throw e;
        } catch (Exception e) {
            logger.error("查询失败!", e);
            throw e;
        }
    }

    @Override
    public Map byTradeBillId(String tradeBillId) {
        try {
            TradeBill tradeBill = getOneById(tradeBillId);
            List<Goods> goodsList = goodsService.getListByTradeBillId(tradeBillId);
            List<Losses> lossesList = lossesService.getListByTradeBillId(tradeBillId);
            List<TradeBillReview> tradeBillViewList = tradeBillReviewService.getListByTradeBillId(tradeBillId);
            List<TradeBillOperationRecord> tradeBillOperationRecordList = operationRecordService.getListByTradeBillId(tradeBillId);

            return ResultsUtil.getSuccessResultMap(TradeBillView.newInstance(tradeBill, goodsList, lossesList, tradeBillViewList, tradeBillOperationRecordList));
        } catch (TmsDataValidationException e) {
            logger.error("数据校验异常!", e);
            throw e;
        } catch (Exception e) {
            logger.error("查询失败!", e);
            throw e;
        }
    }
}
