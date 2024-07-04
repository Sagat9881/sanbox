package ru.apzakharov.abstract_crud.repository.mapper.util;

import org.jooq.JSONB;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import ru.apzakharov.abstract_crud.config.MappingConfiguration;
import ru.apzakharov.abstract_crud.util.SerializationUtil;

import java.util.function.Function;

@Mapper(config = MappingConfiguration.class)
public abstract class Object2JsonbMapper implements Function<Object, JSONB> {

  @Autowired
  private SerializationUtil util;

  @Override
  public JSONB apply(Object src) {
    return JSONB.valueOf(util.serialize(src));
  }
}