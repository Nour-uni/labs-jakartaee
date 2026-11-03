package com.horizon.lab2.model;

import java.util.List;

public class ProductCatalog {
    private static final List<ProductDTO> PRODUCTS = List.of(
            new ProductDTO(1L, "Laptop", "electronics", 1299.99),
            new ProductDTO(2L, "Headphones", "electronics", 89.90),
            new ProductDTO(3L, "Novel", "books", 14.50),
            new ProductDTO(4L, "Desk Lamp", "home", 32.00),
            new ProductDTO(5L, "Backpack", "accessories", 59.00)
    );

    public static List<ProductDTO> findAll() { return PRODUCTS; }

    public static ProductDTO findById(Long id) {
        return PRODUCTS.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}