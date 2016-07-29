package com.wlxk.service.sys;

import com.wlxk.domain.sys.UserOperationRecord;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户操作记录服务层
 *
 * @author malin
 * @version 1.0
 * @date 2016/7/27
 */
@Transactional
public interface UserOperationRecordService {
    UserOperationRecord save(UserOperationRecord operationRecord);
    Iterable<UserOperationRecord> save(List<UserOperationRecord> list);
    List<UserOperationRecord> getListByUserId(String userId);
}
