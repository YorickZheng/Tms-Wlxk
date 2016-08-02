package com.wlxk.repository.contract.specs;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.wlxk.domain.contract.Contract;
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
public class ContractSpecs {
    public static Specification<Contract> contractPageSpecs(Map<String, Object> params) {
        return new Specification<Contract>() {
            @Override
            public Predicate toPredicate(Root<Contract> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = Lists.newArrayList();
                String contractNo = (String) params.get("contractNo");
                if (!Strings.isNullOrEmpty(contractNo)) {
                    predicates.add(cb.like(root.get("contractNo"), pattern(contractNo)));
                }
                return cb.and(toArray(predicates, Predicate.class));
            }
        };
    }

    static private String pattern(String str) {
        return "%" + str + "%";
    }
}
