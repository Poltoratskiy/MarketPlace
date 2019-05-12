package ru.marketplace.controller;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.marketplace.entity.Product;
import ru.marketplace.service.ProductService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

import static org.springframework.http.ResponseEntity.ok;


@RestController
@RequestMapping("/")
public class ProductController {


    @Autowired
    private ProductService productService;



    @GetMapping("")
    public ResponseEntity all() {
        return ok(this.productService.getAll());
    }



    @RequestMapping(value = "/products", method = RequestMethod.GET)
    @ResponseBody
    public List<Product> getAllProductsByName(@RequestParam(required = false) String searchQuery) {
        if(searchQuery !=null){
            return productService.findByName(searchQuery);
        }
        return productService.getAll();
    }

    @RequestMapping(value = "/v1/products", method = RequestMethod.GET)
    @ResponseBody
    public List<Product> getAllProductsByNameV1(@RequestParam(required = false) String searchQuery) {
        if(searchQuery !=null){
            return productService.findByName(searchQuery);
        }
        return productService.getAll();
    }

//
//    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
//    @ResponseBody
//    public Product getProduct(@PathVariable("id") long productId) {
//        return productService.getById(productId);
//    }
//
//    @RequestMapping(value = "/products", method = RequestMethod.POST)
//    @ResponseBody
//    public Product postProduct(@RequestBody Product product) {
//        return productService.save(product);
//    }
//
//    @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
//    @ResponseBody
//    public void deleteProduct(@PathVariable("id") long productId) {
//        productService.remove(productId);
//    }


}
