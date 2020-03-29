package com.gmail.vladbaransky.servicemodule.model;

import com.gmail.vladbaransky.repositorymodule.model.Item;
import com.sun.istack.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ShopDTO {

    private Long id;
    private String name;
    private String location;
    private List<Item> itemList = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    @Override
    public String toString() {
        return "ShopDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", itemList=" + itemList +
                '}';
    }
}


