package com.wlxk.service.contract;

import com.wlxk.domain.contract.ContractLine;

import java.util.List;

/**
 * Created by malin on 2016/7/26.
 */
public interface ContractLineService {
    ContractLine save(ContractLine contractLine);
    Iterable<ContractLine> save(List<ContractLine> list);
    List<ContractLine> getListByContractId(String contractId);
    void deleteListByContractId(String contractId);
}
