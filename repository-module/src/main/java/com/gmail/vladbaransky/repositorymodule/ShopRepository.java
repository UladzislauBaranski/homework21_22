package com.gmail.vladbaransky.repositorymodule;

import com.gmail.vladbaransky.repositorymodule.model.Shop;

import java.util.List;

public interface ShopRepository extends GenericDaoRepository<Long, Shop> {

    List<Shop> findByLocation(String location);

}
