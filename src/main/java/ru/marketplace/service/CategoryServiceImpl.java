package ru.marketplace.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.marketplace.entity.Product;
import ru.marketplace.entity.Category;
import ru.marketplace.repository.CategoryRepository;


import java.util.Collection;
import java.util.List;


@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;


    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public Category getById(long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    public Category save(Category category) {
        return categoryRepository.saveAndFlush(category);
    }

    public void remove(long id) {
        categoryRepository.deleteById(id);

    }

    public ResponseEntity<Collection<Product>> getProductsById(long id) {
        Category category = categoryRepository.findById(id).orElse(null);

        if (category != null) {
            return new ResponseEntity<>(category.getProducts(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }


    }
}
