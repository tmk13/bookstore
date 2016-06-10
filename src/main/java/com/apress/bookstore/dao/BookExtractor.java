package com.apress.bookstore.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowCallbackHandler;

import com.apress.bookstore.model.Book;
import com.apress.bookstore.model.BookEntity;


public class BookExtractor implements ResultSetExtractor<List<Book>> {
	  
	private Map<Long, Book> map;

	public BookExtractor(Map<Long, Book> map) {
		this.map = map;
	}

	 @Override
	public List<Book> extractData(ResultSet resultSet) throws SQLException,
	   DataAccessException {  
	    
		//Book book = new BookBean();
		//Author author = new AuthorBean();
		//List<Author> authorList = new ArrayList<Author>();
		//book.setId(resultSet.getLong("ID"));
		//book.setBookTitle(resultSet.getString("BOOK_TITLE"));
		//book.setPublisherName(resultSet.getString("PUBLISHER"));
		// book.setCategoryId(resultSet.getLong("id_kategorii"));
		//author.setBookId(resultSet.getLong("id_książki"));
		//author.setFirstName(resultSet.getString("FIRST_NAME"));
		//author.setLastName(resultSet.getString("LAST_NAME"));
		//authorList.add(author);
		//  book.setAuthors(authorList);
		// book.setPrice(resultSet.getDouble("cena"));

		//Map<Long, Book> map = new HashMap<>();
		BookRowCallbackHandler2 bookRowCallbackHandler = new BookRowCallbackHandler2(map);
		bookRowCallbackHandler.processRow(resultSet);
		return new ArrayList<Book>(map.values());

		//return book;  
	 }  
	  
	}  

class BookRowCallbackHandler2 implements RowCallbackHandler {

	private final Map<Long, Book> bookMap;

	public BookRowCallbackHandler2(Map<Long, Book> bookMap) {
		this.bookMap = bookMap;
	}

	@Override
	public void processRow(ResultSet resultSet) throws SQLException {
		while (resultSet.next()) {
			Long id = resultSet.getLong("ID");
			Book book = bookMap.get(id);
			if (book == null) {
				book = new BookEntity();
				book.setId(id);
				book.setBookTitle(resultSet.getString("BOOK_TITLE"));
				book.setPublisherName(resultSet.getString("PUBLISHER"));
				bookMap.put(id, book);
			}
		}
	}
}

