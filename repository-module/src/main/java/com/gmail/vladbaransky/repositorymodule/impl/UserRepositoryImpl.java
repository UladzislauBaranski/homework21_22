package com.gmail.vladbaransky.repositorymodule.impl;

import com.gmail.vladbaransky.repositorymodule.UserRepository;
import com.gmail.vladbaransky.repositorymodule.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

@Repository
public class UserRepositoryImpl extends GenericDaoRepositoryImpl<Long, User> implements UserRepository {

    @Override
    public User getUserByUsername(String username) {
        String queryString = "FROM " + entityClass.getSimpleName() +
                " u WHERE u.username =:name";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("name", username);
        return (User) query.getSingleResult();
    }
}
