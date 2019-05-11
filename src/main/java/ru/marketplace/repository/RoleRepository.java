package ru.marketplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.marketplace.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {


}
