package com.horizon.lab2.web;

import com.horizon.lab2.service.CartService;
import com.horizon.lab2.service.ProductService;
import com.horizon.lab2.service.PromotionService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({"/cart", "/cart/add"})
public class CartServlet extends HttpServlet {

    @Inject
    private CartService cartService;

    @Inject
    private ProductService productService;

    @Inject
    private PromotionService promotionService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("cart", cartService.getItems());
        req.setAttribute("discountedTotal", promotionService.getDiscountedTotal());
        req.getRequestDispatcher("/WEB-INF/views/cart.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long productId = Long.valueOf(req.getParameter("productId"));
        cartService.addItem(productService.getProduct(productId));
        resp.sendRedirect(req.getContextPath() + "/cart");
    }
}