package ru.apzakharov.application.service.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import ru.apzakharov.abstract_crud.config.MappingConfiguration;
import ru.apzakharov.abstract_crud.controller.dto.DemoDto;
import ru.apzakharov.abstract_crud.service.mapper.ServiceMapper;
import ru.apzakharov.application.repository.entity.DemoEntity;

@Mapper(config = MappingConfiguration.class)
@Component
public interface DemoServiceMapper extends ServiceMapper<DemoDto, DemoEntity> {
}
