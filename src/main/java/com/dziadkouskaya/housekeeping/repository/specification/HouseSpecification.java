package com.dziadkouskaya.housekeeping.repository.specification;

import com.dziadkouskaya.housekeeping.entity.House;
import com.dziadkouskaya.housekeeping.entity.House_;
import com.dziadkouskaya.housekeeping.entity.enumerations.SearchType;
import io.micrometer.common.util.StringUtils;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.function.Function;
import java.util.stream.Stream;

import static com.dziadkouskaya.housekeeping.entity.enumerations.SearchType.ANY_MATCH;
import static com.dziadkouskaya.housekeeping.repository.specification.SpecificationUtils.buildOrSpecification;
import static com.dziadkouskaya.housekeeping.repository.specification.SpecificationUtils.conjunction;
import static java.util.Arrays.stream;
import static java.util.List.of;
import static lombok.AccessLevel.PRIVATE;
import static org.hibernate.internal.util.StringHelper.isBlank;

@NoArgsConstructor(access = PRIVATE)
public class HouseSpecification {

    public static <T extends House> Specification<T> search(String search) {
        if (isBlank(search)) {
            return conjunction();
        }
        Stream<Specification<T>> specification = toSpecificationStream(search);
        return specification.reduce(conjunction(), Specification::and);
    }

    public static <T extends House> Specification<T> fullSearch(String search) {
        Specification<T> name = searchByName(search, ANY_MATCH);
        Specification<T> address = searchByAddress(search, ANY_MATCH);
        var specs = of(
            name,
            address
        );
        return buildOrSpecification(specs);
    }

    public static <T extends House> Specification<T> searchByName(String search, SearchType searchType) {
        return getHouseSearch(House_.NAME, search, searchType);
    }

    public static <T extends House> Specification<T> searchByAddress(String search, SearchType searchType) {
        return getHouseSearch(House_.ADDRESS, search, searchType);
    }

    private static <T extends House> Specification<T> getHouseSearch(String attribute, String search, SearchType searchType) {
        return (root, query, builder) -> builder.like(builder.lower(root.get(attribute)), searchType.format(search.toLowerCase()));
    }


    private static <T extends House> Stream<Specification<T>> toSpecificationStream(String search) {
        return stream(search.split(" "))
            .filter(StringUtils::isNotBlank)
            .map(tokenMatches());
    }

    private static <T extends House> Function<String, Specification<T>> tokenMatches() {
        return HouseSpecification::fullSearch;
    }

}
