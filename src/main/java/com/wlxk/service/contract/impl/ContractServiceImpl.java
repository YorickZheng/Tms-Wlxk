package com.wlxk.service.contract.impl;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;
import com.wlxk.controller.contract.vo.*;
import com.wlxk.domain.contract.Contract;
import com.wlxk.domain.contract.ContractLine;
import com.wlxk.domain.contract.ContractOperationRecord;
import com.wlxk.domain.contract.ContractReview;
import com.wlxk.domain.tradebill.TradeBill;
import com.wlxk.domain.tradebill.TradeBillOperationRecord;
import com.wlxk.repository.contract.ContractRepository;
import com.wlxk.repository.contract.specs.ContractSpecs;
import com.wlxk.service.contract.ContractLineService;
import com.wlxk.service.contract.ContractOperationRecordService;
import com.wlxk.service.contract.ContractReviewService;
import com.wlxk.service.contract.ContractService;
import com.wlxk.service.tradebill.TradeBillOperationRecordService;
import com.wlxk.service.tradebill.TradeBillService;
import com.wlxk.support.exception.TmsDataValidationException;
import com.wlxk.support.util.CommonProperty;
import com.wlxk.support.util.PageUtil;
import com.wlxk.support.util.ResultsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

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
    private ContractOperationRecordService contractOperationRecordService;
    @Autowired(required = false)
    private TradeBillService tradeBillService;
    @Autowired(required = false)
    private TradeBillOperationRecordService tradeBillOperationRecordService;

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
            contractOperationRecordService.save(ContractOperationRecord.newInstance(contract.getId(), vo.getOperationById(), vo.getOperationByName(), vo.getDescription()));

        } catch (TmsDataValidationException e) {
            logger.error("数据校验异常!", e);
            throw e;
        } catch (Exception e) {
            logger.error("新增失败!", e);
            throw e;
        }
        return ResultsUtil.getSuccessResultMap("新增成功");
    }

    private void checkAdd(AddContractVo vo) {
        if (Objects.isNull(vo)) {
            throw new TmsDataValidationException("请求主体对象不能为空!");
        }
        if (Objects.isNull(vo.getContract())) {
            throw new TmsDataValidationException("合同对象不能为空!");
        }
        if (Objects.isNull(vo.getLineList())) {
            throw new TmsDataValidationException("线路集合不能为空!");
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
    public Map loading(LoadingVo vo) {
        try {
            logger.info("1. 数据校验");
            checkLoading(vo);

            logger.info("2. 更新装车合同");
            Contract contract = getOneById(vo.getContractId());
            contract.setModifyById(vo.getOperationById());
            contract.setModifyByName(vo.getOperationByName());
            contract.setModifyByDate(Calendar.getInstance().getTime());
            save(contract);

            logger.info("3. 更新交易单");
            List<TradeBill> tradeBillList = tradeBillService.getListByIdList(vo.getTradeBillIdList());
            tradeBillList.forEach(tradeBill -> {
                tradeBill.setContractId(contract.getId());
                tradeBill.setContractNo(contract.getContractNo());
                tradeBill.setModifyById(vo.getOperationById());
                tradeBill.setModifyByName(vo.getOperationByName());
                tradeBill.setModifyByDate(Calendar.getInstance().getTime());
                tradeBillOperationRecordService.save(TradeBillOperationRecord.newInstance(tradeBill.getId(), vo.getOperationById(), vo.getOperationByName(), vo.getDescription()));
            });
            tradeBillService.save(tradeBillList);


            logger.info("4. 新增操作记录");
            contractOperationRecordService.save(ContractOperationRecord.newInstance(contract.getId(), vo.getOperationById(), vo.getOperationByName(), vo.getDescription()));
        } catch (TmsDataValidationException e) {
            logger.error("数据校验异常!", e);
            throw e;
        } catch (Exception e) {
            logger.error("装车失败!", e);
            throw e;
        }
        return ResultsUtil.getSuccessResultMap("装车成功");
    }

    private void checkLoading(LoadingVo vo) {
        if (Objects.isNull(vo)) {
            throw new TmsDataValidationException("请求主体对象不能为空!");
        }
        if (Strings.isNullOrEmpty(vo.getContractId())) {
            throw new TmsDataValidationException("合同ID不能为空!");
        }
        if (Objects.isNull(vo.getTradeBillIdList())) {
            throw new TmsDataValidationException("交易单ID集合不能为空!");
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
    public Map review(ReviewContractVo vo) {
        try {
            logger.info("1. 数据校验");
            checkReview(vo);

            logger.info("2. " + (vo.getCommand() == CommonProperty.ReviewCommand.AUDITED_COMMAND ? "审核" : "取消审核"));
            ContractReview review = reviewService.getOneByContractId(vo.getBusinessId());
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
            contractOperationRecordService.save(ContractOperationRecord.newInstance(vo.getBusinessId(), vo.getOperationById(), vo.getOperationByName(), vo.getDescription()));

        } catch (TmsDataValidationException e) {
            logger.error("数据校验异常!", e);
            throw e;
        } catch (Exception e) {
            logger.error((vo.getCommand() == CommonProperty.ReviewCommand.AUDITED_COMMAND ? "审核" : "取消审核") + "失败!", e);
            throw e;
        }
        return ResultsUtil.getSuccessResultMap((vo.getCommand() == CommonProperty.ReviewCommand.AUDITED_COMMAND ? "审核" : "取消审核") + "成功!");
    }

    private void checkReview(ReviewContractVo vo) {
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
    public Map disuse(DisuseContractVo vo) {
        try {
            logger.info("1. 数据校验");
            checkDisuse(vo);

            logger.info("2. " + (vo.getCommand() == 1 ? "作废" : "取消作废") + "合同 ");
            Contract contract = getOneById(vo.getBusinessId());
            if (vo.getCommand().equals(CommonProperty.DisuseCommand.DISUSE)) {
                contract.setDeleteFlag(Boolean.TRUE);
            } else if (vo.getCommand().equals(CommonProperty.DisuseCommand.DISUSE_CANCEL)) {
                contract.setDeleteFlag(Boolean.FALSE);
            }
            contract.setModifyById(vo.getOperationById());
            contract.setModifyByName(vo.getOperationByName());
            contract.setModifyByDate(Calendar.getInstance().getTime());
            save(contract);

            logger.info("3. " + (vo.getCommand() == 1 ? "作废" : "取消作废") + "路线");
            List<ContractLine> lineList = lineService.getListByContractId(vo.getBusinessId());
            lineList.forEach(contractLine -> {
                if (vo.getCommand().equals(CommonProperty.DisuseCommand.DISUSE)) {
                    contractLine.setDeleteFlag(Boolean.TRUE);
                } else if (vo.getCommand().equals(CommonProperty.DisuseCommand.DISUSE_CANCEL)) {
                    contractLine.setDeleteFlag(Boolean.FALSE);
                }
                contractLine.setModifyById(vo.getOperationById());
                contractLine.setModifyByName(vo.getOperationByName());
                contractLine.setModifyByDate(Calendar.getInstance().getTime());
            });
            lineService.save(lineList);

            /*
            logger.info("4. 作废审核记录");
            List<ContractReview> reviewList = reviewService.getListByContractId(vo.getContractId());
            reviewList.forEach(contractReview -> {
                contractReview.setDeleteFlag(Boolean.TRUE);
                contractReview.setModifyById(vo.getDisuseById());
                contractReview.setModifyByDate(Calendar.getInstance().getTime());
            });
            reviewService.save(reviewList);*/

            logger.info("4. 新增操作记录");
            contractOperationRecordService.save(ContractOperationRecord.newInstance(vo.getBusinessId(), vo.getOperationById(), vo.getOperationByName(), vo.getDescription()));
        } catch (TmsDataValidationException e) {
            logger.error("数据校验异常!", e);
            throw e;
        } catch (Exception e) {
            logger.error((vo.getCommand() == 1 ? "作废" : "取消作废") + "失败!", e);
            throw e;
        }
        return ResultsUtil.getSuccessResultMap((vo.getCommand() == 1 ? "作废" : "取消作废") + "成功!");
    }

    private void checkDisuse(DisuseContractVo vo) {
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
            throw new TmsDataValidationException("作废人名称不能为空!");
        }
        if (Strings.isNullOrEmpty(vo.getDescription())) {
            throw new TmsDataValidationException("说明不能为空!");
        }
        if (Objects.isNull(vo.getCommand())) {
            throw new TmsDataValidationException("命令不能为空!");
        }
        if (!Ints.contains(CommonProperty.DisuseCommand.COMMANDS, vo.getCommand())) {
            throw new TmsDataValidationException("作废命令无效!");
        }
    }

    @Override
    public Map update(UpdateContractVo vo) {
        try {
            logger.info("1. 数据校验");
            checkUpdate(vo);

            logger.info("2. 更新合同");
            Contract contract = vo.getContract();
            contract.setModifyById(vo.getOperationById());
            contract.setModifyByName(vo.getOperationByName());
            contract.setModifyByDate(Calendar.getInstance().getTime());
            save(contract);

            logger.info("3. 线路操作");
            if (!Objects.isNull(vo.getLineList())) {
                logger.info("3-1. 删除线路");
                lineService.deleteListByContractId(contract.getId());

                logger.info("3-2. 新增线路");
                List<ContractLine> lineList = vo.getLineList();
                lineList.forEach(contractLine -> {contractLine.setContractId(contract.getId());});
                lineService.save(lineList);
            } else {
                logger.info("3-1. 无路线更新");
            }

            logger.info("4. 新增操作记录");
            contractOperationRecordService.save(ContractOperationRecord.newInstance(contract.getId(), vo.getOperationById(), vo.getOperationByName(), vo.getDescription()));
        } catch (TmsDataValidationException e) {
            logger.error("数据校验异常!", e);
            throw e;
        } catch (Exception e) {
            logger.error("更新失败!", e);
            throw e;
        }
        return ResultsUtil.getSuccessResultMap("更新成功!");
    }

    private void checkUpdate(UpdateContractVo vo) {
        if (Objects.isNull(vo)) {
            throw new TmsDataValidationException("请求主体对象不能为空!");
        }
        if (Objects.isNull(vo.getContract())) {
            throw new TmsDataValidationException("合同对象不能为空!");
        }
        if (Strings.isNullOrEmpty(vo.getOperationById())) {
            throw new TmsDataValidationException("操作人ID不能为空!");
        }
        if (Strings.isNullOrEmpty(vo.getOperationByName())) {
            throw new TmsDataValidationException("作废人名称不能为空!");
        }
        if (Strings.isNullOrEmpty(vo.getDescription())) {
            throw new TmsDataValidationException("说明不能为空!");
        }
    }

    @Override
    public Page<Contract> pageData(QueryContractVo vo) {
        Sort sort = new Sort(vo.getDirection(), vo.getSortList());
        PageRequest request = new PageRequest(vo.getPage(), vo.getSize(), sort);
        return repository.findAll(ContractSpecs.contractPageSpecs(vo.getParams()), request);
    }

    @Override
    public Map pageView(QueryContractVo vo) {
        try {
            logger.info("1. 数据校验");
            getPageViewByDataValidation(vo);

            logger.info("2 查询主表");
            Page<Contract> contractPage = pageData(vo);

            logger.info("3. 查询子表");
            List<ContractView> content = Lists.newArrayList();
            contractPage.getContent().forEach(contract -> {
                String contractId = contract.getId();
                List<ContractLine> lineList = lineService.getListByContractId(contractId);
                List<ContractReview> reviews = reviewService.getListByContractId(contractId);
                List<ContractOperationRecord> operationRecordList = contractOperationRecordService.getListByBusinessId(contractId);

                content.add(ContractView.newInstance(contract, lineList, reviews, operationRecordList));
            });

            logger.info("4. 组装数据返回");
            return ResultsUtil.getSuccessResultMap(PageUtil.newInstancePage(contractPage, content));
        } catch (TmsDataValidationException e) {
            logger.error("数据校验异常!", e);
            throw e;
        } catch (Exception e) {
            logger.error("查询失败!", e);
            throw e;
        }
    }

    private void getPageViewByDataValidation(QueryContractVo vo) {
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

