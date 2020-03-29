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
        return "items";
    }

    @GetMapping("/shops")
    public String getShop(Model model) {
        List<ShopDTO> shops = shopService.findAll();
        model.addAttribute("shops", shops);
        return "shops";
    }

    @GetMapping("/add")
    public String addShopPage(Model model) {
        model.addAttribute("shop", new ShopDTO());
        return "shop_add";
    }

    @PostMapping("/add")
    public String addShop(
            @Valid @ModelAttribute(name = "shop") ShopDTO shop,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("shop", shop);
            return "shop_add";
        }
        shopService.add(shop);
        return "redirect:/shops";
    }

    @GetMapping("item/{id}")
    public String getItem(@PathVariable Long id, Model model) {
        ItemDTO item = itemService.findById(id);
        List<ShopDTO> shops = item.getShops();
        model.addAttribute("item", item);
        model.addAttribute("shops", shops);
        return "item_and_shop";
    }

    @GetMapping("item/delete/{id}")
    public String deleteItem(@PathVariable Long id, Model model) {
        itemService.deleteById(id);
        return "redirect:/items";
    }
}

