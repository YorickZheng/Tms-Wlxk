package com.wlxk.controller.contract;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wlxk.TmsApplication;
import com.wlxk.controller.contract.vo.*;
import com.wlxk.domain.contract.Contract;
import com.wlxk.domain.contract.ContractLine;
import com.wlxk.repository.contract.ContractRepository;
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

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TmsApplication.class)
@WebAppConfiguration
public class ContractControllerTest {
    @Autowired
    ContractRepository repository;

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
        Contract contract = Contract.newInstance("C_00002", "测试合同");
        ContractLine line1 = ContractLine.newInstance("123", "上海卡行", "上海", "合肥");
        ContractLine line2 = ContractLine.newInstance("123", "合肥卡行", "合肥", "武汉");

        AddContractVo vo = new AddContractVo();
        vo.setContract(contract);
        vo.setLineList(Lists.newArrayList(line1, line2));
        vo.setOperationById("123456");
        vo.setOperationByName("马林");
        vo.setDescription("马林测试新建合同");


        doPost(vo, "/contract/add");
    }

    @Test
    public void testReview() throws Exception {
        ReviewContractVo vo = new ReviewContractVo();
        vo.setBusinessId("402883a2563c548c01563c5497520000");
        vo.setOperationById("123456");
        vo.setOperationByName("马林");
        vo.setDescription("马林测试合同审核");
        vo.setCommand(CommonProperty.ReviewCommand.AUDITED_COMMAND);
        //vo.setDescription("马林测试合同取消审核");
        //vo.setCommand(CommonProperty.ReviewCommand.CANNEL_COMMAND);

        doPost(vo, "/contract/review");
    }

    @Test
    public void testDisuse() throws Exception {
        DisuseContractVo vo = new DisuseContractVo();

        vo.setBusinessId("402883a2563c527501563c527fb50000");
        vo.setOperationById("123456");
        vo.setOperationByName("马林");
        vo.setDescription("马林测试合同取消作废");
        vo.setCommand(CommonProperty.DisuseCommand.DISUSE_CANCEL);

        doPost(vo, "/contract/disuse");
    }

    @Test
    public void testUpdate() throws Exception {
        Contract contract = repository.findOne("402883a2563c548c01563c5497520000");
        contract.setContractNo("My_----1");

        ContractLine line = ContractLine.newInstance("123", "上海卡行", "上海", "成都");

        UpdateContractVo vo = new UpdateContractVo();
        vo.setContract(contract);
        vo.setLineList(Lists.newArrayList(line));
        vo.setOperationById("123456");
        vo.setOperationByName("马林");
        vo.setDescription("马林测试更新合同");

        doPost(vo, "/contract/update");

    }

    @Test
    public void testPageView() throws Exception {
        Map<String, Object> params = Maps.newConcurrentMap();
        params.put("contractNo", "U");
        QueryContractVo vo = new QueryContractVo();
        vo.setParams(params);


        doPost(vo, "/contract/pageView");
    }
}
