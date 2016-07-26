package com.wlxk;

import com.google.common.collect.Lists;
import com.wlxk.domain.tradebill.TradeBill;
import com.wlxk.repository.sys.UserRepository;
import com.wlxk.repository.tradebill.TradeBillRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TmsApplication.class)
@WebAppConfiguration
public class TmsApplicationTests {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TradeBillRepository tradeBillRepository;

    @Test
    public void contextLoads() {
    }

    @Test
    public void tradeBillTest() {
        List<TradeBill> list = Lists.newArrayList();
        for (int i = 0; i < 2000000; i++) {
            TradeBill t = new TradeBill();
            t.setTradeBillNo("T_0000" + i);
            list.add(t);
        }
        tradeBillRepository.save(list);
    }

    @Test
    public void tradeBillTest2() {
        List<TradeBill> list = Lists.newArrayList();
        for (int i = 2000000; i < 4000000; i++) {
            TradeBill t = new TradeBill();
            t.setTradeBillNo("T_0000" + i);
            list.add(t);
        }
        tradeBillRepository.save(list);
    }

    @Test
    public void tradeBillTest3() {
        List<TradeBill> list = Lists.newArrayList();
        for (int i = 4000000; i < 6000000; i++) {
            TradeBill t = new TradeBill();
            t.setTradeBillNo("T_0000" + i);
            list.add(t);
        }
        tradeBillRepository.save(list);
    }

    @Test
    public void tradeBillTest4() {
        List<TradeBill> list = Lists.newArrayList();
        for (int i = 6000000; i < 8000000; i++) {
            TradeBill t = new TradeBill();
            t.setTradeBillNo("T_0000" + i);
            list.add(t);
        }
        tradeBillRepository.save(list);
    }

    @Test
    public void tradeBillTest5() {
        List<TradeBill> list = Lists.newArrayList();
        for (int i = 8000000; i < 100000000; i++) {
            TradeBill t = new TradeBill();
            t.setTradeBillNo("T_0000" + i);
            list.add(t);
        }
        tradeBillRepository.save(list);
    }


}
