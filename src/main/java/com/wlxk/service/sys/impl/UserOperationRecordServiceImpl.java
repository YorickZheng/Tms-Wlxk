package com.wlxk.service.sys.impl;

import com.wlxk.domain.sys.UserOperationRecord;
import com.wlxk.repository.sys.UserOperationRecordRepository;
import com.wlxk.service.sys.UserOperationRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户操作记录服务层实现
 *
 * @author malin
 * @version 1.0
 * @date 2016/7/27
 */
@Service
public class UserOperationRecordServiceImpl implements UserOperationRecordService {

    @Autowired(required = false)
    private UserOperationRecordRepository repository;

    @Override
    public UserOperationRecord save(UserOperationRecord operationRecord) {
        return repository.save(operationRecord);
    }

    @Override
    public Iterable<UserOperationRecord> save(List<UserOperationRecord> list) {
        return repository.save(list);
    }

    @Override
    public List<UserOperationRecord> getListByUserId(String userId) {
        return repository.findByBusinessId(userId);
    }
}
