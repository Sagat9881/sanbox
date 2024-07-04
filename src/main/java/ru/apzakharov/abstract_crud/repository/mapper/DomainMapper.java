package ru.apzakharov.abstract_crud.repository.mapper;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jooq.Record;
import org.jooq.RecordMapper;
import ru.apzakharov.abstract_crud.repository.entity.EntityWithId;

import java.util.function.Function;


public interface DomainMapper<RECORD extends Record, ENTITY extends EntityWithId> extends RecordMapper<RECORD, ENTITY> {

    ENTITY toEntity(RECORD record);

    RECORD toRecord(ENTITY entity);

    @Override
    @Nullable
    default ENTITY map(RECORD record) {
        return this.toEntity(record);
    }

    @Override
    default ENTITY apply(RECORD record) {
        return this.toEntity(record);
    }

    @NotNull
    @Override
    default <V> Function<V, ENTITY> compose(@NotNull Function<? super V, ? extends RECORD> before) {
        return RecordMapper.super.compose(before);
    }

    @NotNull
    @Override
    default <V> Function<RECORD, V> andThen(@NotNull Function<? super ENTITY, ? extends V> after) {
        return RecordMapper.super.andThen(after);
    }
}
