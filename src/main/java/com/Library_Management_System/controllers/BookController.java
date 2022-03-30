package com.Library_Management_System.controllers;


import com.Library_Management_System.datas.model.Book;
import com.Library_Management_System.dtos.BookDto;
import com.Library_Management_System.exceptions.BookDoesNotExistException;
import com.Library_Management_System.exceptions.BookLogicException;
import com.Library_Management_System.service.BookService;
import com.github.fge.jsonpatch.JsonPatch;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("api/book")
@RestController

public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping()
    public ResponseEntity<?> findAll(){
        List<Book> bookList = bookService.findAll();
        return ResponseEntity.ok().body(bookList);
    }

    @GetMapping("get/book/id")
    public ResponseEntity<?>findById(Long id){

        try{
            Book savedBook = bookService.findBookById(id);
            return ResponseEntity.ok().body(savedBook);
        }

        catch(BookDoesNotExistException message){
            return ResponseEntity.badRequest().body(message);
        }
    }



    @PostMapping("add/")
    public ResponseEntity<?> addBooks(@RequestBody BookDto bookDto){

        log.info("user added::{}", bookDto);

        try{
            Book book = bookService.addBookToFavorite(bookDto);
            return ResponseEntity.ok().body(book);
        }

        catch (BookLogicException message){
            return ResponseEntity.badRequest().body(message);
        }
    }

    @PatchMapping(path = "{id}", consumes = "application/json-patch+json")
    public ResponseEntity<?> updateBookRecords(@PathVariable Long id, @RequestBody JsonPatch bookPatch){

        try {
            Book updatedEmployee = bookService.updateBookRecords(id, bookPatch);
            log.info("updated employee {}", updatedEmployee);
            return ResponseEntity.status(HttpStatus.OK).body(updatedEmployee);
        }

        catch (BookLogicException e){
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
        return  null;
    }
}
