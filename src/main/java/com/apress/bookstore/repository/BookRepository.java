package com.apress.bookstore.repository;

import com.apress.bookstore.entity.Book;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Transactional(readOnly = true)
public interface BookRepository extends CrudRepository<Book, Long> {

    @Query("SELECT DISTINCT b FROM Book b"
            + " JOIN FETCH b.categories cats"
            + " WHERE cats.categoryDescription = :category")
    Set<Book> findByCategory(@Param("category") String category);

    @Query("SELECT DISTINCT b FROM Book b"
            + " JOIN b.authors auths"
            + " WHERE b.bookTitle LIKE %?1%"
            + " OR auths.firstName LIKE %?1%"
            + " OR auths.lastName LIKE %?1%")
    Set<Book> findByKeyWord(String keyWord);
}
