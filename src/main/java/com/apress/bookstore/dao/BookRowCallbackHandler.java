package com.apress.bookstore.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.springframework.jdbc.core.RowCallbackHandler;

import com.apress.bookstore.model.Book;
import com.apress.bookstore.model.BookEntity;

class BookRowCallbackHandler implements RowCallbackHandler {

	private final Map<Long, Book> bookMap;

	public BookRowCallbackHandler(Map<Long, Book> bookMap) {
		this.bookMap = bookMap;
	}

	@Override
	public void processRow(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("ID");
		Book book = bookMap.get(id);
		if (book == null) {
			book = new BookEntity();
			book.setId(id);
			book.setBookTitle(resultSet.getString("BOOK_TITLE"));
			book.setPublisherName(resultSet.getString("PUBLISHER"));
			book.setPrice(resultSet.getFloat("PRICE"));
			bookMap.put(id, book);
		}
	}
}