package ru.marketplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.marketplace.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {


}
