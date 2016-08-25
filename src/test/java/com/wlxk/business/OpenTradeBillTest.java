package com.wlxk.business;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.wlxk.TmsApplication;
import com.wlxk.controller.contract.vo.AddContractVo;
import com.wlxk.controller.tradebill.vo.OpenTradeBillVo;
import com.wlxk.domain.car.Driver;
import com.wlxk.domain.contract.Contract;
import com.wlxk.domain.contract.ContractLine;
import com.wlxk.domain.tradebill.TradeBill;
import com.wlxk.repository.car.DriverRepository;
import com.wlxk.service.tradebill.TradeBillService;
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

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * 开单单元测试
 * <p>
 * Created by 马林 on 2016/8/13.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TmsApplication.class)
@WebAppConfiguration
public class OpenTradeBillTest {
    @Autowired
    private TradeBillService service;
    @Autowired
    private DriverRepository driverRepository;
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

    // 开单
    @Test
    public void openTradeBill() throws Exception {
        TradeBill tradeBill = new TradeBill();
        tradeBill.setTradeBillNo("Test1");

        OpenTradeBillVo vo = new OpenTradeBillVo();
        doPost(vo, "/tradeBill/openTradeBill");
    }

    // 创建合同
    @Test
    public void createContract() throws Exception {
        // 司机ID
        String driverId = "1";

        // 装车合同ID
        Contract contract = new Contract();
        contract.setDriverId(driverId);
        contract.setContractNo("ZC_00001");
        contract.setSettleType(CommonProperty.SettleType.现金);
        contract.setStatus(CommonProperty.ContractStatus.待装车);
        contract.setDescription("马林-单元测试创建合同");


        ContractLine cl1 = new ContractLine();
        cl1.setStartStation("上海");
        cl1.setEndStation("成都");

        // 运输线路
        List<ContractLine> contractLineList = Lists.newArrayList(cl1);


        AddContractVo vo = AddContractVo.newInstance(contract, contractLineList);

        doPost(vo, "/contract/add");
    }

    // 装车
    @Test
    public void loading() throws Exception {

        doPost(null, "/contract/loading");
    }

    // 发车
    @Test
    public void departure() throws Exception {

    }

    // 签收
    @Test
    public void receipt() throws Exception {

    }

    // 分页显示交易单列表
    @Test
    public void pageTradeBill() throws Exception {

    }

    // 显示交易单明细
    @Test
    public void showTradeBillDetail() throws Exception {

    }

    // 分页显示装车合同
    @Test
    public void pageContract() throws Exception {

    }

    // 显示合同明细
    @Test
    public void showContractDetail() throws Exception {

    }

    // 车辆跟踪
    @Test
    public void carTrack() throws Exception {

    }


    // 分页显示费用
    @Test
    public void pageFee() throws Exception {

    }
}
