package ru.marketplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.marketplace.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {


}
