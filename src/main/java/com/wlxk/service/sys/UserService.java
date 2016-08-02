package com.wlxk.service.sys;

import com.wlxk.controller.sys.vo.user.AddUserVo;
import com.wlxk.controller.sys.vo.user.DisuseUserVo;
import com.wlxk.controller.sys.vo.user.QueryUserVo;
import com.wlxk.controller.sys.vo.user.UpdateUserVo;
import com.wlxk.domain.sys.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    User findByUsernameAndPassword(String username, String password);

    User save(User user);

    User getOneById(String id);

    Map add(AddUserVo vo);

    Map disuse(DisuseUserVo vo);

    Map update(UpdateUserVo vo);

    Page<User> getPage(QueryUserVo vo);

    Map getPageView(QueryUserVo vo);

    Map findByUsername(String username);

    Map getByUsernameAndPassword(String username, String password);
}
