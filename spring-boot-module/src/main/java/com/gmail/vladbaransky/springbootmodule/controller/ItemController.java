package com.gmail.vladbaransky.springbootmodule.controller;

import com.gmail.vladbaransky.servicemodule.ItemService;
import com.gmail.vladbaransky.servicemodule.ShopService;
import com.gmail.vladbaransky.servicemodule.model.ItemDTO;
import com.gmail.vladbaransky.servicemodule.model.ShopDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Controller
public class ItemController {

    private final ItemService itemService;
    private final ShopService shopService;

    public ItemController(ItemService itemService, ShopService shopService) {
        this.itemService = itemService;
        this.shopService = shopService;
    }

    @GetMapping("/items")
    public String getItem(Model model) {
        List<ItemDTO> items = itemService.findAll();
        model.addAttribute("items", items);
        model.addAttribute("item", new ItemDTO());
        return "items";
    }

    @GetMapping("/add/item")
    public String addItemAndShopPage(Model model) {
        List<ShopDTO> shops = shopService.findAll();
        model.addAttribute("shops", shops);
        model.addAttribute("item", new ItemDTO());
        return "item_add";
    }

    @PostMapping("/add/item")
    public String addItemAndShopPage(
            @ModelAttribute(name = "item") @Valid ItemDTO item,
            @RequestParam(required = false, name = "shopId") Long[] shopId,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("item", item);
            return "item_add";
        } else {
            List<ShopDTO> shops = shopService.findAll();
            for (int i = 0; i < shops.size(); i++) {
                for (Long aLong : shopId) {
                    if (aLong.equals(shops.get(i).getId())) {
                        item.setShopsDTO(shops);
                    }
                }
                itemService.addItemAndShop(item);
            }
            model.addAttribute("item", item);
            model.addAttribute("shops", item.getShopsDTO());
            return "item_and_shop";
        }
    }

    @GetMapping("/item/{id}")
    public String getItemAndShopById(@PathVariable Long id, Model model) {
        ItemDTO item = itemService.findById(id);
        model.addAttribute("item", item);
        model.addAttribute("shops", item.getShopsDTO());
        return "item_and_shop";
    }

    @PostMapping("/find")
    public String addShop(
            @Valid @ModelAttribute(name = "item") ItemDTO item,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("item", item);
            return "shop_add";
        }
        ItemDTO itemResult = itemService.findById(item.getId());
        model.addAttribute("item", itemResult);
        model.addAttribute("shops", itemResult.getShopsDTO());
        return "item_and_shop";
    }

    @PostMapping("/find/name")
    public String findByName(
            @Valid @ModelAttribute(name = "item") ItemDTO item,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("item", item);
            return "shop_add";
        }
        List<ItemDTO> itemResult = itemService.findByName(item.getName());
        model.addAttribute("items", itemResult);
        return "items_with_attribute";
    }

    @PostMapping("/find/price")
    public String findByPrice(
            @Valid @ModelAttribute(name = "item") ItemDTO item,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("item", item);
            return "shop_add";
        }
        List<ItemDTO> itemResult = itemService.findByPrice(item.getPrice());
        model.addAttribute("items", itemResult);
        return "items_with_attribute";
    }

    @GetMapping("/find/all")
    public String findByAttributePage(Model model) {
        model.addAttribute("item", new ItemDTO());
        return "page_find_items_by_attribute";
    }

    @PostMapping("/find/all")
    public String findByAttribute(
            @Valid @ModelAttribute(name = "item") ItemDTO item,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("item", item);
            return "shop_add";
        }
        List<ItemDTO> itemResult = itemService.findByNameAndPrice(item.getName(), item.getPrice());
        model.addAttribute("items", itemResult);
        return "items_with_attribute";
    }

    @PostMapping("/find/attribute")
    public String findItem(
            @Valid @ModelAttribute(name = "item") ItemDTO item,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("item", item);
            return "shop_add";
        }
        List<ItemDTO> itemResult = itemService.findByName(item.getName());
        model.addAttribute("items", itemResult);
        return "items_with_attribute";
    }

    @GetMapping("item/delete/{id}")
    public String deleteItem(@PathVariable Long id) {
        itemService.deleteById(id);
        return "redirect:/items";
    }
}

