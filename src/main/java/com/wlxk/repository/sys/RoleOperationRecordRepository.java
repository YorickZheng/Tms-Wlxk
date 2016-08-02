package com.wlxk.repository.sys;

import com.wlxk.domain.sys.RoleOperationRecord;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by malin on 2016/7/28.
 */
public interface RoleOperationRecordRepository extends CrudRepository<RoleOperationRecord, String> {
    List<RoleOperationRecord> findByBusinessId(String businessId);
}
