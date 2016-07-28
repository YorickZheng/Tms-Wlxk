package com.wlxk.service.tradebill.impl;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.wlxk.controller.tradebill.vo.*;
import com.wlxk.domain.sys.UserOperationRecord;
import com.wlxk.domain.tradebill.*;
import com.wlxk.repository.tradebill.TradeBillRepository;
import com.wlxk.service.tradebill.*;
import com.wlxk.support.exception.TmsDataValidationException;
import com.wlxk.support.exception.TmsException;
import com.wlxk.support.util.CommonProperty;
import com.wlxk.support.util.CurrentUserUtils;
import com.wlxk.support.util.PageUtil;
import com.wlxk.support.util.ResultsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by malin on 2016/7/22.
 */
@Service
public class TradeBillServiceImpl implements TradeBillService {

    private final static Logger logger = LoggerFactory.getLogger(TradeBillServiceImpl.class);

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
    // 交易单 repository
    @Autowired(required = false)
    private TradeBillRepository tradeBillRepository;

    @Override
    public TradeBill save(TradeBill tradeBill) {
        return tradeBillRepository.save(tradeBill);
    }

    @Override
    public TradeBill getOneById(String tradeBillId) {
        return tradeBillRepository.findOne(tradeBillId);
    }

    @Transactional
    @Override
    public Map openTradeBill(OpenTradeBillVo vo) {
        try {
            logger.info("1. 开始数据校验");
            checkOpenTradeBillData(vo);

            logger.info("1. 开始添加运单");
            TradeBill tradeBill = save(vo.getTradeBill());
            String tradeBillId = tradeBill.getId();
            String tradeBillNo = tradeBill.getTradeBillNo();

            logger.info("2. 开始添加商品");
            vo.getGoodsList().forEach(goods -> {
                goods.setTradeBillId(tradeBillId);
                goods.setTradeBillNo(tradeBillNo);
            });
            goodsService.save(vo.getGoodsList());

            logger.info("3. 开始添加运单费用");
            vo.getLossesList().forEach(losses -> {
                losses.setTradeBillId(tradeBillId);
                losses.setTradeBillNo(tradeBillNo);
            });
            lossesService.save(vo.getLossesList());

            logger.info("4. 开始添加运单审核");
            TradeBillReview tradeBillReview = TradeBillReview.newDefaultInstance(tradeBillId);
            tradeBillReviewService.save(tradeBillReview);

            logger.info("5. 开始添加运单操作记录");
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
    }

    @Transactional
    @Override
    public Map reviewTradeBill(ReviewTradeBillVo vo, Integer command) {
        try {
            logger.info("1. 开始数据校验");
            checkReviewTradeBill(vo);

            logger.info("2. 开始更新交易单审核记录");
            TradeBillReview tradeBillReview = tradeBillReviewService.getOneTradeBillReview(vo.getBusinessId());
            if (command.equals(CommonProperty.TradeBillReviewCommand.PENDING_COMMAND)) {
                tradeBillReview.setStatus(CommonProperty.ReviewStatus.PENDING);
            } else if (command.equals(CommonProperty.TradeBillReviewCommand.AUDITED_COMMAND)) {
                tradeBillReview.setStatus(CommonProperty.ReviewStatus.AUDITED);
            }
            tradeBillReview.setReviewByPerson(vo.getOperationByName());
            tradeBillReview.setDescription(vo.getDescription());
            tradeBillReview.setModifyById(vo.getOperationById());
            tradeBillReview.setModifyByName(vo.getOperationByName());
            tradeBillReview.setModifyByDate(Calendar.getInstance().getTime());
            tradeBillReviewService.save(tradeBillReview);

            logger.info("3. 开始更新交易单审核记录");
            operationRecordService.save(TradeBillOperationRecord.newInstance(vo.getBusinessId(), vo.getOperationById(), vo.getOperationByName(), vo.getDescription()));

        } catch (TmsDataValidationException e) {
            logger.error("数据校验异常!", e);
            throw e;
        } catch (Exception e) {
            logger.error("审核失败!", e);
            throw e;
        }
        return ResultsUtil.getSuccessResultMap("审核成功!");
    }

    private void checkReviewTradeBill(ReviewTradeBillVo vo) {
        if (Objects.isNull(vo)) {
            throw new TmsDataValidationException("交易单审核请求主体对象不能空!");
        }
        if (Strings.isNullOrEmpty(vo.getBusinessId())) {
            throw new TmsDataValidationException("交易单编号不能为空!");
        }
        if (Strings.isNullOrEmpty(vo.getOperationByName())) {
            throw new TmsDataValidationException("交易单审核人不能为空!");
        }
        if (Strings.isNullOrEmpty(vo.getOperationById())) {
            throw new TmsDataValidationException("修改人编号不能为空!");
        }
    }

    @Transactional
    @Override
    public Map updateTradeBill(UpdateTradeBillVo vo) {
        try {
            logger.info("1. 开始数据校验");
            checkUpdateTradeBill(vo);

            logger.info("2. 开始更新交易单");
            String tradeBillId = vo.getTradeBill().getId();
            String tradeBillNo = vo.getTradeBill().getTradeBillNo();
            save(vo.getTradeBill());

            logger.info("3. 开始删除商品");
            goodsService.deleteListByTradeBillId(tradeBillId);

            logger.info("4. 开始新增商品");
            goodsService.save(vo.getGoodsList());

            logger.info("5. 开始删除费用信息");
            lossesService.deleteListByTradeBillId(tradeBillId);

            logger.info("6. 开始新增费用信息");
            vo.getLossesList().forEach(losses -> {
                losses.setTradeBillId(tradeBillId);
                losses.setTradeBillNo(tradeBillNo);
            });
            lossesService.save(vo.getLossesList());

            logger.info("7. 开始新增操作记录");
            operationRecordService.save(TradeBillOperationRecord.newInstance(vo.getBusinessId(), vo.getOperationById(), vo.getOperationByName(), vo.getDescription()));

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
            logger.info("1. 开始作废交易单数据校验");
            checkDisuseTradeBill(vo);

            logger.info("2. 开始作废交易单");
            tradeBillId = vo.getBusinessId();
            TradeBill tradeBill = getOneById(tradeBillId);
            tradeBill.setDeleteFlag(CommonProperty.DeleteFlag.DELETE_OFF);
            save(tradeBill);

            logger.info("3. 开始作商品列表");
            List<Goods> goodsList = goodsService.getListByTradeBillId(tradeBillId);
            goodsList.forEach(goods -> {
                goods.setDeleteFlag(CommonProperty.DeleteFlag.DELETE_OFF);
            });
            goodsService.save(goodsList);

            logger.info("4. 开始作废运单的费用信息");
            List<Losses> lossesList = lossesService.getListByTradeBillId(tradeBillId);
            lossesList.forEach(losses -> {
                losses.setDeleteFlag(CommonProperty.DeleteFlag.DELETE_OFF);
            });
            lossesService.save(lossesList);

            logger.info("5. 开始作废运单的审核信息");
            List<TradeBillReview> tradeBillReviewList = tradeBillReviewService.getListByTradeBillId(tradeBillId);
            tradeBillReviewList.forEach(tradeBillReview -> {
                tradeBillReview.setDeleteFlag(CommonProperty.DeleteFlag.DELETE_OFF);
            });
            tradeBillReviewService.save(tradeBillReviewList);

            logger.info("6. 开始添加作废运单操作信息");
            operationRecordService.save(TradeBillOperationRecord.newInstance(vo.getBusinessId(), vo.getOperationById(), vo.getOperationByName(), vo.getDescription()));

        } catch (TmsDataValidationException e) {
            logger.error("数据校验异常!", e);
            throw e;
        } catch (Exception e) {
            logger.error("作废失败!", e);
            throw e;
        }
        return ResultsUtil.getSuccessResultMap("作废成功!");
    }

    private void checkDisuseTradeBill(DisuseTradeBillVo vo) {
        if (Objects.isNull(vo)) {
            throw new TmsDataValidationException("请求主体对象不能为空!");
        }
        if (Strings.isNullOrEmpty(vo.getBusinessId())) {
            throw new TmsDataValidationException("交易单编号不能为空!");
        }
        if (Strings.isNullOrEmpty(vo.getOperationById())) {
            throw new TmsDataValidationException("操作人编号不能为空!");
        }
        if (Strings.isNullOrEmpty(vo.getOperationByName())) {
            throw new TmsDataValidationException("操作人名称不能为空!");
        }
        if (Strings.isNullOrEmpty(vo.getDescription())) {
            throw new TmsDataValidationException("说明不能为空!");
        }
    }

    @Override
    public Page<TradeBill> getTradeBillPage(TradeBill tradeBill, Integer page, Integer size) {
        return tradeBillRepository.findByAuto(tradeBill, new PageRequest(page, size));
    }

    @Override
    public Map getTradeBillViewPage(Page<TradeBill> tradeBillPage) {
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

}
