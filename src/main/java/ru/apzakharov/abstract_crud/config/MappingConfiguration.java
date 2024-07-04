package ru.apzakharov.abstract_crud.config;

import org.mapstruct.MapperConfig;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.extensions.spring.SpringMapperConfig;

@SpringMapperConfig(conversionServiceBeanName = "mvcConversionService")
@MapperConfig(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface MappingConfiguration {

}