package com.wlxk.service.sys.impl;

import com.wlxk.domain.sys.User;
import com.wlxk.repository.sys.UserRepository;
import com.wlxk.service.sys.UserService;
import com.wlxk.support.util.ResultsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by malin on 2016/7/22.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired(required = false)
    private UserRepository userRepository;

    @Override
    public Map login(String username, String password) {
        User user = userRepository.findOneByUsernameAndPassword(username, password);
        if (user != null) {
            return ResultsUtil.getSuccessResultMap(user);
        } else {
            return ResultsUtil.getFailureResultMap("账号或密码错误!");
        }
    }


    @Override
    public Map save(User user) {
        try {
            return ResultsUtil.getSuccessResultMap(userRepository.save(user));
        } catch (Exception e) {
            return ResultsUtil.getFailureResultMap("用户保存失败!");
        }
    }

    @Override
    public Page<User> pageData(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
}
