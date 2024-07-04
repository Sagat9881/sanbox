package ru.apzakharov.application.repository.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import ru.apzakharov.abstract_crud.config.MappingConfiguration;
import ru.apzakharov.abstract_crud.repository.mapper.DomainMapper;
import ru.apzakharov.application.repository.entity.DemoEntity;
import ru.apzakharov.jooq.tables.records.DemoRecord;

@Mapper(config = MappingConfiguration.class)
@Component
public interface DemoMapper extends DomainMapper<DemoRecord, DemoEntity> {
}
