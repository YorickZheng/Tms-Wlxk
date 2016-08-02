package com.wlxk.repository.branch.specs;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.wlxk.domain.branch.Branch;
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
public class BranchSpecs {
    public static Specification<Branch> branchPageSpecs(Map<String, Object> params) {
        return new Specification<Branch>() {
            @Override
            public Predicate toPredicate(Root<Branch> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = Lists.newArrayList();
                String branchNo = (String) params.get("branchNo");
                if (!Strings.isNullOrEmpty(branchNo)) {
                    predicates.add(cb.like(root.get("branchNo"), pattern(branchNo)));
                }
                String branchName = (String) params.get("branchName");
                if (!Strings.isNullOrEmpty(branchName)) {
                    predicates.add(cb.like(root.get("branchName"), pattern(branchName)));
                }
                return cb.and(toArray(predicates, Predicate.class));
            }
        };
    }

    static private String pattern(String str) {
        return "%" + str + "%";
    }
}
