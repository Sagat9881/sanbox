package ru.apzakharov.application.repository;

import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import ru.apzakharov.abstract_crud.repository.crud.AbstractCRUDRepository;
import ru.apzakharov.abstract_crud.repository.mapper.DomainMapper;
import ru.apzakharov.application.repository.entity.DemoEntity;
import ru.apzakharov.jooq.tables.Demo;
import ru.apzakharov.jooq.tables.records.DemoRecord;
@Repository
public class DemoRepository extends AbstractCRUDRepository<DemoRecord,Integer, DemoEntity> {

    public DemoRepository(DSLContext dslContext, DomainMapper<DemoRecord, DemoEntity> mapper) {
        super(dslContext, Demo.DEMO, Demo.DEMO.ID, DemoEntity.class, mapper);
    }
}
