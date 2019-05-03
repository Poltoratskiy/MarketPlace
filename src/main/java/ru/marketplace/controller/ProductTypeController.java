package ru.marketplace.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.marketplace.entity.Product;
import ru.marketplace.entity.ProductType;
import ru.marketplace.service.ProductTypeService;

import java.util.Collection;
import java.util.List;


@RestController
public class ProductTypeController {


    @Autowired
    private ProductTypeService productTypeService;

    @RequestMapping(value = "/productTypes", method = RequestMethod.GET)
    @ResponseBody
    public List<ProductType> getAllProductTypes() {
        return productTypeService.getAll();
    }

    @RequestMapping(value = "/productTypes/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ProductType getProductTypes(@PathVariable("id") long productTypeId) {
        return productTypeService.getById(productTypeId);
    }

    @RequestMapping(value = "/productTypes/{id}/products", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Collection<Product>> getAllProductByProductTypeId(@PathVariable("id") long productTypeId) {
        return productTypeService.getProductsById(productTypeId);
    }



    @RequestMapping(value = "/productTypes", method = RequestMethod.POST)
    @ResponseBody
    public ProductType postProduct(@RequestBody ProductType productType) {
        return productTypeService.save(productType);
    }

    @RequestMapping(value = "/productTypes/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteProduct(@PathVariable("id") long productTypeId) {
        productTypeService.remove(productTypeId);
    }

}
