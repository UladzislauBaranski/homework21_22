package com.gmail.vladbaransky.repositorymodule.impl;

import com.gmail.vladbaransky.repositorymodule.ItemRepository;
import com.gmail.vladbaransky.repositorymodule.model.Item;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.List;

@Repository
public class ItemRepositoryImpl extends GenericDaoRepositoryImpl<Long, Item> implements ItemRepository {

    @Override
    public List<Item> findByName(String name) {
        String queryString = "FROM " + entityClass.getSimpleName() +
                " i WHERE i.name =:name";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("name", name);
        List<Item> resultList = query.getResultList();
        return resultList;
    }

    @Override
    public List<Item> findByPrice(BigDecimal price) {
        String stringPrice = String.valueOf(price);
        String queryString = "FROM " + entityClass.getSimpleName() +
                " i WHERE i.itemDetails.price =:stringPrice";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("stringPrice", stringPrice);
        return (List<Item>) query.getResultList();
    }

    @Override
    public List<Item> findByNameAndPrice(String name, BigDecimal price) {
        String stringPrice = String.valueOf(price);
        String queryString = "FROM " + entityClass.getSimpleName() +
                " i WHERE i.itemDetails.price =:stringPrice AND  i.name =:name";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("name", name);
        query.setParameter("stringPrice", stringPrice);
        return (List<Item>) query.getResultList();
    }
}
