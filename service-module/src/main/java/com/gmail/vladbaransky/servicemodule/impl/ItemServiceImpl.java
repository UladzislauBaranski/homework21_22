package com.gmail.vladbaransky.servicemodule.impl;

import com.gmail.vladbaransky.repositorymodule.ItemRepository;
import com.gmail.vladbaransky.repositorymodule.model.Item;
import com.gmail.vladbaransky.repositorymodule.model.ItemDetails;
import com.gmail.vladbaransky.servicemodule.ItemService;
import com.gmail.vladbaransky.servicemodule.model.ItemDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    @Transactional
    public List<ItemDTO> findAll() {
        List<Item> items = itemRepository.findAll();
        return items.stream()
                .map(this::getDTOFromObject)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void add(ItemDTO itemDTO) {
        Item item = getObjectFromDTO(itemDTO);
        itemRepository.add(item);
    }

    @Override
    @Transactional
    public ItemDTO findById(Long id) {
        Item item = itemRepository.findById(id);
        return getDTOFromObject(item);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Item item = itemRepository.findById(id);
        itemRepository.delete(item);
    }

    private ItemDTO getDTOFromObject(Item item) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(item.getId());
        itemDTO.setName(item.getName());
        itemDTO.setDescription(item.getDescription());
        if (item.getItemDetails() != null) {
            String priceFromDatabase = item.getItemDetails().getPrice();
            BigDecimal price = BigDecimal.valueOf(Double.parseDouble(priceFromDatabase));
            itemDTO.setPrice(price);
            Long itemId = item.getItemDetails().getItemId();
            itemDTO.setId(itemId);
        }
        return itemDTO;
    }

    private Item getObjectFromDTO(ItemDTO itemDTO) {
        Item item = new Item();
        item.setName(itemDTO.getName());
        item.setDescription(itemDTO.getDescription());
        ItemDetails itemDetails = new ItemDetails();
        itemDetails.setItem(item);
        itemDetails.setPrice(itemDTO.getPrice().toString());
        item.setItemDetails(itemDetails);
        return item;
    }
}

