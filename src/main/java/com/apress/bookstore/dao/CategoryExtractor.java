package com.apress.bookstore.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.apress.bookstore.model.Category;
import com.apress.bookstore.model.CategoryEntity;

public class CategoryExtractor implements ResultSetExtractor<Category> {  

	@Override
	public Category extractData(ResultSet resultSet) throws SQLException,  
			DataAccessException {

		Category category = new CategoryEntity();
		category.setId(resultSet.getLong("ID"));
		category.setCategoryDescription(resultSet.getString("DESCRIPTION_CATEGORY"));

		return category;
	}  

}

