package com.apress.bookstore.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.apress.bookstore.model.Book;


public class BookRowMapper implements RowMapper<List<Book>> {
	  
	 @Override  
	public List<Book> mapRow(ResultSet resultSet, int line) throws SQLException {
		BookExtractor bookExtractor = new BookExtractor(new HashMap<Long, Book>());
	  return bookExtractor.extractData(resultSet);  
	 }  
	  
	}  