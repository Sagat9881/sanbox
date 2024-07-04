package ru.apzakharov.abstract_crud.repository.crud;

import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Table;
import org.jooq.UpdatableRecord;
import ru.apzakharov.abstract_crud.repository.entity.EntityWithId;
import ru.apzakharov.abstract_crud.repository.mapper.DomainMapper;

import java.util.List;

public abstract class AbstractCRUDRepository<R extends UpdatableRecord<R>, ID, P extends EntityWithId<ID>>
        implements CrudRepository<ID, P> {

    protected final DSLContext dslContext;
    protected final Table<R> table;
    protected final Field<ID> idField;
    protected final Class<P> pojoClass;
    protected final DomainMapper<R, P> mapper;

    public AbstractCRUDRepository(DSLContext dslContext, Table<R> table, Field<ID> idField,  Class<P> pojoClass, DomainMapper<R, P> mapper) {
        this.dslContext = dslContext;
        this.table = table;
        this.pojoClass = pojoClass;
        this.idField = idField;
        this.mapper = mapper;
    }

    @Override
    public void saveAll(List<P> entities) {
        dslContext.batchInsert(entities.stream().map(mapper::toRecord).toList()).execute();
    }



    @Override
    public void upsert(List<P> entities) {
        var queries = entities.stream()
                .map(mapper::toRecord)
                .map(record ->
                        dslContext.insertInto(record.getTable())
                                .set(record)
                                .onDuplicateKeyUpdate()
                                .set(record)
                ).toList();

        dslContext.batch(queries).execute();
    }

    @Override
    public List<P> getAll() {
        return dslContext.selectFrom(table)
                .fetch()
                .map(mapper::toEntity);
    }

    public P getById(ID id) {
        return getRecordById(id).into(pojoClass);
    }

    public P update(ID id, P pojo) {
        R record = getRecordById(id);
        record.from(pojo);
        record.store();
        return mapper.toEntity(record);
    }

    public P save(P pojo) {
        R record = newRecord(pojo);
        record.store();
        return mapper.toEntity(record);
    }

    public void delete(ID id) {
        dslContext.delete(table).where(idField.eq(id)).execute();
    }

    protected R getRecordById(ID id) {
        return dslContext.selectFrom(table)
                .where(idField.eq(id))
                .fetchOne();
    }

    protected R newRecord(Object obj) {
        return dslContext.newRecord(table, obj);
    }
}
