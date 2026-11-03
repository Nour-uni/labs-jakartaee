package com.horizon.lab2.service;

import com.horizon.lab2.model.ProductDTO;
import jakarta.ejb.Stateful;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Stateful
@SessionScoped
public class CartService implements Serializable {

    private List<ProductDTO> items = new ArrayList<>();

    public void addItem(ProductDTO product) {
        items.add(product);
    }

    public void removeItem(Long productId) {
        items.removeIf(p -> p.getId().equals(productId));
    }

    public List<ProductDTO> getItems() {
        return items;
    }

    public void clear() {
        items.clear();
    }
}