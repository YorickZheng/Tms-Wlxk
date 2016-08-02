package com.wlxk.repository.sys;

import com.wlxk.domain.sys.User;
import com.wlxk.support.CustomRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * 用户仓储层
 *
 * @author malin
 * @version 1.0
 * @date 2016/7/22
 */
public interface UserRepository extends PagingAndSortingRepository<User, String>, JpaSpecificationExecutor<User> {

    User findOneByUsernameAndPassword(String username, String password);

    User findOneByUsername(String username);

    Page<User> findAll(Pageable pageable);
}
