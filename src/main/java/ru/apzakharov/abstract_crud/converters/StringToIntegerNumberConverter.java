package ru.apzakharov.abstract_crud.converters;

import com.google.common.collect.ImmutableSet;
import org.springframework.core.convert.TypeDescriptor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Set;

public class StringToIntegerNumberConverter extends StringToNumberConverter {

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        return ImmutableSet.of(new ConvertiblePair(String.class, Long.class),
                new ConvertiblePair(String.class, Integer.class),
                new ConvertiblePair(String.class, BigInteger.class));
    }

    @Override
    protected Number getValue(@NotNull TypeDescriptor targetType, String s) {
        try {
            BigDecimal val = new BigDecimal(s).stripTrailingZeros();

            if (val.scale() > 0) {
                return null;
            }

            if (targetType.getType() == Long.class || targetType.getType() == long.class) {
                return val.longValue();
            } else if (targetType.getType() == Integer.class || targetType.getType() == int.class) {
                return val.intValue();
            }

            return val.toBigInteger();
        } catch (NumberFormatException e) {
            return null;
        }
    }
}