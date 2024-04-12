package ru.apzakharov.abstract_crud.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.data.convert.Jsr310Converters;

import java.util.List;

/**
 * User: ext_ibalekseev
 * Date: 10.02.2015
 * Time: 12:09
 */
public class DSConversionService extends DefaultConversionService {

    public DSConversionService() {
        Jsr310Converters.getConvertersToRegister()
                .forEach(this::addConverter);
    }

    public void setConverters(List<Converter> converters) {
        for (Converter converter : converters) {
            addConverter((Converter<?, ?>) converter);
        }
    }

    public void setGenericConverters(List<GenericConverter> converters) {
        for (GenericConverter converter : converters) {
            addConverter(converter);
        }
    }

    public void setConverterFactories(List<ConverterFactory<?, ?>> converterFactories) {
        for (ConverterFactory<?, ?> converterFactory : converterFactories) {
            addConverterFactory(converterFactory);
        }
    }
}
