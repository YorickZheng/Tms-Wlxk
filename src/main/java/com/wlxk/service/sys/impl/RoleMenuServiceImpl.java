package com.wlxk.service.sys.impl;

import com.wlxk.domain.sys.RoleMenu;
import com.wlxk.repository.sys.RoleMenuRepository;
import com.wlxk.service.sys.RoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by malin on 2016/7/28.
 */
@Service
public class RoleMenuServiceImpl implements RoleMenuService {

    @Autowired(required = false)
    private RoleMenuRepository repository;

    @Override
    public RoleMenu save(RoleMenu roleMenu) {
        return repository.save(roleMenu);
    }

    @Override
    public Iterable<RoleMenu> save(List<RoleMenu> list) {
        return repository.save(list);
    }

    @Override
    public void deleteByRoleId(String roleId) {
        repository.deleteByRoleId(roleId);
    }

    @Override
    public List<RoleMenu> getListByRoleId(String roleId) {
        return repository.findByRoleId(roleId);
    }

    @Override
    public List<RoleMenu> getListByRoleIdList(List<String> roleIdList) {
        return repository.findByRoleIdIn(roleIdList);
    }
}
