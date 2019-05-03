package ru.marketplace.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import ru.marketplace.entity.Product;
import ru.marketplace.entity.ProductType;
import ru.marketplace.repository.ProductTypeRepository;

import java.util.Collection;
import java.util.List;
import java.util.Set;


@Service
public class ProductTypeServiceImpl implements ProductTypeService {

    @Autowired
    private ProductTypeRepository productTypeRepository;


    public List<ProductType> getAll() {
        return productTypeRepository.findAll();
    }

    public ProductType getById(long id) {
        return productTypeRepository.findById(id).orElse(null);
    }

    public ProductType save(ProductType productType) {
        return productTypeRepository.saveAndFlush(productType);
    }

    public void remove(long id) {
        productTypeRepository.deleteById(id);

    }

    public ResponseEntity<Collection<Product>> getProductsById(long id) {
        ProductType productType = productTypeRepository.findById(id).orElse(null);

        if(productType !=null){
            return new ResponseEntity<>(productType.getProducts(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }


    }
}
