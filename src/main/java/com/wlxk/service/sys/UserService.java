package com.wlxk.service.sys;

import com.wlxk.domain.sys.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

/**
 * Created by malin on 2016/7/22.
 */
public interface UserService {
    Map login(String username, String password);
    Map save(User user);
    Page<User> pageData(Pageable pageable);
}
