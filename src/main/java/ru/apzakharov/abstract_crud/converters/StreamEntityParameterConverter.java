package ru.apzakharov.abstract_crud.converters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.lang3.StringUtils;
import ru.diasoft.micro.domain.StreamEntityParameter;
import ru.diasoft.micro.exceptions.ServiceException;

import javax.persistence.AttributeConverter;
import java.util.ArrayList;
import java.util.List;

/**
 * User: ialekseev
 * Date: 26.07.2021
 * Time: 9:48
 */
public class StreamEntityParameterConverter implements AttributeConverter<List<StreamEntityParameter>, String> {

    private static final ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper()
                .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .findAndRegisterModules();
    }

    @Override
    public String convertToDatabaseColumn(List<StreamEntityParameter> attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            throw new ServiceException("Ошибка сериализации", e);
        }
    }

    @Override
    public List<StreamEntityParameter> convertToEntityAttribute(String dbData) {
        if (StringUtils.isBlank(dbData)) {
            return new ArrayList<>();
        }
        try {
            return objectMapper.readValue(dbData, new TypeReference<List<StreamEntityParameter>>() {
            });
        } catch (Exception e) {
            throw new ServiceException("Ошибка десериализации", e);
        }
    }

}
