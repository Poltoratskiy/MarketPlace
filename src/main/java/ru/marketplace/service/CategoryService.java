package ru.marketplace.service;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import ru.marketplace.entity.Product;
import ru.marketplace.entity.Category;

import java.util.Collection;
import java.util.List;

@Transactional
public interface CategoryService {
    List<Category> getAll();

    Category getById(long id);

    Category save(Category product);

    void remove(long id);

    ResponseEntity<Collection<Product>> getProductsById(long id);


}
