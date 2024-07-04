package ru.apzakharov.abstract_crud.repository.entity;

import java.io.Serializable;

public interface EntityWithId<T> extends Serializable {

  T getId();

}
