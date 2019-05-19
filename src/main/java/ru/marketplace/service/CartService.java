package ru.marketplace.service;

import org.springframework.transaction.annotation.Transactional;
import ru.marketplace.entity.Cart;
import ru.marketplace.entity.LineItem;

import java.math.BigDecimal;
import java.util.List;

@Transactional
public interface CartService {
    void save(Cart cart);
    void add(Long cartId, Long productId, Integer quantity);
    void remove(Long cartId, Long lineItemId);
    BigDecimal getTotalCost(Long cartId);
    void cleanAll(Long cartId);
    void order(Long cartId);
    List<Cart> getAll();

}
