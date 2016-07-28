package com.wlxk.service.branch.impl;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.wlxk.controller.branch.vo.*;
import com.wlxk.domain.branch.*;
import com.wlxk.repository.branch.BranchRepository;
import com.wlxk.service.branch.*;
import com.wlxk.support.exception.TmsDataValidationException;
import com.wlxk.support.exception.TmsException;
import com.wlxk.support.util.CommonProperty;
import com.wlxk.support.util.CurrentUserUtils;
import com.wlxk.support.util.PageUtil;
import com.wlxk.support.util.ResultsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by malin on 2016/7/26.
 */
@Service
public class BranchServiceImpl implements BranchService {

    private final static Logger logger = LoggerFactory.getLogger(BranchServiceImpl.class);

    @Autowired(required = false)
    private BranchRepository repository;
    @Autowired
    private BranchBankCardService bankCardService;
    @Autowired
    private BranchLinkmanService linkmanService;
    @Autowired
    private BranchReviewService reviewService;
    @Autowired
    private BranchOperationRecordService operationRecordService;

    @Override
    public Branch save(Branch branch) {
        return repository.save(branch);
    }

    @Override
    public Branch getOneById(String id) {
        return repository.findOne(id);
    }

    @Override
    public Map add(AddBranchVo vo) {
        try {
            logger.info("1. 开始数据校验");
            checkAddBranch(vo);

            logger.info("2. 开始新增网点");
            Branch branch = save(vo.getBranch());
            String branchId = branch.getId();

            logger.info("3. 开始新增网点银行卡数据");
            bankCardService.save(vo.getBranchBankCardList());

            logger.info("4. 开始新增网点联系人数据");
            linkmanService.save(vo.getBranchLinkmanList());

            logger.info("5. 开始新增网点审核信息");
            reviewService.save(BranchReview.newDefaultInstance(branchId));

            logger.info("6. 开始新增网点操作记录");
            operationRecordService.save(BranchOperationRecord.newInstance(branchId, CurrentUserUtils.getCurrentUser().getId(), CurrentUserUtils.getCurrentUser().getName(), "新增成功!"));
        } catch (TmsException e) {
            throw e;
        }
        return ResultsUtil.getSuccessResultMap("新增网点成功!");
    }

    private void checkAddBranch(AddBranchVo vo) {
        if (Objects.isNull(vo)) {
            throw new TmsDataValidationException("新增网点请求主体对象不能为空!");
        }
        if (Objects.isNull(vo.getBranch())) {
            throw new TmsDataValidationException("网点对象不能为空!");
        }
        if (vo.getBranchBankCardList() == null) {
            throw new TmsDataValidationException("银行卡集合对象不能为空!");
        }
        if (vo.getBranchLinkmanList() == null) {
            throw new TmsDataValidationException("联系人集合对象不能为空!");
        }
    }

    @Override
    public Map update(UpdateBranchVo vo) {
        try {
            logger.info("1. 数据校验");
            checkUpdateBranch(vo);

            logger.info("2. 保存网点修改");
            String branchId = vo.getBranch().getId();
            save(vo.getBranch());

            logger.info("3. 删除银行卡集合数据");
            bankCardService.deleteListByBranchId(branchId);

            logger.info("4. 新增银行卡集合数据");
            bankCardService.save(vo.getBranchBankCardList());

            logger.info("5. 删除联系人集合数据");
            linkmanService.deleteListByBranchId(branchId);

            logger.info("6. 新增联系人集合数据");
            linkmanService.save(vo.getBranchLinkmanList());

            logger.info("7. 添加修改网点操作记录");
            operationRecordService.save(BranchOperationRecord.newInstance(vo.getBranch().getId(), CurrentUserUtils.getCurrentUser().getId(), CurrentUserUtils.getCurrentUser().getName(), "修改成功!"));
        } catch (TmsException e) {
            throw e;
        }
        return ResultsUtil.getSuccessResultMap("网点修改成功!");
    }

    private void checkUpdateBranch(UpdateBranchVo vo) {
        if (vo == null) {
            throw new TmsDataValidationException("网点修改的请求对象不能为空!");
        }
        if (vo.getBranch() == null) {
            throw new TmsDataValidationException("网点对象不能为空!");
        }
        if (vo.getDescription() == null) {
            throw new TmsDataValidationException("网点修改说明不能为空!");
        }
    }

    @Override
    public Map disuse(DisuseBranchVo vo) {
        try {
            logger.info("1. 数据校验");
            checkDisuseBranch(vo);

            logger.info("3. 获取视图参数");
            String branchId = vo.getBranchId();
            String disuseById = vo.getDisuseById();
            String disuseByName = vo.getDisuseByName();
            String description = vo.getDescription();

            logger.info("2. 作废网点");
            Branch branch = getOneById(branchId);
            branch.setModifyById(disuseById);
            branch.setDeleteFlag(Boolean.TRUE);
            save(branch);

            logger.info("3. 作废网点银行卡数据");
            List<BranchBankCard> branchBankCardList = bankCardService.getListByBranchId(branchId);
            branchBankCardList.forEach(branchBankCard -> {
                branchBankCard.setDeleteFlag(Boolean.TRUE);
            });
            bankCardService.save(branchBankCardList);

            logger.info("4. 作废网点联系人数据");
            List<BranchLinkman> branchLinkmanList = linkmanService.getListByBranchId(branchId);
            branchLinkmanList.forEach(branchLinkman -> {
                branchLinkman.setDeleteFlag(Boolean.TRUE);
            });
            linkmanService.save(branchLinkmanList);

            logger.info("5. 作废网带你审核数据");
            List<BranchReview> branchReviewList = reviewService.getListByBranchId(branchId);
            branchReviewList.forEach(branchReview -> {
                branchReview.setDeleteFlag(Boolean.TRUE);
            });
            reviewService.save(branchReviewList);

            logger.info("6. 添加网点作废操作数据");
            operationRecordService.save(BranchOperationRecord.newInstance(branchId, CurrentUserUtils.getCurrentUser().getId(), CurrentUserUtils.getCurrentUser().getName(), description));
        } catch (TmsException e) {
            throw e;
        }
        return ResultsUtil.getSuccessResultMap("网点作废成功!");
    }

    private void checkDisuseBranch(DisuseBranchVo vo) {
        if (vo == null) {
            throw new TmsDataValidationException("作废网点请求主体对象不能为空!");
        }
        if (Strings.isNullOrEmpty(vo.getBranchId())) {
            throw new TmsDataValidationException("网点编号不能为空!");
        }
        if (Strings.isNullOrEmpty(vo.getDisuseById())) {
            throw new TmsDataValidationException("作废人编号不能为空!");
        }
        if (Strings.isNullOrEmpty(vo.getDisuseByName())) {
            throw new TmsDataValidationException("作废人名称不能为空!");
        }
        if (Strings.isNullOrEmpty(vo.getDescription())) {
            throw new TmsDataValidationException("作废说明不能为空!");
        }
    }

    @Override
    public Map review(ReviewBranchVo vo) {
        try {
            logger.info("1. 数据审核");
            checkReview(vo);

            logger.info("2. 添加审核记录");
            String branchId = vo.getBranchId();
            BranchReview review = reviewService.getOneByBranchId(branchId);
            review.setReviewById(vo.getPersonalById());
            review.setReviewByPerson(vo.getPersonal());
            review.setDescription(vo.getDescription());
            review.setStatus(CommonProperty.ReviewStatus.AUDITED);
            reviewService.save(review);

            logger.info("3. 添加操作记录");
            operationRecordService.save(BranchOperationRecord.newInstance(branchId, CurrentUserUtils.getCurrentUser().getId(), CurrentUserUtils.getCurrentUser().getName(), "审核成功!"));
        } catch (TmsException e) {
            throw e;
        }
        return ResultsUtil.getSuccessResultMap("审核成功!");
    }

    private void checkReview(ReviewBranchVo vo) {
        if (Objects.isNull(vo)) {
            throw new TmsDataValidationException("审核请求主体对象不能为空!");
        }
        if (Strings.isNullOrEmpty(vo.getBranchId())) {
            throw new TmsDataValidationException("网点编号不能为空!");
        }
        if (Strings.isNullOrEmpty(vo.getPersonalById())) {
            throw new TmsDataValidationException("审核人编号不能为空!");
        }
        if (Strings.isNullOrEmpty(vo.getPersonal())) {
            throw new TmsDataValidationException("审核人不能为空!");
        }
        if (Strings.isNullOrEmpty(vo.getDescription())) {
            throw new TmsDataValidationException("审核说明不能为空!");
        }
    }

    @Override
    public Page<Branch> getBranchPage(Branch branch, Integer page, Integer size) {
        return repository.findByAuto(branch, new PageRequest(page, size));
    }

    @Override
    public Page<BranchView> getBranchViewPage(Page<Branch> branchPage) {
        List<BranchView> content = Lists.newArrayList();
        branchPage.getContent().forEach(branch -> {
            String branchId = branch.getId();
            List<BranchBankCard> bankCardList = bankCardService.getListByBranchId(branchId);
            List<BranchLinkman> linkmenList = linkmanService.getListByBranchId(branchId);
            List<BranchOperationRecord> operationRecordList = operationRecordService.getListByBranchId(branchId);
            List<BranchReview> reviewList = reviewService.getListByBranchId(branchId);
            content.add(BranchView.newInstance(branch, bankCardList, linkmenList, operationRecordList, reviewList));
        });
        return PageUtil.newInstancePage(branchPage, content);
    }
}
