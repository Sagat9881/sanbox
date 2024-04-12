package ru.apzakharov.abstract_crud.converters.jpa;

import com.google.common.collect.ImmutableMap;
import jakarta.persistence.Id;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.core.GenericTypeResolver;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalConverter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * User: ialekseev
 * Date: 28.04.2017
 * Time: 15:13
 */
public class IdToJpaReferenceConverterFactory implements ConverterFactory<Object, Object>, ConditionalConverter {

    private Map<Class, JpaRepository> repositoryMap;
    private Map<Class, Field> idFieldMap;
    private final ConversionService conversionService;

    private List<? extends JpaRepository> repositories = new ArrayList<>();

    public IdToJpaReferenceConverterFactory(ConversionService conversionService, List<? extends JpaRepository> repositories) {
        this.conversionService = conversionService;
        this.repositories = repositories;
        init();
    }

    private void init() {
        ImmutableMap.Builder<Class, JpaRepository> repositoryBuilder = new ImmutableMap.Builder<>();
        ImmutableMap.Builder<Class, Field> idFieldBuilder = new ImmutableMap.Builder<>();
        for (JpaRepository repository : repositories) {
            Class entityClass = GenericTypeResolver.resolveTypeArguments(repository.getClass(), JpaRepository.class)[0];

            List<Field> ids = FieldUtils.getFieldsListWithAnnotation(entityClass, Id.class);
            if (ids.size() == 1) {
                repositoryBuilder.put(entityClass, repository);
                idFieldBuilder.put(entityClass, ids.get(0));
            }
        }
        repositoryMap = repositoryBuilder.build();
        idFieldMap = idFieldBuilder.build();
    }

    @Override
    public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {
        if (repositoryMap.get(targetType.getType()) == null) {
            return false;
        }

        return conversionService.canConvert(sourceType, new TypeDescriptor(idFieldMap.get(targetType.getType())));
    }

    @Override
    public <T> Converter<Object, T> getConverter(Class<T> targetType) {
        JpaRepository repository = repositoryMap.get(targetType);
        return new IdToJpaReferenceConverter<T>(repository, conversionService, idFieldMap.get(targetType));
    }

    private static class IdToJpaReferenceConverter<T> implements Converter<Object, T> {
        private final JpaRepository<T, Serializable> repository;
        private final ConversionService conversionService;
        private final Field idField;

        IdToJpaReferenceConverter(JpaRepository<T, Serializable> repository, ConversionService conversionService, Field idField) {
            this.repository = repository;
            this.conversionService = conversionService;
            this.idField = idField;
        }

        @Override
        public T convert(Object source) {
            if (source instanceof String && "null".equals(source)) {
                return null;
            }

            Serializable id = (Serializable) conversionService.convert(source, idField.getType());
            T entity = null;
            if (id != null)
                entity = repository.findById(id).orElse(null);
            return entity;
        }
    }
}
