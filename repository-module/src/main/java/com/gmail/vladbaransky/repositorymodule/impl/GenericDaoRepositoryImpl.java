package com.gmail.vladbaransky.repositorymodule.impl;

import com.gmail.vladbaransky.repositorymodule.GenericDaoRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class GenericDaoRepositoryImpl<I, T> implements GenericDaoRepository<I, T> {

    protected Class<T> entityClass;
    @PersistenceContext
    protected EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public GenericDaoRepositoryImpl() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass()
                .getGenericSuperclass();
        this.entityClass = (Class<T>) genericSuperclass.getActualTypeArguments()[1];
    }

    @Override
    public List<T> findAll() {
        String query = "from " + entityClass.getName() + " c  ORDER BY c.name ASC";
        Query q = entityManager.createQuery(query);
        return q.getResultList();
    }

    @Override
    public void add(T entity) {
        entityManager.persist(entity);
    }

    @Override
    public T findById(I id) {
        return entityManager.find(entityClass, id);
    }

    @Override
    public void delete(T entity) {
        entityManager.remove(entity);
    }

}
