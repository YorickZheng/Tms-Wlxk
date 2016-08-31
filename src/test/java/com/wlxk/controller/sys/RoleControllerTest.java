package com.wlxk.controller.sys;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wlxk.TmsApplication;
import com.wlxk.controller.sys.vo.role.AddRoleVo;
import com.wlxk.controller.sys.vo.role.DisuseRoleVo;
import com.wlxk.controller.sys.vo.role.QueryRoleVo;
import com.wlxk.controller.sys.vo.role.UpdateRoleVo;
import com.wlxk.domain.sys.Role;
import com.wlxk.domain.sys.RoleMenu;
import com.wlxk.repository.sys.RoleRepository;
import com.wlxk.support.util.CommonProperty;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * Created by malin on 2016/7/28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TmsApplication.class)
@WebAppConfiguration
public class RoleControllerTest {
    @Autowired
    RoleRepository repository;

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

    @Test
    public void testAdd() throws Exception {
        Role role = Role.getInstance("管理员", "Admin", "超级管理员");

        RoleMenu rm1 = RoleMenu.getInstance("", "4028839456316f7f0156316f9b120000");
        RoleMenu rm2 = RoleMenu.getInstance("", "40288394563190e90156319102610000");
        RoleMenu rm3 = RoleMenu.getInstance("", "40288394563192280156319243ec0000");

        List<RoleMenu> roleMenuList = Lists.newArrayList(rm1, rm2, rm3);

        AddRoleVo vo = new AddRoleVo();
        vo.setRole(role);
        //vo.setRoleMenuList(roleMenuList);
        vo.setOperationById("123456");
        vo.setOperationByName("马林");
        vo.setDescription("马林测试新增角色");

        doPost(vo, "/role/add");
    }

    @Test
    public void testDisuse() throws Exception {
        DisuseRoleVo vo = new DisuseRoleVo();
        //vo.setCommand(CommonProperty.DisuseCommand.DISUSE);
        vo.setCommand(CommonProperty.DisuseCommand.DISUSE_CANCEL);
        vo.setBusinessId("402883945631ad9d015631adb6ec0000");
        vo.setOperationById("123456");
        vo.setOperationByName("马林");
        vo.setDescription("马林测试取消作废角色");

        doPost(vo, "/role/disuse");
    }

    @Test
    public void testUpdate() throws Exception {
        Role role = repository.findOne("402883945631ad9d015631adb6ec0000");
        role.setName("超级管理员");
        role.setCode("Super Admin");
        role.setDescription("拥有所有权限");

        UpdateRoleVo vo = new UpdateRoleVo();
        vo.setOperationById("123456");
        vo.setOperationByName("马林");
        vo.setDescription("马林测试修改角色");
        vo.setRole(role);

        doPost(vo, "/role/update");
    }

    @Test
    public void testPageView() throws Exception {
        Map<String, Object> params = Maps.newConcurrentMap();
        params.put("name", "管理员");

        QueryRoleVo vo = new QueryRoleVo();
        vo.setParams(params);
        vo.setPage(1);
        vo.setSize(1);
        vo.setSortList(Lists.newArrayList("id", "createByDate"));

        doPost(vo, "/role/pageView");
    }
}
