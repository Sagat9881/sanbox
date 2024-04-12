package ru.apzakharov.abstract_crud.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UUIDToStringConverter implements Converter<UUID, String> {
    @Override
    public String convert(UUID uuid) {
        if (uuid == null)
            return null;
        return uuid.toString();
    }
}
