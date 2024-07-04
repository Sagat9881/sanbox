package ru.apzakharov.abstract_crud.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.JSONB;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SerializationUtil {

  private final ObjectMapper objectMapper;

  public String serialize(Object object) {
    String result;
    try {
      result = objectMapper.writeValueAsString(object);
    } catch (JsonProcessingException e) {
      log.error("Serialization error", e);
      throw new RuntimeException(e);
    }
    return result;
  }

  public <T> T deserialize(String json, Class<T> clazz) {
    T result;
    try {
      result = objectMapper.readValue(json, clazz);
    } catch (JsonProcessingException e) {
      log.error("Deserialization error {}", json, e);
      throw new RuntimeException(e);
    }
    return result;
  }


  public JsonNode convert(JSONB jsonb) {
    try {
      return objectMapper.readTree(jsonb.data());
    } catch (JsonProcessingException e) {
      log.error("Can't convert jsonb to JsonNode");
      throw new RuntimeException(e);
    }
  }
}
