package ru.apzakharov.abstract_crud.service.crud;

public interface CrudService <DTO, ID>{
     ID add( DTO dto);
     DTO get( ID id);
     void update( DTO dto,  ID id);
     void delete( ID id);

}
