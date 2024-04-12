package ru.apzakharov.abstract_crud.mapper;

import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Persistable;

import java.io.Serializable;
import java.util.Collection;

public interface ExtendedEntityMapper<E extends Persistable<I>, I extends Serializable, D> {

    E dtoToEntity(D dto);

    D entityToDto(E entity);

    Collection<E> toEntityCollection(Collection<D> dtoList);

    Collection<D> toDtoCollection(Collection<E> entityList);

    @Mapping(target = "id", ignore = true)
    E update(D dto, @MappingTarget E entity);

}

