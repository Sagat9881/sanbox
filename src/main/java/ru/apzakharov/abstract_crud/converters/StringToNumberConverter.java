package ru.apzakharov.abstract_crud.converters;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.lang.Nullable;

import java.util.Set;


/**
 * Created by Inna Spodarik on 25.08.17.
 */
public abstract class StringToNumberConverter implements GenericConverter {

    @Override
    public abstract Set<ConvertiblePair> getConvertibleTypes();

    @Override
    public Object convert(@Nullable Object source,  TypeDescriptor sourceType,  TypeDescriptor targetType) {
        String s = (String) source;
        if (StringUtils.isBlank(s)) {
            return null;
        }

        String formattedStr = s.replace(',', '.').replace(" ", "");
        Number val = getValue(targetType, formattedStr);
        if (val == null) {
            throw new CannotConvertException("Ошибка преобразования строки " + s + " в " + targetType.getName());
        }

        return val;
    }

    protected abstract Number getValue( TypeDescriptor sourceType, String s);

}
