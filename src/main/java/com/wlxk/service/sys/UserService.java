package com.wlxk.service.sys;

import com.wlxk.controller.sys.vo.user.*;
import com.wlxk.domain.sys.User;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * 用户服务层
 *
 * @author malin
 * @version 1.0
 * @date 2016/7/22
 */
@Transactional
public interface UserService {
    User save(User user);

    User getOneById(String id);

    Map add(AddUserVo vo);

    Map disuse(DisuseUserVo vo);

    Map update(UpdateUserVo vo);

    Page<User> getUserPage(QueryUserVo vo, Integer page, Integer size);

    Map getUserViewPage(Page<User> page);

}
