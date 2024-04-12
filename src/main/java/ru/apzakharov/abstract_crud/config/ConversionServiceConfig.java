package ru.apzakharov.abstract_crud.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.format.support.DefaultFormattingConversionService;
import ru.apzakharov.abstract_crud.converters.*;
import ru.apzakharov.abstract_crud.converters.jpa.IdToJpaReferenceConverterFactory;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ConversionServiceConfig {
    @Autowired(required = false)
    private List<? extends JpaRepository> repositories = new ArrayList<>();

    @Bean("predicateConversionService")
    public ConversionService predicateConversionService() {
        DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();

        conversionService.addConverter(new StringToLocalDateConverter());
        conversionService.addConverter(new StringToLocalDateTimeConverter());
        conversionService.addConverter(new StringToDateConverter());
        conversionService.addConverter(new LocalDateToISOStringConverter());
        conversionService.addConverter(new LocalDateTimeToISOStringConverter());

        conversionService.addConverter(new StringToFractionalNumberConverter());

        conversionService.addConverter(new StringToIntegerNumberConverter());

        conversionService.addConverterFactory(new StringToStringCodeEnumConverterFactory());

        conversionService.addConverterFactory(new IdToJpaReferenceConverterFactory(conversionService, repositories));
        return conversionService;
    }
}
