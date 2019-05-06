package ru.marketplace.controller;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import ru.marketplace.entity.Product;
import ru.marketplace.service.ProductService;

import java.util.List;


@RestController
public class ProductController {


    @Autowired
    private ProductService productService;

//    @RequestMapping(value = "/products", method = RequestMethod.GET)
//    @ResponseBody
//    public List<Product> getAllProducts() {
//        return productService.getAll();
//    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    @ResponseBody
    public List<Product> getAllProductsByName(@RequestParam String name) {
        return productService.findByName(name);
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Product getProduct(@PathVariable("id") long productId) {
        return productService.getById(productId);
    }

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    @ResponseBody
    public Product postProduct(@RequestBody Product product) {
        return productService.save(product);
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteProduct(@PathVariable("id") long productId) {
        productService.remove(productId);
    }

}
