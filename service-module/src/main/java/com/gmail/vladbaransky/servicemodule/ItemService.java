package com.gmail.vladbaransky.servicemodule;

import com.gmail.vladbaransky.servicemodule.model.ItemDTO;

import java.math.BigDecimal;
import java.util.List;

public interface ItemService {
   List<ItemDTO> findAll();

    void add(ItemDTO itemDTO);

    void deleteById(Long id);

    ItemDTO findById(Long id);

    List<ItemDTO> findByName(String name);

    List<ItemDTO> findByPrice(BigDecimal price);

 List<ItemDTO> findByNameAndPrice(String name, BigDecimal price);

    void addItemAndShop(ItemDTO itemDTO);
}
