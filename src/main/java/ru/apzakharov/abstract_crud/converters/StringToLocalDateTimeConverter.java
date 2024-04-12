package ru.apzakharov.abstract_crud.converters;

import com.google.common.collect.ImmutableList;
import org.springframework.core.convert.converter.Converter;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * User: ibalekseev
 * Date: 27.04.2015
 * Time: 17:48
 */
public class StringToLocalDateTimeConverter implements Converter<String, LocalDateTime> {
    private static final List<DateTimeFormatter> OFFSET_FORMATERS = ImmutableList.of(
            DateTimeFormatter.ISO_OFFSET_DATE_TIME,
            DateTimeFormatter.ofPattern("yyyy-MM-dd' 'HH:mm:ssZ"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ"),
            DateTimeFormatter.ofPattern("dd.MM.yyyy' 'HH:mm:ssZ"),
            DateTimeFormatter.ofPattern("dd.MM.yyyy'T'HH:mm:ssZ")
    );
    private static final List<DateTimeFormatter> LOCAL_FORMATERS = ImmutableList.of(
            DateTimeFormatter.ISO_LOCAL_DATE_TIME,
            DateTimeFormatter.ofPattern("yyyy-MM-dd' 'HH:mm:ss"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"),
            DateTimeFormatter.ofPattern("dd.MM.yyyy' 'HH:mm:ss"),
            DateTimeFormatter.ofPattern("dd.MM.yyyy'T'HH:mm:ss")

    );

    @Override
    public LocalDateTime convert(String source) {
        if (source == null || source.isEmpty())
            return null;
        for (DateTimeFormatter formatter : OFFSET_FORMATERS) {
            try {
                return OffsetDateTime.parse(source, formatter).atZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();
            } catch (Exception ignored) {
            }
        }
        for (DateTimeFormatter formatter : LOCAL_FORMATERS) {
            try {
                return LocalDateTime.parse(source, formatter);
            } catch (Exception ignored) {
            }
        }
        throw new CannotConvertException("Невозможно преобразовать значение к типу дата со временем: " + source);
    }
}
