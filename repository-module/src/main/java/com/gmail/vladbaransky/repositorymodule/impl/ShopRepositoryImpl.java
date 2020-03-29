package com.gmail.vladbaransky.repositorymodule.impl;

import com.gmail.vladbaransky.repositorymodule.ShopRepository;
import com.gmail.vladbaransky.repositorymodule.model.Shop;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class ShopRepositoryImpl extends GenericDaoRepositoryImpl<Long, Shop> implements ShopRepository {

    @Override
    public List<Shop> findByLocation(String location) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Shop> query = cb.createQuery(Shop.class);
        Root<Shop> name = query.from(Shop.class);
        ParameterExpression<String> parameterExpression = cb.parameter(String.class);
        query.select(name).where(cb.equal(name.get("location"), parameterExpression));
        TypedQuery<Shop> typedQuery = entityManager.createQuery(query);
        typedQuery.setParameter(parameterExpression, location);
        return typedQuery.getResultList();
    }
}
