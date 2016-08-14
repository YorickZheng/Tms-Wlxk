package com.wlxk.business;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wlxk.TmsApplication;
import com.wlxk.controller.tradebill.vo.OpenTradeBillVo;
import com.wlxk.domain.tradebill.TradeBill;
import com.wlxk.service.tradebill.TradeBillService;
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
 * 开单单元测试
 *
 * Created by 马林 on 2016/8/13.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TmsApplication.class)
@WebAppConfiguration
public class OpenTradeBillTest {
    @Autowired
    private TradeBillService service;
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
    public void testOne() throws Exception {
        TradeBill tradeBill = new TradeBill();
        tradeBill.setTradeBillNo("Test1");

        OpenTradeBillVo vo = new OpenTradeBillVo();
        doPost(vo, "/tradeBill/openTradeBill");
    }
}
