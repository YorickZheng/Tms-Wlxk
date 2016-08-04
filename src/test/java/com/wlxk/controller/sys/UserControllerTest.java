package com.wlxk.controller.sys;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wlxk.TmsApplication;
import com.wlxk.controller.sys.vo.user.AddUserVo;
import com.wlxk.controller.sys.vo.user.DisuseUserVo;
import com.wlxk.controller.sys.vo.user.QueryUserVo;
import com.wlxk.controller.sys.vo.user.UpdateUserVo;
import com.wlxk.domain.sys.User;
import com.wlxk.domain.sys.UserRole;
import com.wlxk.repository.sys.UserRepository;
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

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TmsApplication.class)
@WebAppConfiguration
public class UserControllerTest {

    @Autowired
    UserRepository userRepository;

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


    @Test
    public void testAdd() throws Exception {
        AddUserVo vo = new AddUserVo();
        vo.setOperationById("123456");
        vo.setOperationByName("管理员");
        vo.setDescription("新增用户!");
        vo.setUser(new User("MaLin", "123456", "马林"));
        vo.setUserRoleList(Lists.newArrayList(new UserRole("402883945631ad9d015631adb6ec0000"), new UserRole("186d6f4f-62d3-4583-990e-781ffa92e345")));

        MvcResult result = mockMvc.perform(
                post("/user/add", "json")
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
    public void testDisuse() throws Exception {
        DisuseUserVo vo = new DisuseUserVo();
        vo.setCommand(2);
        vo.setOperationById("123456");
        vo.setOperationByName("管理员");
        vo.setDescription((vo.getCommand() == 1 ? "作废" : "取消作废") + "用户!");
        vo.setBusinessId("40288394562ce84401562ce85eda0000");

        MvcResult result = mockMvc.perform(
                post("/user/disuse", "json")
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
    public void testUpdate() throws Exception {
        User user = userRepository.findOne("40288394562ce84401562ce85eda0000");
        user.setName("小马哥");
        UpdateUserVo vo = new UpdateUserVo();
        vo.setUser(user);
        vo.setOperationById("123456");
        vo.setOperationByName("管理员");
        vo.setDescription("更新用户!");
        // vo.setBusinessId("40288394562ce84401562ce85eda0000");

        MvcResult result = mockMvc.perform(
                post("/user/update", "json")
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
    public void testPage() throws Exception {
        Map<String, Object> params = Maps.newConcurrentMap();
        params.put("username", "MaXZX");

        QueryUserVo vo = new QueryUserVo();
        vo.setParams(params);


        MvcResult result = mockMvc.perform(
                post("/user/pageView", "json")
                        .characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(obj2Json(vo)))
                .andReturn();
        int status = result.getResponse().getStatus();
        String content = result.getResponse().getContentAsString();
        assertEquals("错误，正确的返回值为200", 200, status);
        System.out.println(content);
    }
}
