package ru.marketplace.service;

import org.springframework.transaction.annotation.Transactional;
import ru.marketplace.entity.Product;

import java.util.List;

@Transactional
public interface ProductService {
    List<Product> getAll();

    Product getById(long id);

    Product save(Product product);

    void remove(long id);


}
