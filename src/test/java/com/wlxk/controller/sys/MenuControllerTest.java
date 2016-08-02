package com.wlxk.controller.sys;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wlxk.TmsApplication;
import com.wlxk.controller.sys.vo.menu.AddMenuVo;
import com.wlxk.controller.sys.vo.menu.DisuseMenuVo;
import com.wlxk.controller.sys.vo.menu.QueryMenuVo;
import com.wlxk.controller.sys.vo.menu.UpdateMenuVo;
import com.wlxk.domain.sys.Menu;
import com.wlxk.repository.sys.MenuRepository;
import com.wlxk.support.util.CommonProperty;
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

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * Created by malin on 2016/7/28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TmsApplication.class)
@WebAppConfiguration
public class MenuControllerTest {
    @Autowired
    MenuRepository repository;

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
        Menu menu = new Menu();
        menu.setName("修改用户");
        menu.setCode("ADD_USER");
        menu.setUrl("/user/manager/add");
        menu.setImageUrl("/user/manager/add_user.jpg");
        menu.setDescription("修改用户页面");
        menu.setLevel(2);
        menu.setParentMenuId("4028839456316f7f0156316f9b120000");

        AddMenuVo vo = new AddMenuVo();
        vo.setMenu(menu);
        vo.setOperationById("123456");
        vo.setOperationByName("马林");
        vo.setDescription("马林测试新增菜单!");
        doPost(vo, "/menu/add");
    }

    @Test
    public void testDisuse() throws Exception {
        DisuseMenuVo vo = new DisuseMenuVo();
        vo.setBusinessId("4028839456316f7f0156316f9b120000");
        vo.setOperationById("123456");
        vo.setOperationByName("马林");
        vo.setDescription("马林测试菜单取消作废!");
        vo.setCommand(CommonProperty.DisuseCommand.DISUSE_CANCEL);

        doPost(vo, "/menu/disuse");
    }

    @Test
    public void testUpdate() throws Exception {
        Menu menu = repository.findOne("4028839456316f7f0156316f9b120000");
        menu.setName("User Manager");

        UpdateMenuVo vo = new UpdateMenuVo();
        vo.setMenu(menu);
        vo.setOperationById("123456");
        vo.setOperationByName("马林");
        vo.setDescription("马林测试更新菜单");

        doPost(vo, "/menu/update");
    }

    @Test
    public void testQueryOneMenu() throws Exception {
        QueryMenuVo vo = new QueryMenuVo();
        vo.setMenuId("4028839456316f7f0156316f9b120000");
        vo.setCommand(CommonProperty.MenuQueryCommand.QUERY_BY_ID);
        vo.setCommand(CommonProperty.MenuQueryCommand.QUERY_BY_PARENT_ID);

        doPost(vo, "/menu/getPageView");
    }
}
