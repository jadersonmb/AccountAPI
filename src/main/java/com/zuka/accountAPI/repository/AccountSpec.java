package com.zuka.accountAPI.repository;

import com.zuka.accountAPI.dto.AccountDTO;
import com.zuka.accountAPI.model.Account;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AccountSpec {
    public static Specification<Account> searchDesc(AccountDTO filter) {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (Objects.nonNull(filter.getName()) && !filter.getName().isEmpty()) {
                predicates.add(builder.like(builder.lower(root.<String>get("name")),
                        "%".concat(filter.getName().toLowerCase()).concat("%")));
            }
            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
