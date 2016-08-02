package com.wlxk.controller.car;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wlxk.TmsApplication;
import com.wlxk.controller.car.vo.AddCarVo;
import com.wlxk.controller.car.vo.DisuseCarVo;
import com.wlxk.controller.car.vo.QueryCarVo;
import com.wlxk.controller.car.vo.UpdateCarVo;
import com.wlxk.domain.car.Car;
import com.wlxk.domain.car.Driver;
import com.wlxk.repository.car.CarRepository;
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

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TmsApplication.class)
@WebAppConfiguration
public class CarControllerTest {
    @Autowired
    CarRepository repository;

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
        Car car = new Car();
        car.setNumberPlate("鄂D 123456");

        Driver d1 = new Driver();
        d1.setLinkman("马林");
        Driver d2 = new Driver();
        d2.setLinkman("田野");

        AddCarVo vo = new AddCarVo();
        vo.setCar(car);
        vo.setDriverList(Lists.newArrayList(d1, d2));
        vo.setOperationById("123456");
        vo.setOperationByName("马林");
        vo.setDescription("马林测试车辆新增");

        doPost(vo, "/car/add");
    }

    @Test
    public void testDisuse() throws Exception {

        DisuseCarVo vo = new DisuseCarVo();
        vo.setBusinessId("402883a2563cf01801563cf022fc0000");
        vo.setOperationById("123456");
        vo.setOperationByName("马林");
        vo.setDescription("测试作废");
        vo.setCommand(CommonProperty.DisuseCommand.DISUSE_CANCEL);

        doPost(vo, "/car/disuse");
    }

    @Test
    public void testUpdate() throws Exception {
        Car car = repository.findOne("402883a2563cf01801563cf022fc0000");
        car.setNumberPlate("沪A 1111");

        Driver d1 = new Driver();
        d1.setLinkman("马林xxx");
        Driver d2 = new Driver();
        d2.setLinkman("田野xxx");

        UpdateCarVo vo = new UpdateCarVo();
        vo.setCar(car);
        vo.setDriverList(Lists.newArrayList(d1, d2));
        vo.setOperationById("123456");
        vo.setOperationByName("马林");
        vo.setDescription("测试作废");

        doPost(vo, "/car/update");
    }

    @Test
    public void tetePageView() throws Exception {
        Map<String, Object> params = Maps.newConcurrentMap();
        params.put("numberPlate", "D");
        QueryCarVo vo = new QueryCarVo();
        vo.setParams(params);

        doPost(vo, "/car/pageView");
    }

}
