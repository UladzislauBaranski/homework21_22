package com.gmail.vladbaransky.springbootmodule.controller;

import com.gmail.vladbaransky.servicemodule.ShopService;
import com.gmail.vladbaransky.servicemodule.model.ShopDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ShopController {

    private final ShopService shopService;

    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping("/shops")
    public String getShops(Model model) {
        List<ShopDTO> shops = shopService.findAll();
        model.addAttribute("shops", shops);
        return "shops";
    }

    @GetMapping("/add/shop")
    public String addShopPage(Model model) {
        model.addAttribute("shop", new ShopDTO());
        return "shop_add";
    }

    @PostMapping("/add/shop")
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

    @GetMapping("/find/location")
    public String findByLocationPage(Model model) {
        model.addAttribute("shop", new ShopDTO());
        return "page_find_shops_by_location";
    }

    @PostMapping("/find/location")
    public String findByAttribute(
            @Valid @ModelAttribute(name = "shop") ShopDTO shop,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("shop", shop);
            return "shop_add";
        }
        List<ShopDTO> shopResult = shopService.findByLocation(shop.getLocation());
        model.addAttribute("shops", shopResult);
        return "shops_with_attribute";
    }
}
