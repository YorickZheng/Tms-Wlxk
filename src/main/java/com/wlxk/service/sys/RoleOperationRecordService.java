package com.wlxk.service.sys;

import com.wlxk.domain.sys.RoleOperationRecord;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by malin on 2016/7/28.
 */
@Transactional
public interface RoleOperationRecordService {
    RoleOperationRecord save(RoleOperationRecord operationRecord);
}
