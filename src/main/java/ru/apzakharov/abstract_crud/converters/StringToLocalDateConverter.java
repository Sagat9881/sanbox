package ru.apzakharov.abstract_crud.converters;

import org.springframework.core.convert.converter.Converter;
import ru.diasoft.digitalq.logging.DSLogManager;
import ru.diasoft.digitalq.logging.DSLogger;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * User: ibalekseev
 * Date: 27.04.2015
 * Time: 17:48
 */
public class StringToLocalDateConverter implements Converter<String, LocalDate> {
    private static final DSLogger LOGGER = DSLogManager.getLogger(StringToLocalDateConverter.class);

    @Override
    public LocalDate convert(String source) {
        if (source == null || source.isEmpty())
            return null;
        try {
            return LocalDate.parse(source, DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (Exception e) {
            LOGGER.info(e);
        }
        try {
            return LocalDate.parse(source, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        } catch (Exception e) {
            LOGGER.info(e);
        }
        try {
            return LocalDate.parse(source, DateTimeFormatter.ofPattern("yyyyMMdd"));
        } catch (Exception e) {
            LOGGER.info(e);
        }
        try {
            LocalDateTime convert = new StringToLocalDateTimeConverter().convert(source);
            return convert == null ? null : convert.toLocalDate();
        } catch (Exception e) {
            LOGGER.info(e);
            throw new CannotConvertException("Невозможно преобразовать в значение даты: '" + source + "'");
        }
    }
}
