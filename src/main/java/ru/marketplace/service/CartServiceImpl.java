package ru.marketplace.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.marketplace.entity.Cart;
import ru.marketplace.entity.LineItem;
import ru.marketplace.entity.Order;
import ru.marketplace.entity.Product;
import ru.marketplace.entity.enums.OrderStatusEnum;
import ru.marketplace.repository.CartRepository;
import ru.marketplace.repository.LineItemRepository;
import ru.marketplace.repository.OrderRepository;
import ru.marketplace.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private LineItemRepository lineItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void add(Long cartId, Long productId, Integer quantity) {
        Cart cart = cartRepository.findById(cartId).orElse(null);

        if (cart == null) {
            throw new IllegalArgumentException();
        }
        Product product = productRepository.findById(productId).orElse(null);
        if (product == null) {
            throw new IllegalArgumentException();
        }

        cart.getLineItems().add(new LineItem(cart, product, quantity));
        cartRepository.save(cart);
    }

    @Override
    public void order(Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElse(null);
        if (cart == null) {

        }
        Order order = new Order.BuilderOrder()
                .setCustomer(cart.getCustomer())
                .setOrdered(new Date())
                .setStatus(OrderStatusEnum.Ordered)
                .setCart(cart)
                .build();
        orderRepository.save(order);
        cart.getLineItems().clear();
        cartRepository.save(cart);

    }

    @Override
    public BigDecimal getTotalCost(Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElse(null);

        if (cart == null) {
            throw new IllegalArgumentException();
        }

        return cart.calculateTotal();
    }

    @Override
    public void remove(Long cartId, Long lineItemId) {

        Cart cart = cartRepository.findById(cartId).orElse(null);

        if (cart == null) {
            throw new IllegalArgumentException();
        }
        LineItem lineItem = lineItemRepository.findById(lineItemId).orElse(null);
        cart.removeLineItem(lineItem);

        cartRepository.save(cart);

    }

    @Override
    public void save(Cart cart) {
        cartRepository.save(cart);
    }

    @Override
    public void cleanAll(Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElse(null);

        if (cart == null) {
            throw new IllegalArgumentException();
        }
        cart.getLineItems().clear();
        cartRepository.save(cart);

    }

    @Override
    public List<Cart> getAll() {
        return cartRepository.findAll();
    }
}
