package com.wlxk.service.contract.impl;

import com.wlxk.domain.contract.ContractLine;
import com.wlxk.repository.contract.ContractLineRepository;
import com.wlxk.service.contract.ContractLineService;
import com.wlxk.support.exception.TmsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016/7/30.
 */
@Service
public class ContractLineServiceImpl implements ContractLineService {

    @Autowired(required = false)
    private ContractLineRepository repository;

    @Override
    public ContractLine save(ContractLine contractLine) {
        return repository.save(contractLine);
    }

    @Override
    public Iterable<ContractLine> save(List<ContractLine> list) {
        return repository.save(list);
    }

    @Override
    public List<ContractLine> getListByContractId(String contractId) {
        return repository.findByContractId(contractId);
    }

    @Override
    public void deleteListByContractId(String contractId) {
        repository.deleteByContractId(contractId);
    }
}
