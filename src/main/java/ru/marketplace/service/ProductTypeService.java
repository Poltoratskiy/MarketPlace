package ru.marketplace.service;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import ru.marketplace.entity.Product;
import ru.marketplace.entity.ProductType;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Transactional
public interface ProductTypeService {
    List<ProductType> getAll();

    ProductType getById(long id);

    ProductType save(ProductType product);

    void remove(long id);

    ResponseEntity<Collection<Product>> getProductsById(long id);


}
