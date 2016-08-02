package com.wlxk.repository.tradebill.specs;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.wlxk.domain.sys.Menu;
import com.wlxk.domain.tradebill.TradeBill;
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
public class TradeBillSpecs {
    public static Specification<TradeBill> tradeBillPageSpecs(Map<String, Object> params) {
        return new Specification<TradeBill>() {
            @Override
            public Predicate toPredicate(Root<TradeBill> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = Lists.newArrayList();
                String name = (String) params.get("tradeBillNo");
                if (!Strings.isNullOrEmpty(name)) {
                    predicates.add(cb.like(root.get("tradeBillNo"), pattern(name)));
                }
                return cb.and(toArray(predicates, Predicate.class));
            }
        };
    }

    static private String pattern(String str) {
        return "%" + str + "%";
    }
}
