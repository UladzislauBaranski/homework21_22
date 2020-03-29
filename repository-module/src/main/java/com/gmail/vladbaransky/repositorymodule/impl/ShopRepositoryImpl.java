package com.gmail.vladbaransky.repositorymodule.impl;

import com.gmail.vladbaransky.repositorymodule.ShopRepository;
import com.gmail.vladbaransky.repositorymodule.model.Shop;
import org.springframework.stereotype.Repository;

@Repository
public class ShopRepositoryImpl extends GenericDaoRepositoryImpl<Long, Shop> implements ShopRepository {

}
