package com.gmail.vladbaransky.servicemodule;

import com.gmail.vladbaransky.servicemodule.model.ItemDTO;
import com.gmail.vladbaransky.servicemodule.model.ShopDTO;

import java.util.List;

public interface ShopService {
    List<ShopDTO> findAll();

    void add(ShopDTO shopDTO);

}
