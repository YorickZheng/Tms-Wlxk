package com.wlxk.controller.sys;

import com.wlxk.domain.sys.User;
import com.wlxk.service.sys.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.Map;

/**
 * Created by malin on 2016/7/22.
 */
@RestController
@RequestMapping("/sys")
public class SysController {
    @Autowired(required = false)
    private UserService userService;

    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public Map login(@PathParam("username") String username, @PathParam("password") String password) {
        return userService.login(username, password);
    }

    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    public Map addUser(@RequestBody User user) {
        return userService.save(user);
    }

    @RequestMapping(value = "/user/update", method = RequestMethod.POST)
    public Map updateUser(User user) {
        return userService.save(user);
    }


    @RequestMapping(value = "/user/page", method = RequestMethod.GET)
    public Page<User> pageDataUser(@PathParam("page") Integer page, @PathParam("size") Integer size) {
        return userService.pageData(new PageRequest(page, size));
    }


}
