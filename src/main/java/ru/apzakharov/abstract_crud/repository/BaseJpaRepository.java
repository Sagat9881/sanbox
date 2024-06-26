package ru.apzakharov.abstract_crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * User: ibalekseev
 * Date: 12.05.2017
 * Time: 17:38
 */
@NoRepositoryBean
public interface BaseJpaRepository<T, ID extends Serializable> extends QuerydslPredicateExecutor<T>, JpaRepository<T, ID>, JpaSpecificationExecutor<T>, QueryDslPredicateExecutorFixRepository<T> {

}
