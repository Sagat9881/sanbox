package ru.apzakharov.abstract_crud.mapper;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.mapstruct.TargetType;
import org.springframework.data.domain.Persistable;
import org.springframework.stereotype.Component;
import ru.apzakharov.abstract_crud.domain.base.EntityWithId;

import java.io.Serializable;
import java.util.Optional;

@Component
public class ReferenceMapper {

    @PersistenceContext
    private EntityManager entityManager;

    public <ID extends Serializable,T extends EntityWithId<ID>> T mapToReference(final ID id, @TargetType Class<T> type) {
        return id == null ? null : entityManager.getReference(type, id);
    }

    public <ID extends Serializable> ID mapToId(EntityWithId<ID> entity) {
        return entity != null ? entity.getId() : null;
    }

    public <ID extends Serializable> ID mapToId(Optional<? extends EntityWithId<ID>> entity) {
        return entity.map(Persistable::getId).orElse(null);
    }

    //todo write separate methods if other type of Id required (like uuid)
}
