package com.wlxk.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import com.wlxk.TmsApplication;
import com.wlxk.domain.sys.Role;
import com.wlxk.repository.sys.RoleRepository;
import com.wlxk.repository.sys.specs.RoleSpecs;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TmsApplication.class)
@WebAppConfiguration
public class RoleRepositoryTest {
    @Autowired
    RoleRepository repository;

    private String obj2Json(Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }

    @Test
    public void test1() throws JsonProcessingException {
        Map<String, Object> params = Maps.newConcurrentMap();
        params.put("name", "角色1");

        Sort sort = new Sort(Sort.Direction.DESC, "name");
        Pageable pageable = new PageRequest(0, 10, sort);

        Page<Role> page = repository.findAll(RoleSpecs.rolePageSpecs(params), pageable);
        System.out.println("显示查询结果");
        System.out.println(obj2Json(page));
    }
}
