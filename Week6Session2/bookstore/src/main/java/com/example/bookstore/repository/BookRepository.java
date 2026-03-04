package com.example.bookstore.repository;

import com.example.bookstore.entity.Book;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    // find all books by specific author
    List<Book> findByAuthor_Id(Integer id);

    // find book title containing a keyword %anything%
    List<Book> findByTitleContaining(String title);

    //get the count of books per author
    @Query("SELECT a.name, COUNT(b) FROM Book b JOIN b.author a GROUP BY a.name")
    List<Object[]> countBooksPerAuthor();

    // join fetch
    @Query("SELECT b FROM Book b JOIN FETCH b.author")
    List<Book> findAllBooksWithAuthor();

    @EntityGraph(attributePaths = {"author"})
    List<Book> findAll();
}
