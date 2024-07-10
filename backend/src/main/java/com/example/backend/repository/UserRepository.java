package com.example.backend.repository;

import com.example.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByName(String name);
    Optional<User> findByMail(String mail);
    Optional<User> findByNameAndPassword(String name, String password);
}
