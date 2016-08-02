package com.wlxk;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wlxk.controller.sys.vo.role.AddRoleVo;
import com.wlxk.controller.sys.vo.user.AddUserVo;
import com.wlxk.controller.tradebill.vo.*;
import com.wlxk.domain.sys.*;
import com.wlxk.domain.tradebill.Goods;
import com.wlxk.domain.tradebill.Losses;
import com.wlxk.domain.tradebill.TradeBill;
import org.junit.Test;

/**
 * Created by Administrator on 2016/7/31.
 */
public class JsonTest {
    private String obj2Json(Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }

    @Test
    public void 开单 () throws JsonProcessingException {
        String json = obj2Json(new OpenTradeBillVo());
        System.out.println(json);

        String json2 = obj2Json(new TradeBill());
        System.out.println(json2);

        String json3 = obj2Json(new Goods());
        System.out.println(json3);

        String json4 = obj2Json(new Losses());
        System.out.println(json4);
    }

    @Test
    public void 交易单审核() throws Exception {
        String json = obj2Json(new ReviewTradeBillVo());
        System.out.println(json);
    }

    @Test
    public void 更新交易单() throws Exception {
        String json = obj2Json(new UpdateTradeBillVo());
        System.out.println(json);
    }

    @Test
    public void 作废交易单() throws  Exception {
        String json = obj2Json(new DisuseTradeBillVo());
        System.out.println(json);
    }

    @Test
    public void 分页交易单视图() throws Exception {
        String json = obj2Json(new QueryTradeBillVo());
        System.out.println(json);
    }

    @Test
    public void 新增菜单() throws Exception {
        String json = obj2Json(new Menu());
        System.out.println(json);
    }

    @Test
    public void 新增角色() throws Exception {
        System.out.println(obj2Json(new AddRoleVo()));
        System.out.println();
        System.out.println(obj2Json(new Role()));
        System.out.println(obj2Json(new RoleMenu()));
    }


    @Test
    public void 新增用户() throws Exception {
        System.out.println(obj2Json(new AddUserVo()));
        System.out.println(obj2Json(new User()));
        System.out.println(obj2Json(new UserRole()));
    }
}
