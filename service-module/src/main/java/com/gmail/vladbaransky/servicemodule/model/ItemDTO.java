package com.gmail.vladbaransky.servicemodule.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ItemDTO {
    String NOT_NULL_NAME_MESSAGE = "Name cannot be empty";
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private List<ShopDTO> shopsDTO = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<ShopDTO> getShopsDTO() {
        return shopsDTO;
    }

    public void setShopsDTO(List<ShopDTO> shopsDTO) {
        this.shopsDTO = shopsDTO;
    }


}
