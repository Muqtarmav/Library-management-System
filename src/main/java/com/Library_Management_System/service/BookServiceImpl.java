package com.Library_Management_System.service;

import com.Library_Management_System.datas.model.Book;
import com.Library_Management_System.datas.model.User;
import com.Library_Management_System.datas.repository.BookRepository;
import com.Library_Management_System.dtos.BookDto;
import com.Library_Management_System.exceptions.BookDoesNotExistException;
import com.Library_Management_System.exceptions.BookLogicException;
import com.Library_Management_System.exceptions.UserLogicException;
import com.github.fge.jsonpatch.JsonPatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    BookRepository bookRepository;

    @Override
    public List<Book> findAll() {

        return bookRepository.findAll();
    }

    @Override
    public Book addBooks(BookDto bookDto) {

        if ( bookDto == null){
            throw new IllegalArgumentException("argument cannot be null");
        }

        Optional<Book> query = bookRepository.findByCategory(bookDto.getCategory());
        if (query.isPresent()){
            return query.get();
        }

        Book book = new Book();
        book.setTitle("java");
        book.setAuthor("daitel");
        book.setPageCount(700);
        book.setDescription("programming language");
        book.setCategory("software engineering");

        return bookRepository.save(book);
    }
    private Book saveOrUpdate(Book book) throws BookLogicException {
        if ( book == null){
            throw new BookLogicException("book cannot be null");
        }

        return bookRepository.save(book);
    }


    @Override
    public Book findById(Long id) throws BookDoesNotExistException {
        if ( id == null){
            throw new  BookDoesNotExistException("argument  cannot be null");
        }

        Optional<Book> query = bookRepository.findById(id);
        if ( query.isPresent()){
            return query.get();
        }

        throw new BookDoesNotExistException("book with id" + id + "does not exist");
    }

    @Override
    public Book updateBookRecords(Long id, JsonPatch book) {
        return null;
    }

    @Override
    public void delete(Book book) {

        bookRepository.delete(book);

    }
}
