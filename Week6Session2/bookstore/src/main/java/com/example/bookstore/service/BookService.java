package com.example.bookstore.service;

import com.example.bookstore.dto.BookDTO;
import com.example.bookstore.entity.Author;
import com.example.bookstore.entity.Book;
import com.example.bookstore.repository.AuthorRepository;
import com.example.bookstore.repository.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    // get all books;
    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll().stream().map(
                book -> new BookDTO(
                    book.getId(),
                    book.getTitle(),
                    book.getAuthor().getName()
                )
        ).collect(Collectors.toList());
    }

    public List<BookDTO> getAllBooksWithAuthor() {
        return bookRepository.findAllBooksWithAuthor().stream().map(
                book -> new BookDTO(
                        book.getId(),
                        book.getTitle(),
                        book.getAuthor().getName()
                )
        ).collect(Collectors.toList());
    }

    // get books by author

    // create a book
    @Transactional
    public Book createBook(Book book, Integer authorId){
        Author author = authorRepository.findById(authorId).orElse(null);
        book.setAuthor(author);
        return bookRepository.save(book);
    }


}
