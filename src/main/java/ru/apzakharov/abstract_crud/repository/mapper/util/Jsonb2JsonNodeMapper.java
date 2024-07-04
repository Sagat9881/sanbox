package ru.apzakharov.abstract_crud.repository.mapper.util;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.log4j.Log4j2;
import org.jooq.JSONB;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import ru.apzakharov.abstract_crud.config.MappingConfiguration;
import ru.apzakharov.abstract_crud.util.SerializationUtil;

import java.util.Optional;
import java.util.function.Function;

@Log4j2
@Mapper(config = MappingConfiguration.class)
public abstract class Jsonb2JsonNodeMapper implements Function<JSONB, JsonNode> {

  @Autowired
  private SerializationUtil serializationUtil;

  @Override
  public JsonNode apply(JSONB src) {
    return Optional.ofNullable(src)
        .map(it -> serializationUtil.convert(it))
        .orElse(null);
  }
}