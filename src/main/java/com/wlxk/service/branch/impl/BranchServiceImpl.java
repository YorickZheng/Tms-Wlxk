package com.wlxk.service.branch.impl;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;
import com.wlxk.controller.branch.vo.*;
import com.wlxk.controller.sys.vo.role.QueryRoleVo;
import com.wlxk.domain.branch.*;
import com.wlxk.repository.branch.BranchRepository;
import com.wlxk.repository.branch.specs.BranchSpecs;
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
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Calendar;
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
            List<BranchBankCard> bankCardList = vo.getBranchBankCardList();
            bankCardList.forEach(branchBankCard -> {
                branchBankCard.setBranchId(branchId);
            });
            bankCardService.save(bankCardList);

            logger.info("4. 开始新增网点联系人数据");
            List<BranchLinkman> linkmanList = vo.getBranchLinkmanList();
            linkmanList.forEach(branchLinkman -> {
                branchLinkman.setBranchId(branchId);
            });
            linkmanService.save(linkmanList);

            logger.info("5. 开始新增网点审核信息");
            reviewService.save(BranchReview.newDefaultInstance(branchId));

            logger.info("6. 开始新增网点操作记录");
            operationRecordService.save(BranchOperationRecord.newInstance(branchId, vo.getOperationById(), vo.getOperationByName(), vo.getDescription()));
        } catch (TmsDataValidationException e) {
            logger.error("数据校验异常!", e);
            throw e;
        } catch (Exception e) {
            logger.error("新增失败!", e);
            throw e;
        }
        return ResultsUtil.getSuccessResultMap("新增网点成功!");
    }

    private void checkAddBranch(AddBranchVo vo) {
        if (Objects.isNull(vo)) {
            throw new TmsDataValidationException("请求主体对象不能为空!");
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

    @Override
    public Map update(UpdateBranchVo vo) {
        try {
            logger.info("1. 数据校验");
            checkUpdateBranch(vo);

            logger.info("2. 保存网点修改");
            String branchId = vo.getBranch().getId();
            Branch branch = vo.getBranch();
            branch.setModifyById(vo.getOperationById());
            branch.setModifyByName(vo.getOperationByName());
            branch.setModifyByDate(Calendar.getInstance().getTime());
            save(branch);

            logger.info("3. 银行卡更新");
            if (!Objects.isNull(vo.getBranchBankCardList())) {
                logger.info("3-1. 删除银行卡集合数据");
                bankCardService.deleteListByBranchId(branchId);

                logger.info("3-2. 新增银行卡集合数据");
                bankCardService.save(vo.getBranchBankCardList());
            } else {
                logger.info("无银行卡操作");
            }

            logger.info("4. 联系人操作");
            if(!Objects.isNull(vo.getBranchLinkmanList())) {
                logger.info("4-1. 删除联系人集合数据");
                linkmanService.deleteListByBranchId(branchId);

                logger.info("4-2. 新增联系人集合数据");
                linkmanService.save(vo.getBranchLinkmanList());
            } else {
                logger.info("无联系人操作");
            }


            logger.info("5. 添加修改网点操作记录");
            operationRecordService.save(BranchOperationRecord.newInstance(vo.getBranch().getId(), vo.getOperationById(), vo.getOperationByName(), vo.getDescription()));
        } catch (TmsDataValidationException e) {
            logger.error("数据校验异常!", e);
            throw e;
        } catch (Exception e) {
            logger.error("更新失败!", e);
            throw e;
        }
        return ResultsUtil.getSuccessResultMap("更新成功!");
    }

    private void checkUpdateBranch(UpdateBranchVo vo) {
        if (Objects.isNull(vo)) {
            throw new TmsDataValidationException("网点修改的请求对象不能为空!");
        }
        if (Objects.isNull(vo.getBranch())) {
            throw new TmsDataValidationException("网点对象不能为空!");
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

    @Override
    public Map disuse(DisuseBranchVo vo) {
        try {
            logger.info("1. 数据校验");
            checkDisuseBranch(vo);

            logger.info("3. 获取视图参数");
            String branchId = vo.getBusinessId();

            logger.info("2. " + (vo.getCommand() == 1 ? "作废" : "取消作废") + "网点");
            Branch branch = getOneById(branchId);
            if (vo.getCommand().equals(CommonProperty.DisuseCommand.DISUSE)) {
                branch.setDeleteFlag(Boolean.TRUE);
            } else if (vo.getCommand().equals(CommonProperty.DisuseCommand.DISUSE_CANCEL)) {
                branch.setDeleteFlag(Boolean.FALSE);
            }
            branch.setModifyById(vo.getOperationById());
            branch.setModifyByName(vo.getOperationByName());
            branch.setModifyByDate(Calendar.getInstance().getTime());
            save(branch);

            logger.info("3. " + (vo.getCommand() == 1 ? "作废" : "取消作废") + "网点银行卡数据");
            List<BranchBankCard> branchBankCardList = bankCardService.getListByBranchId(branchId);
            branchBankCardList.forEach(branchBankCard -> {
                if (vo.getCommand().equals(CommonProperty.DisuseCommand.DISUSE)) {
                    branchBankCard.setDeleteFlag(Boolean.TRUE);
                } else if (vo.getCommand().equals(CommonProperty.DisuseCommand.DISUSE_CANCEL)) {
                    branchBankCard.setDeleteFlag(Boolean.FALSE);
                }
                branchBankCard.setModifyById(vo.getOperationById());
                branchBankCard.setModifyByName(vo.getOperationByName());
                branchBankCard.setModifyByDate(Calendar.getInstance().getTime());
            });
            bankCardService.save(branchBankCardList);

            logger.info("4. " + (vo.getCommand() == 1 ? "作废" : "取消作废") + "网点联系人数据");
            List<BranchLinkman> branchLinkmanList = linkmanService.getListByBranchId(branchId);
            branchLinkmanList.forEach(branchLinkman -> {
                if (vo.getCommand().equals(CommonProperty.DisuseCommand.DISUSE)) {
                    branchLinkman.setDeleteFlag(Boolean.TRUE);
                } else if (vo.getCommand().equals(CommonProperty.DisuseCommand.DISUSE_CANCEL)) {
                    branchLinkman.setDeleteFlag(Boolean.FALSE);
                }
                branchLinkman.setModifyById(vo.getOperationById());
                branchLinkman.setModifyByName(vo.getOperationByName());
                branchLinkman.setModifyByDate(Calendar.getInstance().getTime());
            });
            linkmanService.save(branchLinkmanList);

            /*
            logger.info("5. 作废网带你审核数据");
            List<BranchReview> branchReviewList = reviewService.getListByBranchId(branchId);
            branchReviewList.forEach(branchReview -> {
                branchReview.setDeleteFlag(Boolean.TRUE);
            });
            reviewService.save(branchReviewList);*/

            logger.info("5. 新增操作记录");
            operationRecordService.save(BranchOperationRecord.newInstance(branchId, CurrentUserUtils.getCurrentUser().getId(), CurrentUserUtils.getCurrentUser().getName(), vo.getDescription()));
        } catch (TmsDataValidationException e) {
            logger.error("数据校验异常!", e);
            throw e;
        } catch (Exception e) {
            logger.error((vo.getCommand() == 1 ? "作废" : "取消作废") + "失败!", e);
            throw e;
        }
        return ResultsUtil.getSuccessResultMap((vo.getCommand() == 1 ? "作废" : "取消作废") + "成功!");
    }

    private void checkDisuseBranch(DisuseBranchVo vo) {
        if (Objects.isNull(vo)) {
            throw new TmsDataValidationException("审核请求主体对象不能为空!");
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
            throw new TmsDataValidationException("操作说明不能为空!");
        }
        if (Objects.isNull(vo.getCommand())) {
            throw new TmsDataValidationException("命令不能为空!");
        }
        if (!Ints.contains(CommonProperty.DisuseCommand.COMMANDS, vo.getCommand())) {
            throw new TmsDataValidationException("作废命令无效!");
        }
    }

    @Override
    public Map review(ReviewBranchVo vo) {
        try {
            logger.info("1. 数据审核");
            checkReview(vo);

            logger.info("2. " + (vo.getCommand() == CommonProperty.ReviewCommand.AUDITED_COMMAND ? "审核" : "取消审核"));
            String branchId = vo.getBusinessId();
            BranchReview review = reviewService.getOneByBranchId(branchId);
            if (vo.getCommand().equals(CommonProperty.ReviewCommand.CANNEL_COMMAND)) {
                review.setStatus(CommonProperty.ReviewStatus.UNAUDITED);
            } else if (vo.getCommand().equals(CommonProperty.ReviewCommand.AUDITED_COMMAND)) {
                review.setStatus(CommonProperty.ReviewStatus.AUDITED);
            }
            review.setReviewById(vo.getOperationById());
            review.setReviewByName(vo.getOperationByName());
            review.setDescription(vo.getDescription());
            reviewService.save(review);

            logger.info("3. 新增操作记录");
            operationRecordService.save(BranchOperationRecord.newInstance(branchId, vo.getOperationById(), vo.getOperationByName(), vo.getDescription()));
        } catch (TmsDataValidationException e) {
            logger.error("数据校验异常!", e);
            throw e;
        } catch (Exception e) {
            logger.error((vo.getCommand() == CommonProperty.ReviewCommand.AUDITED_COMMAND ? "审核" : "取消审核") + "失败!", e);
            throw e;
        }
        return ResultsUtil.getSuccessResultMap((vo.getCommand() == CommonProperty.ReviewCommand.AUDITED_COMMAND ? "审核" : "取消审核") + "成功!");
    }

    private void checkReview(ReviewBranchVo vo) {
        if (Objects.isNull(vo)) {
            throw new TmsDataValidationException("审核请求主体对象不能为空!");
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
            throw new TmsDataValidationException("操作说明不能为空!");
        }
        if (Objects.isNull(vo.getCommand())) {
            throw new TmsDataValidationException("命令不能为空!");
        }
        if (!Ints.contains(CommonProperty.ReviewCommand.COMMANDS, vo.getCommand())) {
            throw new TmsDataValidationException("作废命令无效!");
        }
    }

    @Override
    public Page<Branch> pageData(QueryBranchVo vo) {
        PageRequest request = new PageRequest(vo.getPage(), vo.getSize(), new Sort(vo.getDirection(), vo.getSortList()));
        return repository.findAll(BranchSpecs.branchPageSpecs(vo.getParams()), request);
    }

    @Override
    public Map pageDataView(QueryBranchVo vo) {
        try {
            logger.info("1. 数据校验");
            getPageViewByDataValidation(vo);

            logger.info("2 查询主表");
            Page<Branch> branchPage = pageData(vo);

            logger.info("3. 查询子表");
            List<BranchView> content = Lists.newArrayList();
            branchPage.getContent().forEach(branch -> {
                String branchId = branch.getId();
                List<BranchBankCard> bankCardList = bankCardService.getListByBranchId(branchId);
                List<BranchLinkman> linkmenList = linkmanService.getListByBranchId(branchId);
                List<BranchOperationRecord> operationRecordList = operationRecordService.getListByBranchId(branchId);
                List<BranchReview> reviewList = reviewService.getListByBranchId(branchId);

                content.add(BranchView.newInstance(branch, bankCardList, linkmenList, operationRecordList, reviewList));
            });

            logger.info("4. 组装数据返回");
            return ResultsUtil.getSuccessResultMap(PageUtil.newInstancePage(branchPage, content));
        } catch (TmsDataValidationException e) {
            logger.error("数据校验异常!", e);
            throw e;
        } catch (Exception e) {
            logger.error("查询失败!", e);
            throw e;
        }
    }

    private void getPageViewByDataValidation(QueryBranchVo vo) {
        if (Objects.isNull(vo)) {
            throw new TmsDataValidationException("请求主体对象不能为空!");
        }
        if (Objects.isNull(vo.getParams())) {
            throw new TmsDataValidationException("查询参数列表不能为空!");
        }
        if (Objects.isNull(vo.getPage())) {
            throw new TmsDataValidationException("起始页不能为空!");
        }
        if (Objects.isNull(vo.getSize())) {
            throw new TmsDataValidationException("每页数量不能为空!");
        }
        if (Objects.isNull(vo.getDirection())) {
            throw new TmsDataValidationException("排序方式不能为空!");
        }
        if (Objects.isNull(vo.getSortList())) {
            throw new TmsDataValidationException("排序集合不能为空!");
        }
    }

}
