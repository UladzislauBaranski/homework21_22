package com.gmail.vladbaransky.repositorymodule;

import com.gmail.vladbaransky.repositorymodule.model.User;

public interface UserRepository extends GenericDaoRepository<Long, User> {

    User getUserByUsername(String username);
}
