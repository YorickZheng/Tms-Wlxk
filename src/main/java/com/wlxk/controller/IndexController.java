package com.wlxk.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by malin on 2016/7/20.
 */
@RestController
public class IndexController {

    @RequestMapping(value = "/login")
    public Map<String, Object> login(@RequestBody User user) {
        Map<String, Object> result = new HashMap<>();
        if (user.getUsername().equals("admin") && user.getPassword().equals("admin")) {
            result.put("flag", true);
            result.put("message", "验证成功");
        } else {
            result.put("flag", false);
            result.put("message", "验证失败");
        }
        return result;
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
        public Map<String, Object> getData(@PathParam("currentPage") Integer currentPage, @PathParam("pageSize") Integer pageSize) {
        Map<String, Object> result = new HashMap<>();
        List<Book> data = new ArrayList<>();
        for (int i =0;i<10;i++) {
            data.add(new Book("Java编程思想:" + i + "版", "ml"));
        }
        result.put("data", data);
        result.put("currentPage", 1);
        result.put("pageSize", 10);
        result.put("records", 100);
        return result;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String hello() {
        return "Hello World";
    }
}

class Book {
    String name;
    String author;

    public Book(String name, String author) {
        this.name = name;
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}

class User {
    String username;
    String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}