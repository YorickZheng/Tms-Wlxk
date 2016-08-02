package com.wlxk.service.sys.impl;

import com.wlxk.domain.sys.RoleOperationRecord;
import com.wlxk.repository.sys.RoleOperationRecordRepository;
import com.wlxk.service.sys.RoleOperationRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by malin on 2016/7/28.
 */
@Service
public class RoleOperationRecordServiceImpl implements RoleOperationRecordService {

    @Autowired(required = false)
    private RoleOperationRecordRepository repository;

    @Override
    public RoleOperationRecord save(RoleOperationRecord operationRecord) {
        return repository.save(operationRecord);
    }

    @Override
    public List<RoleOperationRecord> getListByRoleId(String roleId) {
        return repository.findByBusinessId(roleId);
    }
}
