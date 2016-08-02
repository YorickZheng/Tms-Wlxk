package com.wlxk.service.sys;

import com.wlxk.controller.sys.vo.role.*;
import com.wlxk.domain.sys.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by malin on 2016/7/28.
 */
@Transactional
public interface RoleService {
    Role save(Role role);
    Iterable<Role> save(List<Role> list);
    Role getOneById(String id);
    List<Role> getListById(List<String> idList);

    Map add(AddRoleVo vo);
    Map disuse(DisuseRoleVo vo);
    Map update(UpdateRoleVo vo);
    Page<Role> getPage(QueryRoleVo vo);
    Map getPageView(QueryRoleVo vo);
    Map queryByCode(String code);
}
