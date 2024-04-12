package ru.apzakharov.abstract_crud.dto;



import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ListResult<T> {
    long totalCount;
    List<T> data = new LinkedList<>();

    public ListResult(long totalCount, Collection<T> data) {
        this.totalCount = totalCount;
        this.data = new LinkedList<>(data);
    }
}
