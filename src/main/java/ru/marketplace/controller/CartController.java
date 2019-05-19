package ru.marketplace.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.marketplace.entity.Cart;
import ru.marketplace.service.CartService;

import java.util.List;

@RestController
public class CartController {

    @Autowired
    private CartService cartService;

    @RequestMapping(value = "/carts", method = RequestMethod.GET)
    @ResponseBody
    public List<Cart> getAllCarts() {
        return cartService.getAll();
    }
}
