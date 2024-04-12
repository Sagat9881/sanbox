package ru.apzakharov.abstract_crud.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

import javax.annotation.Nonnull;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeToISOStringConverter implements Converter<LocalDateTime, String> {

    @Nullable
    @Override
    public String convert(@Nonnull LocalDateTime source) {
        return source.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }
}
