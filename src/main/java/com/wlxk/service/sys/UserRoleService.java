package com.wlxk.service.sys;

import com.wlxk.domain.sys.UserRole;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by malin on 2016/7/27.
 */
@Transactional
public interface UserRoleService {
    UserRole save(UserRole userRole);
    Iterable<UserRole> save(List<UserRole> list);
    void deleteByUserId(String userId);
    List<UserRole> getListByUserId(String userId);
}
