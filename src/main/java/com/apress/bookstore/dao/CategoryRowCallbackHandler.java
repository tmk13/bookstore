package com.apress.bookstore.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowCallbackHandler;

import com.apress.bookstore.model.Book;
import com.apress.bookstore.model.Category;
import com.apress.bookstore.model.CategoryEntity;

class CategoryRowCallbackHandler implements RowCallbackHandler {

	private Map<Long, Book> bookMap;

	public CategoryRowCallbackHandler(Map<Long, Book> bookMap) {
		this.bookMap = bookMap;
	}

	@Override
	public void processRow(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("BOOK_ID");
		Book book = bookMap.get(id);
		if (book != null) {
			List<Category> categoryList = book.getCategories();
			Category category = new CategoryEntity();
			category.setId(resultSet.getLong("ID"));
			category.setCategoryDescription(resultSet
					.getString("DESCRIPTION_CATEGORY"));
			categoryList.add(category);
		}
	}
}