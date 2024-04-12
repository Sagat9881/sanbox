package ru.apzakharov.abstract_crud.converters;

import com.google.common.collect.ImmutableSet;
import org.springframework.core.convert.TypeDescriptor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.Set;

public class StringToFractionalNumberConverter extends StringToNumberConverter {

    private static final int MAX_SCALE = 18;        // MSSQL

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        return ImmutableSet.of(new ConvertiblePair(String.class, Double.class),
                new ConvertiblePair(String.class, Float.class),
                new ConvertiblePair(String.class, BigDecimal.class));
    }

    @Override
    protected Number getValue(@NotNull TypeDescriptor targetType, String s) {
        try {
            BigDecimal val = new BigDecimal(s).stripTrailingZeros();

            if (val.scale() > MAX_SCALE) {
                String message = MessageFormat.format("Точность числа {0} превышает максимально допустимую - {1}", val.toString(), MAX_SCALE);
                throw new CannotConvertException(message);
            }

            if (targetType.getType() == Double.class || targetType.getType() == double.class) {
                return val.doubleValue();
            } else if (targetType.getType() == Float.class || targetType.getType() == float.class) {
                return val.floatValue();
            }

            return new BigDecimal(val.toPlainString());     // (new BigDecimal("1E+2")).equals(new BigDecimal("100")) = false
        } catch (NumberFormatException e) {
            return null;
        }
    }
}