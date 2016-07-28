package com.wlxk.controller.tradebill;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.wlxk.TmsApplication;
import com.wlxk.controller.sys.vo.user.AddUserVo;
import com.wlxk.controller.tradebill.vo.OpenTradeBillVo;
import com.wlxk.domain.sys.User;
import com.wlxk.domain.sys.UserRole;
import com.wlxk.domain.tradebill.Goods;
import com.wlxk.domain.tradebill.Losses;
import com.wlxk.domain.tradebill.TradeBill;
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

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * Created by malin on 2016/7/28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TmsApplication.class)
@WebAppConfiguration
public class TradeBillControllerTest {
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
        OpenTradeBillVo vo = new OpenTradeBillVo();
        TradeBill tradeBill = new TradeBill();
        tradeBill.setTradeBillNo("TMS_00002");
        List<Goods> goodsList = Lists.newArrayList();
        Goods g1 = new Goods();
        g1.setName("书籍1");
        Goods g2 = new Goods();
        g2.setName("电脑1");
        goodsList.add(g1);
        goodsList.add(g2);
        List<Losses> lossesList = Lists.newArrayList();
        Losses l1 = new Losses();
        l1.setFeeType(1);
        l1.setMoney(BigDecimal.ONE);
        Losses l2 = new Losses();
        l2.setFeeType(2);
        l2.setMoney(BigDecimal.TEN);
        lossesList.add(l1);
        lossesList.add(l2);

        vo.setTradeBill(tradeBill);
        vo.setGoodsList(goodsList);
        vo.setLossesList(lossesList);



        // 模拟发送HTTP请求
        MvcResult result = mockMvc.perform(
                post("/tradeBill/openTradeBill", "json")
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
