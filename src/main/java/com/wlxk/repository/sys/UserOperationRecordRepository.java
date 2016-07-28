package com.wlxk.repository.sys;

import com.wlxk.domain.sys.UserOperationRecord;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * 用户操作记录仓储层
 *
 * @author malin
 * @version 1.0
 * @date 2016/7/27
 */
public interface UserOperationRecordRepository extends CrudRepository<UserOperationRecord, String> {
    List<UserOperationRecord> findByBusinessId(String businessId);
}
