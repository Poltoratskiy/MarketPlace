package ru.marketplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.marketplace.entity.LineItem;

public interface LineItemRepository extends JpaRepository<LineItem, Long> {


}
