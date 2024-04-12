package ru.apzakharov.abstract_crud.mapper;

interface EntityMapper<T, D> {

    D entityToDto(T t);

    T dtoToEntity(D d);

}
