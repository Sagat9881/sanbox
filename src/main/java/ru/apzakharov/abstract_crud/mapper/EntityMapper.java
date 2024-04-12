package ru.apzakharov.abstract_crud.mapper;

/**
 * User: ialekseev
 * Date: 21.12.2021
 * Time: 17:04
 */
interface EntityMapper<T, D> {

    D entityToDto(T t);

    T dtoToEntity(D d);

}
