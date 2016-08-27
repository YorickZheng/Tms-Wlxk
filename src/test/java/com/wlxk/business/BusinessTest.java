package com.wlxk.business;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wlxk.TmsApplication;
import com.wlxk.controller.contract.vo.*;
import com.wlxk.controller.tradebill.vo.OpenTradeBillVo;
import com.wlxk.controller.tradebill.vo.QueryLossesVo;
import com.wlxk.domain.contract.Contract;
import com.wlxk.domain.contract.ContractLine;
import com.wlxk.domain.tradebill.Goods;
import com.wlxk.domain.tradebill.Losses;
import com.wlxk.domain.tradebill.TradeBill;
import com.wlxk.repository.car.DriverRepository;
import com.wlxk.repository.contract.ContractRepository;
import com.wlxk.service.tradebill.TradeBillService;
import com.wlxk.support.util.CommonProperty;
import com.wlxk.support.vo.BasicQueryVo;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

/**
 * 开单单元测试
 * <p>
 * Created by 马林 on 2016/8/13.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TmsApplication.class)
@WebAppConfiguration
public class BusinessTest {
    @Autowired
    private TradeBillService service;
    @Autowired
    private DriverRepository driverRepository;
    @Autowired
    private ContractRepository contractRepository;
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

    // 开单
    @Test
    public void openTradeBill() throws Exception {
        TradeBill tradeBill = new TradeBill();
        tradeBill.setTradeBillNo("Test1");

        Goods goods1 = new Goods();
        goods1.setName("商品1");
        Goods goods2 = new Goods();
        goods2.setName("商品2");
        List<Goods> goodsList = Lists.newArrayList(goods1, goods2);

        Losses losses1 = new Losses();
        losses1.setMoney(BigDecimal.ONE);
        Losses losses2 = new Losses();
        losses2.setMoney(BigDecimal.ONE);
        List<Losses> lossesList = Lists.newArrayList(losses1, losses2);


        OpenTradeBillVo vo = OpenTradeBillVo.newInstance(tradeBill, goodsList, lossesList);
        vo.setOperationById("123456");
        vo.setOperationByName("马林");
        vo.setDescription("开单-单元测试");
        doPost(vo, "/tradeBill/openTradeBill");
    }

    // 创建合同
    @Test
    public void createContract() throws Exception {
        // 司机ID
        String driverId = "123456";

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
        vo.setOperationById("123456");
        vo.setOperationByName("马林");
        vo.setDescription("创建合同-单元测试");


        doPost(vo, "/contract/add");
    }

    // 装车
    @Test
    public void loading() throws Exception {
        String contractId = "402883a356c6e1570156c6e17d290000";
        List<String> tradeBillIdList = Lists.newArrayList("402883a356c6df990156c6dfbef30000");

        LoadingVo vo = LoadingVo.newInstance(contractId, tradeBillIdList);
        vo.setOperationById("123456");
        vo.setOperationByName("马林");
        vo.setDescription("装车-单元测试");

        doPost(vo, "/contract/loading");
    }

    // 发车
    @Test
    public void departure() throws Exception {
        String contractId = "402883a356c6e1570156c6e17d290000";

        DepartureVo vo = new DepartureVo();
        vo.setContractId(contractId);
        vo.setOperationById("123456");
        vo.setOperationByName("马林");
        vo.setDescription("发车-单元测试");

        doPost(vo, "/contract/departure");
    }

    // 签收
    @Test
    public void receipt() throws Exception {
        String contractId = "402883a356c6e1570156c6e17d290000";

        ReceiptVo vo = new ReceiptVo();
        vo.setContractId(contractId);
        vo.setOperationById("123456");
        vo.setOperationByName("马林");
        vo.setDescription("签收-单元测试");

        doPost(vo, "/contract/receipt");
    }

    // 分页显示交易单列表
    @Test
    public void pageTradeBill() throws Exception {
        BasicQueryVo vo = new BasicQueryVo();
        vo.setParams(Maps.newConcurrentMap());
        doPost(vo, "/tradeBill/pageView");
    }

    // 显示交易单明细
    @Test
    public void showTradeBillDetail() throws Exception {
        doGet("/tradeBill/402883a356c6df990156c6dfbef30000");
    }

    // 分页显示装车合同
    @Test
    public void pageContract() throws Exception {
        QueryContractVo vo = new QueryContractVo();

        vo.setParams(Maps.newConcurrentMap());

        doPost(vo, "/contract/pageView");
    }

    // 显示合同明细
    @Test
    public void showContractDetail() throws Exception {
        doGet("/contract/402883a356c6e1570156c6e17d290000");
    }

    // 车辆跟踪
    @Test
    public void carTrack() throws Exception {

    }


    // 分页显示费用
    @Test
    public void pageFee() throws Exception {
        QueryLossesVo vo = new QueryLossesVo();
        vo.setParams(Maps.newConcurrentMap());

        doPost(vo, "/losses/pageView");
    }
}
