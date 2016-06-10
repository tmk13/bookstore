package com.apress.bookstore.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.apress.bookstore.model.Author;


public class AuthorRowMapper implements RowMapper<Author> {
	  
	 @Override  
	public Author mapRow(ResultSet resultSet, int line) throws SQLException {
		AuthorExtractor autExtractor = new AuthorExtractor();
		return autExtractor.extractData(resultSet);
	 }  
	  
	}  