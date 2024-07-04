package ru.apzakharov.abstract_crud.service.mapper;

public interface ServiceMapper <DTO,ENTITY> {
    ENTITY toEntity(DTO dto);
    DTO toDTO(ENTITY entity);
}
