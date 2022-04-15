package com.Library_Management_System.service;

import com.Library_Management_System.datas.model.Book;
import com.Library_Management_System.datas.model.User;
import com.Library_Management_System.datas.repository.BookRepository;
import com.Library_Management_System.dtos.BookDto;
import com.Library_Management_System.exceptions.BookDoesNotExistException;
import com.Library_Management_System.exceptions.BookLogicException;
import com.Library_Management_System.exceptions.UserLogicException;
import com.Library_Management_System.service.cloud.CloudinaryService;
import com.cloudinary.utils.ObjectUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    BookRepository bookRepository;

    @Autowired
            @Qualifier("cloudinary-service")
    CloudinaryService cloudinaryService;

    @Override
    public List<Book> findAll() {

        return bookRepository.findAll();
    }

    @Override
    public Book addBookToFavorite(BookDto bookDto) throws BookLogicException {

        if ( bookDto == null){
            throw new IllegalArgumentException("argument cannot be null");
        }
        Optional<Book> query = bookRepository.findByCategory(bookDto.getCategory());
        if (query.isPresent()){
           // throw new BookLogicException("book category exists" + bookDto.getCategory());
            return query.get();
        }

        Book book = new Book();
//        try {
//            if (bookDto.getImage() != null) {
//                Map<?, ?> getUpload = cloudinaryService.upload(bookDto.getImage().getBytes(),
//                        ObjectUtils.asMap("public_id", "inventory/" + bookDto.getImage().getOriginalFilename()));
//                book.setImageUrl(getUpload.get("url").toString());
//            }
//        }
//
//        catch (IOException e){
//            e.printStackTrace();
//        }

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
    public Book findBookById(Long id) throws BookDoesNotExistException {
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
    public Book updateBookRecords(Long id, JsonPatch bookPatch) throws BookLogicException{

            Optional<Book> bookQuery = bookRepository.findById(id);

            if( bookQuery.isEmpty()){
                throw new IllegalArgumentException("user with id" + id + "does not exist");
            }

            Book book1 = bookQuery.get();

            try{
                book1 = applyPatchToBook(bookPatch, book1);
                return saveOrUpdate(book1);
            }

            catch(JsonPatchException | JsonProcessingException | BookLogicException je){
                throw new BookLogicException("update failed");
            }
        }

        private Book applyPatchToBook(JsonPatch productPatch, Book book1) throws JsonProcessingException, JsonPatchException{

            ObjectMapper objectMapper = new ObjectMapper();

            JsonNode patched = productPatch.apply(objectMapper.convertValue(book1, JsonNode.class));

            return objectMapper.treeToValue(patched, Book.class);
        }

    @Override
    public void delete(Book book) {

        bookRepository.delete(book);

    }
}
