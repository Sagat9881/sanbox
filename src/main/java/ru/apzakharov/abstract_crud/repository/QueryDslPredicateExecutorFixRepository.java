package ru.apzakharov.abstract_crud.repository;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

/**
 * User: ibalekseev
 * Date: 19.05.2017
 * Time: 13:53
 */
public interface QueryDslPredicateExecutorFixRepository<T> extends QuerydslPredicateExecutor<T> {

    @Override
    List<T> findAll(Predicate predicate);

    @Override
    List<T> findAll(Predicate predicate, Sort sort);

    @Override
    List<T> findAll(Predicate predicate, OrderSpecifier<?>[] orders);

    @Override
    List<T> findAll(OrderSpecifier<?>[] orders);

}
