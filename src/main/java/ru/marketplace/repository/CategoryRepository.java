package ru.marketplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.marketplace.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {


}
