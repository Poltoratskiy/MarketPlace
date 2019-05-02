package ru.marketplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.marketplace.entity.ProductType;

public interface ProductTypeRepository extends JpaRepository<ProductType, Long> {


}
