package com.Library_Management_System.service;

import com.Library_Management_System.datas.model.Book;
import com.Library_Management_System.dtos.BookDto;
import com.github.fge.jsonpatch.JsonPatch;

import java.util.List;

public class BookServiceImpl implements BookService{
    @Override
    public List<Book> findAll() {
        return null;
    }

    @Override
    public Book addBooks(BookDto bookDto) {
        return null;
    }

    @Override
    public Book findById(Long id) {
        return null;
    }

    @Override
    public Book updateBookRecords(Long id, JsonPatch book) {
        return null;
    }

    @Override
    public void delete(Book book) {

    }
}
