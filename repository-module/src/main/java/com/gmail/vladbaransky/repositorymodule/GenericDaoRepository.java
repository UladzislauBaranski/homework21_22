package com.gmail.vladbaransky.repositorymodule;

import java.util.List;

public interface GenericDaoRepository<I, T> {
    List<T> findAll();

    void add(T entity);

    T findById(I id);

    void delete(T entity);

}
