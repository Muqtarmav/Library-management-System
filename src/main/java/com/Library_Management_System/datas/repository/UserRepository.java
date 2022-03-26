package com.Library_Management_System.datas.repository;

import com.Library_Management_System.datas.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);

    Optional<User> findByFirstNameOrEmail(String firstName, String email);
}

