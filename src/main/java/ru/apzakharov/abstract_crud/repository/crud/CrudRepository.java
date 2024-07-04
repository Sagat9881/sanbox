package ru.apzakharov.abstract_crud.repository.crud;

import org.jooq.UpdatableRecord;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.apzakharov.abstract_crud.repository.entity.EntityWithId;

import java.util.List;

public interface CrudRepository<ID, ENTITY extends EntityWithId<ID>> {
    @Transactional(propagation = Propagation.REQUIRED)
    ENTITY save(ENTITY ENTITY);

    @Transactional(propagation = Propagation.REQUIRED)
    void saveAll(List<ENTITY> ENTITY);

    @Transactional(propagation = Propagation.REQUIRED)
    ENTITY update(ID id, ENTITY ENTITY);

    @Transactional(propagation = Propagation.REQUIRED)
    void upsert(List<ENTITY> ENTITY);

    @Transactional(propagation = Propagation.REQUIRED)
    void delete(ID id);

    @Transactional(readOnly = true)
    ENTITY getById(ID id);

    @Transactional(readOnly = true)
    List<ENTITY> getAll();
//    List<DTO> getList(Predicate )
}
