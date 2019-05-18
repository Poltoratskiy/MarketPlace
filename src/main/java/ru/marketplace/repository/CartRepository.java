package ru.marketplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.marketplace.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {


}
