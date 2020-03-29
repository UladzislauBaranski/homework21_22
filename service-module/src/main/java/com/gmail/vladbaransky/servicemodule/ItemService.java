package com.gmail.vladbaransky.servicemodule;

import com.gmail.vladbaransky.servicemodule.model.ItemDTO;

import java.util.List;

public interface ItemService {
   List<ItemDTO> findAll();

    void add(ItemDTO itemDTO);

    void deleteById(Long id);

    ItemDTO findById(Long id);

}
