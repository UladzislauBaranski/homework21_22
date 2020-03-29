package com.gmail.vladbaransky.repositorymodule;

import com.gmail.vladbaransky.repositorymodule.model.Item;

import java.math.BigDecimal;
import java.util.List;


public interface ItemRepository extends GenericDaoRepository<Long, Item> {

    List<Item> findByName(String name);

    List<Item> findByPrice(BigDecimal price);

    List<Item> findByNameAndPrice(String name, BigDecimal price);

}
