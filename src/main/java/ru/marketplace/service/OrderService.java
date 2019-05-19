package ru.marketplace.service;

import org.springframework.transaction.annotation.Transactional;
import ru.marketplace.entity.Order;

import java.util.List;

@Transactional
public interface OrderService {
    void removeOrder(Order order);
    void save(Order order);
    List<Order> getAll();
}
