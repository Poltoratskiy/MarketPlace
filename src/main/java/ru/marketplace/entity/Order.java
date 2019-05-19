package ru.marketplace.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import ru.marketplace.entity.enums.OrderStatusEnum;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "\"order\"")
public class Order implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private long id;


    public Order(){};

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id", referencedColumnName = "cart_id")
    @JsonIgnore
    private Cart cart;


    private OrderStatusEnum status;

    @Column(name = "ordered")
    private Date ordered;


    public Order(Long id, Cart cart, Date ordered, OrderStatusEnum status) {
        this.id = id;
        this.cart = cart;
        this.ordered = ordered;
        this.status = status;
    }


    public Date getOrdered() {
        return ordered;
    }

    public void setOrdered(Date ordered) {
        this.ordered = ordered;
    }

    public OrderStatusEnum getStatus() {
        return status;
    }

    public void setStatus(OrderStatusEnum status) {
        this.status = status;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }


    public static class BuilderOrder {

        private Long id;
        private Customer customer;
        private Date ordered;
        private OrderStatusEnum status;
        private Cart cart;

        public BuilderOrder setOrderId(Long id) {
            this.id = id;
            return this;
        }

        public BuilderOrder setCustomer(Customer customer) {
            this.customer = customer;
            return this;
        }

        public BuilderOrder setOrdered(Date ordered) {
            this.ordered = ordered;
            return this;
        }

        public BuilderOrder setStatus(OrderStatusEnum status) {
            this.status = status;
            return this;
        }


        public BuilderOrder setCart(Cart cart) {
            this.cart = cart;
            return this;
        }

        public Order build() {
            Order order = new Order(this.id, this.cart, this.ordered, this.status);
            return order;
        }
    }


}
