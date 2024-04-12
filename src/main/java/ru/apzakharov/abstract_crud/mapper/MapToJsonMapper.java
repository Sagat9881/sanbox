package ru.apzakharov.abstract_crud.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MapToJsonMapper {

    public Map<String, Object> toMap(String text) throws JsonProcessingException {
        Map<String, Object> map = new HashMap<>();
        if (StringUtils.isNotBlank(text)) {
            map = new ObjectMapper().readValue(text, new TypeReference<Map<String, Object>>() {
            });
        }
        return map;
    }

    public String fromMap(Map<String, Object> map) throws JsonProcessingException {
        if (map != null) {
            return new ObjectMapper().writeValueAsString(map);
        }
        return null;
    }
}
