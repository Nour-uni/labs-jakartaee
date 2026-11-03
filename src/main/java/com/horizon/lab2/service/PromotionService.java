package com.horizon.lab2.service;

import com.horizon.lab2.model.ProductDTO;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

/*
 * Separate service on purpose: CartService only manages WHICH items are in the cart
 * (single responsibility). Pricing rules like discounts change independently of
 * cart contents, so they live here and depend on CartService, not inside it.
 */
@Stateless
public class PromotionService {

    private static final double DISCOUNT_RATE = 0.10;

    @Inject
    private CartService cartService;

    public double getTotal() {
        return cartService.getItems().stream()
                .mapToDouble(ProductDTO::getPrice)
                .sum();
    }

    public double getDiscountedTotal() {
        return getTotal() * (1 - DISCOUNT_RATE);
    }
}