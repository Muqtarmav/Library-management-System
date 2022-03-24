package com.Library_Management_System.datas.repository;

import com.Library_Management_System.datas.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long>{
    Optional<Book> findById(Long id);

    Optional<Book> findByCategory(String category);

}
