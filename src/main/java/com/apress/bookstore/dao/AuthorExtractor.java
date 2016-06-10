package com.apress.bookstore.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.apress.bookstore.model.Author;
import com.apress.bookstore.model.AuthorEntity;

public class AuthorExtractor implements ResultSetExtractor<Author> {
	  
	@Override
	public Author extractData(ResultSet resultSet) throws SQLException,
	   DataAccessException {  
	    
		Author author = new AuthorEntity();
		author.setId(resultSet.getLong("ID"));
		author.setFirstName(resultSet.getString("FIRST_NAME"));
		author.setLastName(resultSet.getString("LAST_NAME"));
		return author;
	 }  
	  
	}  


