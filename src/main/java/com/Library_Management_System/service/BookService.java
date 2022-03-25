package com.Library_Management_System.service;

import com.Library_Management_System.datas.model.Book;
import com.Library_Management_System.dtos.BookDto;
import com.Library_Management_System.exceptions.BookDoesNotExistException;
import com.Library_Management_System.exceptions.BookLogicException;
import com.github.fge.jsonpatch.JsonPatch;

import java.util.List;

public interface BookService {

    List<Book> findAll();
    Book addBookToFavorite(BookDto bookDto) throws BookLogicException;
    Book findBookById(Long id) throws BookDoesNotExistException;
    Book updateBookRecords(Long id, JsonPatch book) throws BookLogicException;
    void delete(Book book);



}
