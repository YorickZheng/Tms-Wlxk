package com.wlxk.service.sys;

import com.wlxk.controller.sys.vo.role.AddRoleVo;
import com.wlxk.controller.sys.vo.role.DisuseRoleVo;
import com.wlxk.controller.sys.vo.role.UpdateRoleVo;
import com.wlxk.domain.sys.Role;

import java.util.List;
import java.util.Map;

/**
 * Created by malin on 2016/7/28.
 */
public interface RoleService {
    Role save(Role role);
    Iterable<Role> save(List<Role> list);
    Role getOneById(String id);
    List<Role> getListById(List<String> idList);

    Map addRole(AddRoleVo vo);
    Map disuseRole(DisuseRoleVo vo);
    Map updateRole(UpdateRoleVo vo);
}
