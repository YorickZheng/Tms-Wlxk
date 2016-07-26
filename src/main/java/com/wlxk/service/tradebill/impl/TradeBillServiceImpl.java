package com.wlxk.service.tradebill.impl;

import com.google.common.collect.Lists;
import com.wlxk.controller.tradebill.vo.*;
import com.wlxk.domain.tradebill.*;
import com.wlxk.repository.tradebill.TradeBillRepository;
import com.wlxk.service.tradebill.*;
import com.wlxk.support.exception.TmsBusinessException;
import com.wlxk.support.exception.TmsException;
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
    private TradeBillOperationRecordService tradeBillOperationRecordService;
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
            Iterable<Goods> goodsList = goodsService.save(vo.getGoodsList());

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
            TradeBillOperationRecord operationRecord = TradeBillOperationRecord.newDefaultInstance(tradeBillId, "开单成功", Boolean.TRUE);
            tradeBillOperationRecordService.save(operationRecord);

        } catch (TmsException e) {
            throw e;
        }
        return ResultsUtil.getSuccessResultMap("开单成功!");
    }

    private void checkOpenTradeBillData(OpenTradeBillVo vo) {
        if (vo == null) {
            throw new TmsBusinessException("开单请求主体对象不能空!");
        }
        if (vo.getTradeBill() == null) {
            throw new TmsBusinessException("开单交易单数据不能为空!");
        }
        if (vo.getGoodsList() == null) {
            throw new TmsBusinessException("开单商品数据不能为空!");
        }
        if (vo.getLossesList() == null) {
            throw new TmsBusinessException("开单挂账信息不能空!");
        }
    }

    @Transactional
    @Override
    public Map reviewTradeBill(ReviewTradeBillVo vo, Integer command) {
        try {
            logger.info("1. 开始数据校验");
            checkReviewTradeBill(vo);

            logger.info("2. 开始更新交易单审核记录");
            TradeBillReview tradeBillReview = tradeBillReviewService.getOneTradeBillReview(vo.getTradeBillId());
            if (command.equals(CommonProperty.TradeBillReviewCommand.PENDING_COMMAND)) {
                tradeBillReview.setStatus(CommonProperty.ReviewStatus.PENDING);
            } else if (command.equals(CommonProperty.TradeBillReviewCommand.AUDITED_COMMAND)) {
                tradeBillReview.setStatus(CommonProperty.ReviewStatus.AUDITED);
            }
            tradeBillReview.setReviewByPerson(vo.getReviewPerson());
            tradeBillReview.setDescription(vo.getDescription());
            tradeBillReview.setModifyById(vo.getModifyById());
            tradeBillReview.setModifyByDate(Calendar.getInstance().getTime());
            tradeBillReviewService.save(tradeBillReview);

            logger.info("3. 开始更新交易单审核记录");
            TradeBillOperationRecord operationRecord = TradeBillOperationRecord.newDefaultInstance(vo.getTradeBillId(), "交易单审核成功", Boolean.TRUE);
            tradeBillOperationRecordService.save(operationRecord);

        } catch (TmsException e) {
            throw e;
        }
        return ResultsUtil.getSuccessResultMap("审核成功!");
    }

    private void checkReviewTradeBill(ReviewTradeBillVo vo) {
        if (vo == null) {
            throw new TmsBusinessException("交易单审核请求主体对象不能空!");
        }
        if (vo.getTradeBillId() == null) {
            throw new TmsBusinessException("交易单编号不能为空!");
        }
        if (vo.getReviewPerson() == null) {
            throw new TmsBusinessException("交易单审核人不能为空!");
        }
        if (vo.getModifyById() == null) {
            throw new TmsBusinessException("修改人编号不能为空!");
        }
        if (vo.getCommand() == null) {
            throw new TmsBusinessException("操作命令不能为空!");
        }
    }

    @Transactional
    @Override
    public Map updateTradeBill(UpdateTradeBillVo vo) {
        try {
            logger.info("1. 开始数据校验");
            checkUpdateTradeBill(vo);

            logger.info("2. 开始更新交易单");
            String tradeBillId = vo.getTradeBillId();
            String tradeBillNo = vo.getTradeBillNo();
            TradeBill tradeBill = getOneById(tradeBillId);
            tradeBill = updateTradeBillVoToTradeBill(vo, tradeBill);
            save(tradeBill);

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
            TradeBillOperationRecord operationRecord = TradeBillOperationRecord.newDefaultInstance(vo.getTradeBillId(), "交易单修改成功!", Boolean.TRUE);
            tradeBillOperationRecordService.save(operationRecord);

        } catch (TmsException e) {
            throw e;
        }
        return ResultsUtil.getSuccessResultMap("修改成功!");
    }

    private void checkUpdateTradeBill(UpdateTradeBillVo vo) {
        if (vo == null) {
            throw new TmsBusinessException("开单请求主体对象不能空!");
        }
        if (vo.getTradeBill() == null) {
            throw new TmsBusinessException("开单交易单数据不能为空!");
        }
        if (vo.getGoodsList() == null) {
            throw new TmsBusinessException("开单商品数据不能为空!");
        }
        if (vo.getLossesList() == null) {
            throw new TmsBusinessException("开单挂账信息不能空!");
        }
    }

    private TradeBill updateTradeBillVoToTradeBill(UpdateTradeBillVo vo, TradeBill target) {

        // 发货人信息
        target.setSellerTelephone(vo.getTradeBill().getSellerTelephone());
        target.setSellerClient(vo.getTradeBill().getSellerClient());
        target.setSellerPerson(vo.getTradeBill().getSellerPerson());
        target.setSellerPhone(vo.getTradeBill().getSellerPhone());
        target.setSellerAddress(vo.getTradeBill().getSellerAddress());

        // 发货人信息
        target.setBuyerTelephone(vo.getTradeBill().getBuyerTelephone());
        target.setBuyerClient(vo.getTradeBill().getBuyerClient());
        target.setBuyerPerson(vo.getTradeBill().getBuyerPerson());
        target.setBuyerPhone(vo.getTradeBill().getBuyerPhone());
        target.setBuyerAddress(vo.getTradeBill().getBuyerAddress());

        // 运单信息
        target.setTradeBillNo(vo.getTradeBill().getTradeBillNo());
        target.setTransportDate(vo.getTradeBill().getTransportDate());
        target.setStartStation(vo.getTradeBill().getStartStation());
        target.setEndStation(vo.getTradeBill().getEndStation());
        target.setSettleType(vo.getTradeBill().getSettleType());
        target.setDescription(vo.getTradeBill().getDescription());
        target.setStatus(vo.getTradeBill().getStatus());

        // 网点信息
        target.setBranchId(vo.getTradeBill().getBranchId());
        target.setBranchName(vo.getTradeBill().getBranchName());

        // 更新人信息
        target.setModifyById(vo.getModifyById());
        target.setModifyByDate(Calendar.getInstance().getTime());
        return target;
    }

    @Transactional
    @Override
    public Map disuseTradeBill(DisuseTradeBillVo vo) {
        String tradeBillId = null;
        try {
            logger.info("1. 开始作废交易单数据校验");
            checkDisuseTradeBill(vo);

            logger.info("2. 开始作废交易单");
            tradeBillId = vo.getTradeBillId();
            TradeBill tradeBill = getOneById(tradeBillId);
            tradeBill.setDeleteFlag(CommonProperty.DeleteFlag.DELETE_OFF);
            save(tradeBill);

            logger.info("3. 开始作商品列表");
            List<Goods> goodsList = goodsService.getListByTradeBillId(tradeBillId);
            goodsList.forEach(goods -> {
                goods.setDeleteFlag(CommonProperty.DeleteFlag.DELETE_OFF);
            });
            goodsService.save(goodsList);

            logger.info("4. 开始作废交易单和商品关联关系");
            //List<TradeBillGoods> tradeBillGoodsList = tradeBillGoodsService.getTradeBillGoodsList(tradeBillId);
            //tradeBillGoodsList.forEach(tradeBillGoods -> {
                //tradeBillGoods.setDeleteFlag(CommonProperty.DeleteFlag.DELETE_OFF);
           // });
            //tradeBillGoodsService.saveTradeBillGoodsList(tradeBillGoodsList);

            logger.info("5. 开始作废运单的费用信息");
            List<Losses> lossesList = lossesService.getListByTradeBillId(tradeBillId);
            lossesList.forEach(losses -> {
                losses.setDeleteFlag(CommonProperty.DeleteFlag.DELETE_OFF);
            });
            lossesService.save(lossesList);

            logger.info("6. 开始作废运单的审核信息");
            List<TradeBillReview> tradeBillReviewList = tradeBillReviewService.getListByTradeBillId(tradeBillId);
            tradeBillReviewList.forEach(tradeBillReview -> {
                tradeBillReview.setDeleteFlag(CommonProperty.DeleteFlag.DELETE_OFF);
            });
            tradeBillReviewService.save(tradeBillReviewList);

            logger.info("7. 开始添加作废运单操作信息");
            TradeBillOperationRecord operationRecord = TradeBillOperationRecord.newDefaultInstance(vo.getTradeBillId(), "交易单作废成功", Boolean.TRUE);
            tradeBillOperationRecordService.save(operationRecord);

        } catch (TmsException e) {
            throw e;
        }
        return ResultsUtil.getSuccessResultMap("作废成功!");
    }

    private void checkDisuseTradeBill(DisuseTradeBillVo vo) {
        if (vo == null) {
            throw new TmsBusinessException("作废交易单请求主体对象不能空!");
        }
        if (vo.getTradeBillId() == null) {
            throw new TmsBusinessException("交易单编号不能为空!");
        }
        if (vo.getDisuseById() == null) {
            throw new TmsBusinessException("作废人编号不能为空!");
        }
        if (vo.getDescription() == null) {
            throw new TmsBusinessException("交易单作废说明不能为空!");
        }
    }

    @Override
    public Page<TradeBill> getTradeBillPage(TradeBill tradeBill, Integer page, Integer size) {
        return tradeBillRepository.findByAuto(tradeBill, new PageRequest(page, size));
    }

    @Override
    public Page<TradeBillView> getTradeBillViewPage(Page<TradeBill> tradeBillPage) {
        List<TradeBillView> content = Lists.newArrayList();
        tradeBillPage.getContent().forEach(tradeBill -> {
            String tradeBillId = tradeBill.getId();
            List<Goods> goodsList = goodsService.getListByTradeBillId(tradeBillId);
            List<Losses> lossesList = lossesService.getListByTradeBillId(tradeBillId);
            List<TradeBillReview> tradeBillViewList = tradeBillReviewService.getListByTradeBillId(tradeBillId);
            List<TradeBillOperationRecord> tradeBillOperationRecordList = tradeBillOperationRecordService.getListByTradeBillid(tradeBillId);
            content.add(TradeBillView.newInstance(tradeBill, goodsList, lossesList, tradeBillViewList, tradeBillOperationRecordList));
        });
        return PageUtil.newInstancePage(tradeBillPage, content);
    }

}
