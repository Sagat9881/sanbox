package ru.apzakharov.abstract_crud.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

import javax.annotation.Nonnull;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateToISOStringConverter implements Converter<LocalDate, String> {
    @Nullable
    @Override
    public String convert(@Nonnull LocalDate source) {
        return source.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }
}
