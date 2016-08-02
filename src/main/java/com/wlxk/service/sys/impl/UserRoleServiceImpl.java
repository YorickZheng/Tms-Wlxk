package com.wlxk.service.sys.impl;

import com.wlxk.domain.sys.UserRole;
import com.wlxk.repository.sys.UserRoleRepository;
import com.wlxk.service.sys.UserRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by malin on 2016/7/27.
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {

    private static final Logger logger = LoggerFactory.getLogger(UserRoleServiceImpl.class);

    @Autowired(required = false)
    private UserRoleRepository repository;

    @Override
    public UserRole save(UserRole userRole) {
        return repository.save(userRole);
    }

    @Override
    public Iterable<UserRole> save(List<UserRole> list) {
        return repository.save(list);
    }

    @Override
    public void deleteByUserId(String userId) {
        repository.deleteByUserId(userId);
    }

    @Override
    public List<UserRole> getListByUserId(String userId) {
        return repository.findByUserId(userId);
    }
}
