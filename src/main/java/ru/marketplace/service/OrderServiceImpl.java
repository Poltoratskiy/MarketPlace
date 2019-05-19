package ru.marketplace.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.marketplace.entity.Order;
import ru.marketplace.repository.OrderRepository;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {


    @Autowired
    OrderRepository orderRepository;

    @Override
    public void removeOrder(Order order) {
        orderRepository.delete(order);
    }

    @Override
    public void save(Order order) {
        orderRepository.saveAndFlush(order);
    }

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }
}
