package ru.marketplace.service;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import ru.marketplace.entity.User;

import java.util.Collection;
import java.util.List;

@Transactional
public interface UserService {
    List<User> getAll();

    User getById(long id);

//    void save(User product);

    void remove(long id);

    User findByUsername(String username);
}
