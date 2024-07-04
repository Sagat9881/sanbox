package ru.apzakharov.application.repository.entity;

import lombok.Builder;
import lombok.Data;
import ru.apzakharov.abstract_crud.repository.entity.EntityWithId;

@Data
@Builder
public class DemoEntity implements EntityWithId<Integer> {
    private Integer id;
    private Integer valueint;
    private String valuechar;

    @Override
    public Integer getId() {
        return id;
    }
}
