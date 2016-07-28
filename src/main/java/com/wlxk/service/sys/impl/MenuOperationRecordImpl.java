package com.wlxk.service.sys.impl;

import com.wlxk.domain.sys.MenuOperationRecord;
import com.wlxk.repository.sys.MenuOperationRecordRepository;
import com.wlxk.service.sys.MenuOperationRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by malin on 2016/7/28.
 */
@Service
public class MenuOperationRecordImpl implements MenuOperationRecordService {

    @Autowired(required = false)
    private MenuOperationRecordRepository repository;

    @Override
    public MenuOperationRecord save(MenuOperationRecord operationRecord) {
        return repository.save(operationRecord);
    }
}
