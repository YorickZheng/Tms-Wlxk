package com.wlxk.service.contract.impl;

import com.google.common.base.Strings;
import com.wlxk.controller.contract.vo.AddContractVo;
import com.wlxk.controller.contract.vo.DisuseContractVo;
import com.wlxk.controller.contract.vo.ReviewContractVo;
import com.wlxk.controller.contract.vo.UpdateContractVo;
import com.wlxk.domain.contract.Contract;
import com.wlxk.domain.contract.ContractLine;
import com.wlxk.domain.contract.ContractOperationRecord;
import com.wlxk.domain.contract.ContractReview;
import com.wlxk.repository.contract.ContractRepository;
import com.wlxk.service.contract.ContractLineService;
import com.wlxk.service.contract.ContractOperationRecordService;
import com.wlxk.service.contract.ContractReviewService;
import com.wlxk.service.contract.ContractService;
import com.wlxk.support.exception.TmsBusinessException;
import com.wlxk.support.exception.TmsException;
import com.wlxk.support.util.CommonProperty;
import com.wlxk.support.util.ResultsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by malin on 2016/7/26.
 */
@Service
public class ContractServiceImpl implements ContractService {

    private final static Logger logger = LoggerFactory.getLogger(ContractServiceImpl.class);

    @Autowired(required = false)
    private ContractRepository repository;
    @Autowired(required = false)
    private ContractLineService lineService;
    @Autowired(required = false)
    private ContractReviewService reviewService;
    @Autowired(required = false)
    private ContractOperationRecordService operationRecordService;

    @Override
    public Contract getOneById(String id) {
        return repository.findOne(id);
    }

    @Override
    public Contract save(Contract contract) {
        return repository.save(contract);
    }

    @Override
    public Map add(AddContractVo vo) {
        try {
            logger.info("1. 数据校验");
            checkAdd(vo);

            logger.info("2. 新增合同");
            Contract contract = save(vo.getContract());

            logger.info("3. 新增路线");
            vo.getLineList().forEach(contractLine -> {
                contractLine.setContractId(contract.getId());
            });
            lineService.save(vo.getLineList());

            logger.info("4. 新增审核");
            reviewService.save(ContractReview.newDefaultInstance(contract.getId()));

            logger.info("5. 新增操作记录");
            operationRecordService.save(ContractOperationRecord.newDefaultInstance(contract.getId(), "新增成功!", Boolean.TRUE));

        } catch (TmsException e) {
            throw e;
        }
        return ResultsUtil.getSuccessResultMap("新增成功");
    }

    private void checkAdd(AddContractVo vo) {
        if (Objects.isNull(vo)) {
            throw new TmsBusinessException("请求主体对象不能为空!");
        }
        if (Objects.isNull(vo.getContract())) {
            throw new TmsBusinessException("合同对象不能为空!");
        }
        if (Objects.isNull(vo.getLineList())) {
            throw new TmsBusinessException("线路集合不能为空!");
        }
    }

    @Override
    public Map review(ReviewContractVo vo) {
        try {
            logger.info("1. 数据校验");
            checkReview(vo);

            logger.info("2. 审核数据");
            ContractReview review = reviewService.getOneByContractId(vo.getContractId());
            review.setReviewById(vo.getReviewById());
            review.setReviewByPerson(vo.getReviewByName());
            review.setDescription(vo.getDescription());
            review.setStatus(CommonProperty.ReviewStatus.AUDITED);
            reviewService.save(review);

            logger.info("3. 新增操作记录");
            operationRecordService.save(ContractOperationRecord.newDefaultInstance(vo.getContractId(), "审核成功!", Boolean.TRUE));

        } catch (TmsException e) {
            throw e;
        }
        return ResultsUtil.getSuccessResultMap("审核成功!");
    }

    private void checkReview(ReviewContractVo vo) {
        if (Objects.isNull(vo)) {
            throw new TmsBusinessException("请求主体对象不能为空!");
        }
        if (Strings.isNullOrEmpty(vo.getContractId())) {
            throw new TmsBusinessException("合同编号不能为空!");
        }
        if (Strings.isNullOrEmpty(vo.getReviewById())) {
            throw new TmsBusinessException("审核人编号不能为空!");
        }
        if (Strings.isNullOrEmpty(vo.getReviewByName())) {
            throw new TmsBusinessException("审核人名称不能为空!");
        }
        if (Strings.isNullOrEmpty(vo.getDescription())) {
            throw new TmsBusinessException("说明不能为空!");
        }
    }

    @Override
    public Map disuse(DisuseContractVo vo) {
        try {
            logger.info("1. 数据校验");
            checkDisuse(vo);

            logger.info("2. 作废合同");
            Contract contract = getOneById(vo.getContractId());
            contract.setDeleteFlag(Boolean.TRUE);
            contract.setModifyById(vo.getDisuseById());
            contract.setModifyByDate(Calendar.getInstance().getTime());
            save(contract);

            logger.info("3. 作废路线");
            List<ContractLine> lineList = lineService.getListByContractId(vo.getContractId());
            lineList.forEach(contractLine -> {
                contractLine.setDeleteFlag(Boolean.TRUE);
                contractLine.setModifyById(vo.getDisuseById());
                contractLine.setModifyByDate(Calendar.getInstance().getTime());
            });
            lineService.save(lineList);

            logger.info("4. 作废审核记录");
            List<ContractReview> reviewList = reviewService.getListByContractId(vo.getContractId());
            reviewList.forEach(contractReview -> {
                contractReview.setDeleteFlag(Boolean.TRUE);
                contractReview.setModifyById(vo.getDisuseById());
                contractReview.setModifyByDate(Calendar.getInstance().getTime());
            });
            reviewService.save(reviewList);

            logger.info("5. 新增操作记录");
            operationRecordService.save(ContractOperationRecord.newDefaultInstance(vo.getContractId(), "作废成功!", Boolean.TRUE));
        } catch (TmsException e) {
            throw e;
        }
        return ResultsUtil.getSuccessResultMap("作废成功!");
    }

    private void checkDisuse(DisuseContractVo vo) {
        if (Objects.isNull(vo)) {
            throw new TmsBusinessException("请求主体对象不能为空!");
        }
        if (Strings.isNullOrEmpty(vo.getContractId())) {
            throw new TmsBusinessException("合同编号不能为空!");
        }
        if (Strings.isNullOrEmpty(vo.getDisuseById())) {
            throw new TmsBusinessException("作废人编号不能为空!");
        }
        if (Strings.isNullOrEmpty(vo.getDisuseByName())) {
            throw new TmsBusinessException("作废人名称不能为空!");
        }
        if (Strings.isNullOrEmpty(vo.getDescription())) {
            throw new TmsBusinessException("说明不能为空!");
        }
    }

    @Override
    public Map update(UpdateContractVo vo) {
        try {
            logger.info("1. 数据校验");
            checkUpdate(vo);

            logger.info("2. 更新合同");
            save(vo.getContract());

            logger.info("3. 删除线路");
            lineService.deleteListByContractId(vo.getContract().getId());

            logger.info("4. 新增线路");
            lineService.save(vo.getLineList());

            logger.info("5. 新增操作记录");
            operationRecordService.save(ContractOperationRecord.newDefaultInstance(vo.getContract().getId(), "更新成功!", Boolean.TRUE));
        }catch (TmsException e) {
            throw e;
        }
        return ResultsUtil.getSuccessResultMap("更新成功!");
    }

    private void checkUpdate(UpdateContractVo vo) {
        if (Objects.isNull(vo)) {
            throw new TmsBusinessException("请求主体对象不能为空!");
        }
        if (Objects.isNull(vo.getContract())) {
            throw new TmsBusinessException("合同对象不能为空!");
        }
        if (Objects.isNull(vo.getLineList())) {
            throw new TmsBusinessException("路线集合不能为空!");
        }
    }
}

