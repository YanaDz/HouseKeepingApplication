package com.dziadkouskaya.housekeeping.repository.specification;

import org.springframework.data.jpa.domain.Specification;

import java.util.Collection;

public class SpecificationUtils {
    public static <T> Specification<T> conjunction() {
        return (root, query, builder) -> builder.conjunction();
    }

    public static <T> Specification<T> disjunction() {
        return (root, query, builder) -> builder.disjunction();
    }

    public static <T> Specification<T> buildOrSpecification(Collection<Specification<T>> specs) {
        return specs.stream().reduce(disjunction(), Specification::or);
    }
}
