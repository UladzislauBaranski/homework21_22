package com.gmail.vladbaransky.servicemodule.impl;

import com.gmail.vladbaransky.repositorymodule.ShopRepository;
import com.gmail.vladbaransky.repositorymodule.model.Shop;
import com.gmail.vladbaransky.servicemodule.ShopService;
import com.gmail.vladbaransky.servicemodule.model.ShopDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShopServiceImpl implements ShopService {

    private final ShopRepository shopRepository;

    public ShopServiceImpl(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    @Override
    @Transactional
    public List<ShopDTO> findAll() {
        List<Shop> shops = shopRepository.findAll();
        return shops.stream()
                .map(this::getDTOFromObject)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void add(ShopDTO shopDTO) {
        Shop shop = getObjectFromDTO(shopDTO);
        shopRepository.add(shop);
    }

    private ShopDTO getDTOFromObject(Shop shop) {
        ShopDTO shopDTO = new ShopDTO();
        shopDTO.setId(shop.getId());
        shopDTO.setName(shop.getName());
        shopDTO.setLocation(shop.getLocation());
        return shopDTO;
    }

    private Shop getObjectFromDTO(ShopDTO shopDTO) {
        Shop shop = new Shop();
        shop.setName(shopDTO.getName());
        shop.setLocation(shopDTO.getLocation());
        return shop;
    }
}
