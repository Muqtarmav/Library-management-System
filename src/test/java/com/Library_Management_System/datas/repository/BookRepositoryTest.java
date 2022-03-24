package com.Library_Management_System.datas.repository;

import com.Library_Management_System.datas.model.Book;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@Sql(scripts = {"/db/insert.sql"})
@Slf4j
@SpringBootTest
class BookRepositoryTest {
    @Autowired
    BookRepository bookRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("book can be saved in databaseTest")

    void bookCanBeSavedInDatabaseTest(){

        Book book = new Book();

        book.setTitle("ethereum");
        book.setAuthor("vitalik buterin");
        book.setCategory("crypto");
        book.setDescription("finance/blockchain");
        book.setPageCount(1050);
        book.setPublishDate("june/08/2015");

        assertThat(book.getId()).isNull();

        log.info("book can be saved in database");
        bookRepository.save(book);

        assertThat(book.getTitle()).isEqualTo("ethereum");
        assertThat(book.getAuthor()).isEqualTo("vitalik buterin");
        assertThat(book.getCategory()).isEqualTo("crypto");
        assertThat(book.getDescription()).isEqualTo("finance/blockchain");
        assertThat(book.getPublishDate()).isEqualTo("june/08/2015");
        assertThat(book.getPageCount()).isEqualTo(1050);
        assertThat(book.getId()).isNotNull();

    }

    @Test
    @DisplayName("find all books in database")

    void findAllBooksInDatabaseTest(){

        List<Book> book = bookRepository.findAll();

        assertThat(book).isNotNull();
        assertThat(book.size()).isEqualTo(2);
    }


    @Test
    @DisplayName("books can be find by Id")

    void BookCanBeFindById(){

        Book book = bookRepository.findById(10L).orElse(null);
        assertThat(book).isNotNull();
        assertThat(book.getTitle()).isEqualTo("java");
        assertThat(book.getAuthor()).isEqualTo("daitel");
        assertThat(book.getDescription()).isEqualTo("programming language");
        assertThat(book.getPageCount()).isEqualTo(700);
        assertThat(book.getCategory()).isEqualTo("software engineering");
        assertThat(book.getId()).isEqualTo(10);
        log.info("books can be retrieved:: {}", book);

    }


    @Test
    @DisplayName("book can be updated")

    void bookCanBeUpdated(){

        Book book = bookRepository.findByCategory("software engineering").orElse(null);

        assertThat(book).isNotNull();
        assertThat(book.getTitle()).isEqualTo("java");
        assertThat(book.getAuthor()).isEqualTo("daitel");
        assertThat(book.getCategory()).isEqualTo("software engineering");

        book.setCategory("software engineering 2");
        log.info("book is now updated");
        bookRepository.save(book);

        assertThat(book.getTitle()).isEqualTo("java");
        assertThat(book.getAuthor()).isEqualTo("daitel");
        assertThat(book.getCategory()).isEqualTo("software engineering 2");


    }

    @Test
    @DisplayName("books can be delete in database")

    void booksCanBeDeleteInDatabase(){

        Book book = new Book();
        bookRepository.delete(book);
    }
}