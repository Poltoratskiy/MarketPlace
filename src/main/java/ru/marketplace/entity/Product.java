package ru.marketplace.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "product")
public class Product {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "product_id")
    private long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "price", nullable = false)
    private Integer price;

    public Product() {
    }

//    ProductTypeRepository productTypeRepository;
    public Product(String name, Integer price, Long typeId) {
        this.name = name;
        this.price = price;
//        this.productType = productTypeRepository.findById(typeId).orElse(null);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_type_id")
    @JsonIgnore
    private ProductType productType;

    @ManyToMany
    @JoinTable(name = "market_products",
            joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "market_id", referencedColumnName = "market_id"))
    @JsonIgnore
    private Set<Market> markets = new HashSet<Market>();

    public Set<Market> getMarkets() {
        return markets;
    }

    public void setMarkets(Set<Market> markets) {
        this.markets = markets;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }


}
