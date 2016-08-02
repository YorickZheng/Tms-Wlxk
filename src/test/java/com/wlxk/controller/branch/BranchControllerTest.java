package com.wlxk.controller.branch;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wlxk.TmsApplication;
import com.wlxk.controller.branch.vo.*;
import com.wlxk.domain.branch.Branch;
import com.wlxk.domain.branch.BranchBankCard;
import com.wlxk.domain.branch.BranchLinkman;
import com.wlxk.repository.branch.BranchRepository;
import com.wlxk.support.util.CommonProperty;
import org.hibernate.mapping.List;
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
public class BranchControllerTest {
    @Autowired
    BranchRepository repository;

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
        Branch branch = new Branch();
        branch.setBranchNo("B____0001");
        branch.setBranchName("测试网点");

        BranchBankCard bankCard1 = new BranchBankCard();
        bankCard1.setBankNo("111");
        BranchBankCard bankCard2 = new BranchBankCard();
        bankCard2.setBankNo("222");

        BranchLinkman linkman1 = new BranchLinkman();
        linkman1.setLinkman("联系人1");
        BranchLinkman linkman2 = new BranchLinkman();
        linkman2.setLinkman("联系人2");

        AddBranchVo vo = new AddBranchVo();
        vo.setBranch(branch);
        vo.setBranchBankCardList(Lists.newArrayList(bankCard1, bankCard2));
        vo.setBranchLinkmanList(Lists.newArrayList(linkman1, linkman2));
        vo.setOperationById("123456");
        vo.setOperationByName("马林");
        vo.setDescription("马林测试新增网点");


        doPost(vo, "/branch/add");
    }

    @Test
    public void testDisuse() throws Exception {
        DisuseBranchVo vo = new DisuseBranchVo();
        vo.setBusinessId("402883a2563c724401563c724eef0000");
        vo.setOperationById("123456");
        vo.setOperationByName("马林");
        //vo.setDescription("马林测试作废网点");
        //vo.setCommand(CommonProperty.DisuseCommand.DISUSE);
        vo.setDescription("马林测试取消作废网点");
        vo.setCommand(CommonProperty.DisuseCommand.DISUSE_CANCEL);


        doPost(vo, "/branch/disuse");
    }

    @Test
    public void testReview() throws Exception {
        ReviewBranchVo vo = new ReviewBranchVo();
        vo.setBusinessId("402883a2563c724401563c724eef0000");
        vo.setOperationById("123456");
        vo.setOperationByName("马林");
        vo.setDescription("马林测试审核网点");
        vo.setCommand(CommonProperty.ReviewCommand.AUDITED_COMMAND);
        //vo.setDescription("马林测试取消审核网点");
        //vo.setCommand(CommonProperty.ReviewCommand.CANNEL_COMMAND);

        doPost(vo, "/branch/review");
    }

    @Test
    public void testUpdate() throws Exception {
        Branch branch = repository.findOne("402883a2563c724401563c724eef0000");
        branch.setBranchNo("malinwangdian0001");
        branch.setBranchName("网点88888");

        UpdateBranchVo vo = new UpdateBranchVo();
        vo.setOperationById("123456");
        vo.setOperationByName("马林");
        vo.setDescription("马林测试修改网点");

        vo.setBranch(branch);


        doPost(vo, "/branch/update");

    }

    @Test
    public void testPageView() throws Exception {
        Map<String, Object> params = Maps.newConcurrentMap();
        params.put("branchNo", "malin");

        QueryBranchVo vo = new QueryBranchVo();
        vo.setParams(params);
        vo.setPage(1);

        doPost(vo, "/branch/getPageView");
    }
}
