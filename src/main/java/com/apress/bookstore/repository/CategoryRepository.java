package com.apress.bookstore.repository;

import com.apress.bookstore.entity.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface CategoryRepository extends CrudRepository<Category, Long> {
}
