package com.Library_Management_System.datas.repository;

import com.Library_Management_System.datas.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    //User findById(int id);

    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);

    Optional<User> findByUserName(String userName);
    Boolean existsByUserName(String userName);

    Boolean existsByEmail(String email);
}

