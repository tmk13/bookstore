package com.apress.bookstore.repository;

import com.apress.bookstore.entity.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface AuthorRepository extends CrudRepository<Author, Long>{
}
