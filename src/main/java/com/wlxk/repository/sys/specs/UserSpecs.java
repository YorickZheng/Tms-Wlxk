package com.wlxk.repository.sys.specs;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.wlxk.domain.sys.Role;
import com.wlxk.domain.sys.User;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.Iterables.toArray;

/**
 * Created by malin on 2016/7/29.
 */
public class UserSpecs {
    public static Specification<User> userPageSpecs(Map<String, Object> params) {
        return new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = Lists.newArrayList();
                String username = (String) params.get("username");
                if (!Strings.isNullOrEmpty(username)) {
                    predicates.add(cb.like(root.get("username"), pattern(username)));
                }
                String name = (String) params.get("name");
                if (!Strings.isNullOrEmpty(name)) {
                    predicates.add(cb.like(root.get("name"), pattern(name)));
                }
                return cb.and(toArray(predicates, Predicate.class));
            }
        };
    }

    static private String pattern(String str) {
        return "%" + str + "%";
    }
}
