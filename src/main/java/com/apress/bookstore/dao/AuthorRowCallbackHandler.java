package com.apress.bookstore.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowCallbackHandler;

import com.apress.bookstore.model.Author;
import com.apress.bookstore.model.AuthorEntity;
import com.apress.bookstore.model.Book;

class AuthorRowCallbackHandler implements RowCallbackHandler {

	private Map<Long, Book> bookMap;

	public AuthorRowCallbackHandler(Map<Long, Book> bookMap) {
		this.bookMap = bookMap;
	}

	@Override
	public void processRow(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("BOOK_ID");
		System.out.println(resultSet.getString("LAST_NAME"));
		Book book = bookMap.get(id);
		if (book != null) {
			List<Author> authorList = book.getAuthors();
			Author author = new AuthorEntity();
			author.setId(resultSet.getLong("ID"));
			author.setFirstName(resultSet.getString("FIRST_NAME"));
			author.setLastName(resultSet.getString("LAST_NAME"));
			authorList.add(author);
		}
	}
}