package ru.apzakharov.abstract_crud.converters;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * User: ibalekseev
 * Date: 27.04.2015
 * Time: 17:48
 */
public class StringToLocalDateConverter implements Converter<String, LocalDate> {

    @Override
    public LocalDate convert(String source) {
        if (source == null || source.isEmpty())
            return null;
        try {
            return LocalDate.parse(source, DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (Exception e) {

        }
        try {
            return LocalDate.parse(source, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        } catch (Exception e) {

        }
        try {
            return LocalDate.parse(source, DateTimeFormatter.ofPattern("yyyyMMdd"));
        } catch (Exception e) {

        }
        try {
            LocalDateTime convert = new StringToLocalDateTimeConverter().convert(source);
            return convert == null ? null : convert.toLocalDate();
        } catch (Exception e) {

            throw new CannotConvertException("Невозможно преобразовать в значение даты: '" + source + "'");
        }
    }
}
