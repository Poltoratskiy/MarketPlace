package ru.marketplace.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.marketplace.entity.Product;
import ru.marketplace.entity.Category;
import ru.marketplace.service.CategoryService;

import java.util.Collection;
import java.util.List;


@RestController
public class CategoryController {


    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/productTypes", method = RequestMethod.GET)
    @ResponseBody
    public List<Category> getAllProductTypes() {
        return categoryService.getAll();
    }

    @RequestMapping(value = "/productTypes/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Category getProductTypes(@PathVariable("id") long productTypeId) {
        return categoryService.getById(productTypeId);
    }

    @RequestMapping(value = "/productTypes/{id}/products", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Collection<Product>> getAllProductByProductTypeId(@PathVariable("id") long productTypeId) {
        return categoryService.getProductsById(productTypeId);
    }


    @RequestMapping(value = "/productTypes", method = RequestMethod.POST)
    @ResponseBody
    public Category postProductType(@RequestBody Category category) {
        return categoryService.save(category);
    }

    @RequestMapping(value = "/productTypes/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteProductType(@PathVariable("id") long productTypeId) {
        categoryService.remove(productTypeId);
    }

}
