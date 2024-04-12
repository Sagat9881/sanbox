package ru.apzakharov.abstract_crud.converters;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.core.convert.converter.Converter;

import javax.annotation.Nullable;
import java.text.ParseException;
import java.util.Date;


public class StringToDateConverter implements Converter<String, Date> {
    private static final String[] PATTERNS = new String[]{
            "yyyy-MM-dd",
            "yyyyMMdd",
            "yyyy-MM-dd'T'HH:mm:ss.SSS",
            "yyyy-MM-dd'T'HH:mm:ss",
            "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
            "yyyy-MM-dd' 'HH:mm:ss.SSS'Z'",
            "yyyy-MM-dd' 'HH:mm:ss",
            "dd.MM.yyyy",
            "dd.MM.yyyy'T'HH:mm:ss.SSS",
            "dd.MM.yyyy'T'HH:mm:ss.SSS'Z'",
            "dd.MM.yyyy' 'HH:mm:ss.SSS'Z'",
            "dd.MM.yyyy' 'HH:mm:ss",
    };

    @Override
    @Nullable
    public Date convert(String source) {
        try {
            //реализация через просто parseDate к примеру для 2017.19.24 предлагает успешную конвертацию в JulianCalendar
            return source.isEmpty() ? null : DateUtils.parseDateStrictly(source, PATTERNS);
        } catch (ParseException e) {
            throw new CannotConvertException("Невозможно преобразовать в значение даты: '" + source + "'");
        }
    }
}
