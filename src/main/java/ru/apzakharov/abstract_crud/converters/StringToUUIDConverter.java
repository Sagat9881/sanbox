package ru.apzakharov.abstract_crud.converters;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class StringToUUIDConverter implements Converter<String, UUID> {
    @Override
    public UUID convert(String s) {
        if (StringUtils.isBlank(s))
            return null;
        return UUID.fromString(s);
    }
}
