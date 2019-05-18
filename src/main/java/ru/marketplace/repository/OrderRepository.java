package ru.marketplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.marketplace.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {


}
