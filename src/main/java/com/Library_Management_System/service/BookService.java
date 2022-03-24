package com.Library_Management_System.service;

import com.Library_Management_System.datas.model.Book;
import com.Library_Management_System.dtos.BookDto;
import com.github.fge.jsonpatch.JsonPatch;

import java.util.List;

public interface BookService {

    List<Book> findAll();
    Book addBooks(BookDto bookDto);
    Book findById(Long id);
    Book updateBookRecords(Long id, JsonPatch book);
    void delete(Book book);



}
