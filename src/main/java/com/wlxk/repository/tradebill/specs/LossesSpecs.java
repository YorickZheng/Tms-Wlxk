package com.wlxk.repository.tradebill.specs;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.wlxk.domain.tradebill.Losses;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.Iterables.toArray;

/**
 * Created by 马林 on 2016/8/27.
 */
public class LossesSpecs {
    public static Specification<Losses> lossesPageSpecs(Map<String, Object> params) {
        return new Specification<Losses>() {
            @Override
            public Predicate toPredicate(Root<Losses> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = Lists.newArrayList();
                String tradeBillNo = (String) params.get("tradeBillNo");
                if (!Strings.isNullOrEmpty(tradeBillNo)) {
                    predicates.add(cb.like(root.get("tradeBillNo"), pattern(tradeBillNo)));
                }
                return cb.and(toArray(predicates, Predicate.class));
            }
        };
    }

    static private String pattern(String str) {
        return "%" + str + "%";
    }
}
