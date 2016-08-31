package com.wlxk.business;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.wlxk.TmsApplication;
import com.wlxk.controller.sys.vo.menu.AddMenuVo;
import com.wlxk.controller.sys.vo.role.AddRoleVo;
import com.wlxk.controller.sys.vo.user.AddUserVo;
import com.wlxk.domain.sys.Menu;
import com.wlxk.domain.sys.Role;
import com.wlxk.domain.sys.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TmsApplication.class)
@WebAppConfiguration
public class SysTest {
    @Autowired
    WebApplicationContext context;
    MockMvc mockMvc;

    @Before
    public void setUpMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    private String obj2Json(Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }

    private void doPost(Object vo, String uri) throws Exception {
        MvcResult result = mockMvc.perform(
                post(uri, "json")
                        .characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(obj2Json(vo)))
                .andReturn();
        int status = result.getResponse().getStatus();
        String content = result.getResponse().getContentAsString();
        assertEquals("错误，正确的返回值为200", 200, status);
        System.out.println(content);
    }

    private void doGet(String uri) throws Exception {
        MvcResult result = mockMvc.perform(get(uri)).andReturn();
        int status = result.getResponse().getStatus();
        String content = result.getResponse().getContentAsString();
        assertEquals("错误，正确的返回值为200", 200, status);
        System.out.println(content);
    }

    // 新增菜单
    @Test
    public void addMenu() throws Exception {
        Menu menu = new Menu();
        menu.setName("测试菜单");

        AddMenuVo vo = new AddMenuVo();
        vo.setMenu(menu);
        vo.setOperationById("123456");
        vo.setOperationByName("马林");
        vo.setDescription("新增菜单-单元测试");

        doPost(vo, "/menu/add");
    }

    // 新增角色
    @Test
    public void addRole() throws Exception {
        Role role = new Role();
        role.setName("超级管理员");

        List<String> menuIdList = Lists.newArrayList("402883a356cf6c1e0156cf6c46bf0000");

        AddRoleVo vo = new AddRoleVo();
        vo.setRole(role);
        vo.setMenuIdList(menuIdList);
        vo.setOperationById("123456");
        vo.setOperationByName("马林");
        vo.setDescription("新增角色-单元测试");

        doPost(vo, "/role/add");
    }

    @Test
    public void addUser() throws Exception {
        User user = new User();
        user.setUsername("super_admin");
        user.setPassword("123456");
        user.setName("马林");

        List<String> roleIdList = Lists.newArrayList("402883a356cf74820156cf74a6880000");

        AddUserVo vo = new AddUserVo();
        vo.setUser(user);
        vo.setRoleIdList(roleIdList);
        vo.setOperationById("123456");
        vo.setOperationByName("马林");
        vo.setDescription("新增用户-单元测试");

        doPost(vo, "/user/add");
    }
}
