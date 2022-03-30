package com.Library_Management_System.datas.repository;

import com.Library_Management_System.datas.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long>{
Optional<Role> findByName(String name);
}
