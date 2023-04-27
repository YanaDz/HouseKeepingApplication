package com.dziadkouskaya.housekeeping.entity.enumerations;

import com.dziadkouskaya.housekeeping.exception.ConversionException;
import jakarta.persistence.AttributeConverter;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import static com.dziadkouskaya.housekeeping.utils.Constants.ENUM_EXCEPTION_MESSAGE;
import static java.util.Objects.isNull;
import static java.util.stream.Collectors.toMap;

public abstract class EnumConverter<E extends BaseEnum<E>> implements AttributeConverter<E, Integer> {

    private final Map<Integer, E> enumMappings;
    private final String enumName;

    protected EnumConverter(Class<E> clazz) {
        enumName = clazz.getSimpleName();
        enumMappings = buildEnumMappings(clazz);
    }

    @Override
    public final Integer convertToDatabaseColumn(E attribute) {
        return isNull(attribute) ? null : attribute.getCode();
    }

    @Override
    public final E convertToEntityAttribute(Integer dbData) {
        if (isNull(dbData)) {
            return null;
        }
        return Optional.ofNullable(enumMappings.get(dbData))
            .orElseThrow(() -> new ConversionException(String.format(ENUM_EXCEPTION_MESSAGE, enumName, dbData)));
    }

    private Map<Integer, E> buildEnumMappings(Class<E> clazz) {
        return Arrays.stream(clazz.getEnumConstants())
            .collect(toMap(BaseEnum::getCode, Function.identity()));
    }
}
