package ru.marketplace.service;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import ru.marketplace.entity.User;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Transactional
public interface UserService {
    List<User> getAll();

    User getById(long id);

//    void save(User product);

    void remove(long id);

    Optional<User> findByUsername(String username);


}
