package com.wlxk.service.sys;

import com.wlxk.domain.sys.RoleMenu;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by malin on 2016/7/28.
 */
@Transactional
public interface RoleMenuService {
    RoleMenu save(RoleMenu roleMenu);
    Iterable<RoleMenu> save(List<RoleMenu> list);
    void deleteByRoleId(String roleId);
    List<RoleMenu> getListByRoleId(String roleId);
    List<RoleMenu> getListByRoleIdList(List<String> roleIdList);
}
