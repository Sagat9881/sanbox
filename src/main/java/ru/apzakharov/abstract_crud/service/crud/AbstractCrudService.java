package ru.apzakharov.abstract_crud.service.crud;

import ru.apzakharov.abstract_crud.repository.crud.CrudRepository;
import ru.apzakharov.abstract_crud.repository.entity.EntityWithId;
import ru.apzakharov.abstract_crud.service.mapper.ServiceMapper;

import java.io.Serializable;


public abstract class AbstractCrudService<DTO, ID extends Serializable, ENTITY extends EntityWithId<ID>>
        implements CrudService<DTO, ID> {

    protected final CrudRepository<ID, ENTITY> repository;
    private final ServiceMapper<DTO, ENTITY> mapper;

    public AbstractCrudService(CrudRepository<ID, ENTITY> repository, ServiceMapper<DTO, ENTITY> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public ID add(DTO dto) {
        ENTITY entity = mapper.toEntity(dto);
        return repository.save(entity).getId();
    }

    public DTO get(ID id) {
        return mapper.toDTO(repository.getById(id));
    }

    public void update(DTO dto, ID id) {
        repository.update(id, mapper.toEntity(dto));
    }

    public void delete(ID id) {
        repository.delete(id);
    }

}
