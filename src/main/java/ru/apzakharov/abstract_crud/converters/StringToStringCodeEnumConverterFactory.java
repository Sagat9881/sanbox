package ru.apzakharov.abstract_crud.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.isBlank;


public class StringToStringCodeEnumConverterFactory implements ConverterFactory<String, StringCodeEnum> {

    @Override
    public <T extends StringCodeEnum> Converter<String, T> getConverter(Class<T> targetType) {
        return new StringToStringCodeEnum(getEnumType(targetType));
    }


    private static class StringToStringCodeEnum<T extends Enum & StringCodeEnum> implements Converter<String, T> {
        private final Map<String, T> enumMap;

        public StringToStringCodeEnum(Class<T> enumType) {
            enumMap = new HashMap<>();
            for (T t : enumType.getEnumConstants()) {
                enumMap.put(t.getCode(), t);
            }
        }

        @Override
        public T convert(String source) {
            if (isBlank(source)) {
                return null;
            }
            return enumMap.get(source.trim());
        }
    }

    private static Class<?> getEnumType(Class<?> targetType) {
        Class<?> enumType = targetType;
        while (enumType != null && !enumType.isEnum()) {
            enumType = enumType.getSuperclass();
        }
        Assert.notNull(enumType, () -> "The target type " + targetType.getName() + " does not refer to an enum");
        return enumType;
    }

}
