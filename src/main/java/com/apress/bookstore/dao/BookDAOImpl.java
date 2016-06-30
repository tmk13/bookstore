package com.apress.bookstore.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;

import com.apress.bookstore.model.Author;
import com.apress.bookstore.model.Book;
import com.apress.bookstore.model.Category;

@Service
public class BookDAOImpl implements BookDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Book> findBookByCategory(String category) {
		Map<Long, Book> bookMap = new HashMap<>();

		String bookSql = "SELECT book.ID, book.BOOK_TITLE, book.PUBLISHER, book.PRICE FROM book"
				+ " JOIN book_category ON book.ID = book_category.BOOK_ID"
				+ " JOIN category ON book_category.CATEGORY_ID = category.ID"
				+ " WHERE category.DESCRIPTION_CATEGORY = ?";
		jdbcTemplate.query(bookSql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement preparedStatement) throws SQLException {
				preparedStatement.setString(1, category.trim());
			}
		}, new BookRowCallbackHandler(bookMap));

		String authorSql = "SELECT author.ID, author.FIRST_NAME, author.LAST_NAME, book_author.BOOK_ID FROM book_author"
				+ " JOIN author ON book_author.AUTHOR_ID = author.ID"
				+ " JOIN book ON book_author.BOOK_ID = book.ID"
				+ " JOIN book_category ON book.ID = book_category.BOOK_ID"
				+ " JOIN category ON book_category.CATEGORY_ID = category.ID"
				+ " WHERE category.DESCRIPTION_CATEGORY = ?";
		jdbcTemplate.query(authorSql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement preparedStatement) throws SQLException {
				preparedStatement.setString(1, category.trim());
			}
		}, new AuthorRowCallbackHandler(bookMap));

		String categorySql = "SELECT category.ID, category.DESCRIPTION_CATEGORY, book_category.BOOK_ID FROM book_category"
				+ " JOIN category ON book_category.CATEGORY_ID = category.ID"
				+ " WHERE category.DESCRIPTION_CATEGORY = ?";
		jdbcTemplate.query(categorySql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement preparedStatement) throws SQLException {
				preparedStatement.setString(1, category.trim());
			}
		}, new CategoryRowCallbackHandler(bookMap));

		return new ArrayList<Book>(bookMap.values());
	}

	@Override
	public List<Book> findAllBooks() {
		Map<Long, Book> bookMap = new HashMap<>();

		String bookSql = "SELECT book.ID, book.BOOK_TITLE, book.PUBLISHER, book.PRICE FROM book";
		jdbcTemplate.query(bookSql, new BookRowCallbackHandler(bookMap));

		String authorSql = "SELECT author.ID, author.FIRST_NAME, author.LAST_NAME, book_author.BOOK_ID FROM author"
				+ " JOIN book_author ON author.ID = book_author.AUTHOR_ID";
		jdbcTemplate.query(authorSql, new AuthorRowCallbackHandler(bookMap));

		String categorySql = "SELECT category.ID, category.DESCRIPTION_CATEGORY, book_category.BOOK_ID FROM book_category"
				+ " JOIN category ON book_category.CATEGORY_ID = category.ID";
		jdbcTemplate.query(categorySql, new CategoryRowCallbackHandler(bookMap));

		return new ArrayList<Book>(bookMap.values());
	}

	@Override
	public List<Book> searchBooksByKeyword(String keyWord) {
		Map<Long, Book> bookMap = new HashMap<>();

		String bookSql = "SELECT book.ID, book.BOOK_TITLE, book.PUBLISHER, book.PRICE FROM book"
				+ " JOIN book_author ON book.ID = book_author.BOOK_ID"
				+ " JOIN author ON book_author.AUTHOR_ID = author.ID"
				+ " WHERE book.BOOK_TITLE LIKE ?"
				+ " OR author.FIRST_NAME LIKE ?"
				+ " OR author.LAST_NAME LIKE ?";
		jdbcTemplate.query(bookSql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement preparedStatement) throws SQLException {
				preparedStatement.setString(1, "%" + keyWord.trim() + "%");
				preparedStatement.setString(2, "%" + keyWord.trim() + "%");
				preparedStatement.setString(3, "%" + keyWord.trim() + "%");
			}
		}, new BookRowCallbackHandler(bookMap));

		String authorSql = "SELECT author.ID, author.FIRST_NAME, author.LAST_NAME, book_author.BOOK_ID FROM book_author"
				+ " JOIN author ON book_author.AUTHOR_ID = author.ID"
				+ " JOIN book ON book_author.BOOK_ID = book.ID"
				+ " WHERE book.BOOK_TITLE LIKE ?"
				+ " OR author.FIRST_NAME LIKE ?"
				+ " OR author.LAST_NAME LIKE ?";
		jdbcTemplate.query(authorSql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement preparedStatement) throws SQLException {
				preparedStatement.setString(1, "%" + keyWord.trim() + "%");
				preparedStatement.setString(2, "%" + keyWord.trim() + "%");
				preparedStatement.setString(3, "%" + keyWord.trim() + "%");
			}
		}, new AuthorRowCallbackHandler(bookMap));

		String categorySql = "SELECT category.ID, category.DESCRIPTION_CATEGORY, book_category.BOOK_ID FROM book_category"
				+ " JOIN category ON book_category.CATEGORY_ID = category.ID"
				+ " JOIN book ON book_category.BOOK_ID = book.ID"
				+ " JOIN book_author ON book.ID = book_author.BOOK_ID"
				+ " JOIN author ON book_author.AUTHOR_ID = author.ID"
				+ " WHERE book.BOOK_TITLE LIKE ?"
				+ " OR author.FIRST_NAME LIKE ?"
				+ " OR author.LAST_NAME LIKE ?";
		jdbcTemplate.query(categorySql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement preparedStatement) throws SQLException {
				preparedStatement.setString(1, "%" + keyWord.trim() + "%");
				preparedStatement.setString(2, "%" + keyWord.trim() + "%");
				preparedStatement.setString(3, "%" + keyWord.trim() + "%");
			}
		}, new CategoryRowCallbackHandler(bookMap));

		return new ArrayList<Book>(bookMap.values());
	}

	@Override
	public List<Category> findAllCategories() {
		List<Category> result = new ArrayList<>();
		String sql = "SELECT * FROM category";

		result = jdbcTemplate.query(sql, new CategoryRowMapper());

		return result;
	}

	@Override
	public List<Author> findAllAuthors() {
		List<Author> authorList = new ArrayList<>();

		String sql = "SELECT * FROM author";

		authorList = jdbcTemplate.query(sql, new AuthorRowMapper());

		return authorList;
	}

	@Override
	public boolean insert(Book book) {

		String bookSql = "INSERT INTO book(BOOK_TITLE, PUBLISHER, PRICE) VALUES(?, ?, ?)";

		int bookRows = jdbcTemplate.update(bookSql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement preparedStatement) throws SQLException {
				preparedStatement.setString(1, book.getBookTitle().trim());
				preparedStatement.setString(2, book.getPublisherName().trim());
				preparedStatement.setFloat(3, book.getPrice());
			}
		});

		setBookId(book);
		
		setAuthorsIds(book.getAuthors());

		int authorsRows = 0;
		if (bookRows == 1)
			for(Author author : book.getAuthors()) {
				String authorsSql = "INSERT INTO book_author(BOOK_ID, AUTHOR_ID) VALUES(?, ?)";

				authorsRows += jdbcTemplate.update(authorsSql, new PreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement preparedStatement) throws SQLException {
						preparedStatement.setLong(1, book.getId());
						preparedStatement.setLong(2, author.getId());
					}
				});
			}
		else
			return false;

		setCategoriesIds(book.getCategories());

		int categoriesRows = 0;
		if (authorsRows == book.getAuthors().size())
			for(Category category : book.getCategories()) {
				String categoriesSql = "INSERT INTO book_category(BOOK_ID, CATEGORY_ID) VALUES(?, ?)";
				
				categoriesRows += jdbcTemplate.update(categoriesSql, new PreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement preparedStatement) throws SQLException {
						preparedStatement.setLong(1, book.getId());
						preparedStatement.setLong(2, category.getId());
					}
				});
			}
		else
			return false;

		return categoriesRows == book.getCategories().size() ? true : false;
	}

	private void setBookId(Book book) {

		String sql = "SELECT book.ID FROM book WHERE book.BOOK_TITLE = ? AND book.PUBLISHER = ? AND book.PRICE = ?";

		book.setId(jdbcTemplate.query(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement preparedStatement) throws SQLException {
				preparedStatement.setString(1, book.getBookTitle().trim());
				preparedStatement.setString(2, book.getPublisherName().trim());
				preparedStatement.setFloat(3, book.getPrice());
			}
		}, new ResultSetExtractor<Long>() {
			@Override
			public Long extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next())
					return rs.getLong("ID");
				return -1l;
			}
		}));
	}

	private void setAuthorsIds(List<Author> authors) {

		for (Author author : authors) {
			String sql = "SELECT author.ID FROM author WHERE author.FIRST_NAME = ? AND author.LAST_NAME = ?";

			author.setId(jdbcTemplate.query(sql, new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement preparedStatement) throws SQLException {
					preparedStatement.setString(1, author.getFirstName().trim());
					preparedStatement.setString(2, author.getLastName().trim());
				}
			}, new ResultSetExtractor<Long>() {
				@Override
				public Long extractData(ResultSet rs) throws SQLException, DataAccessException {
					if (rs.next())
						return rs.getLong("ID");
					return -1l;
				}
			}));
		}
	}

	private void setCategoriesIds(List<Category> categories) {

		for (Category category : categories) {
			String sql = "SELECT category.ID FROM category WHERE category.DESCRIPTION_CATEGORY = ?";

			category.setId(jdbcTemplate.query(sql, new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement preparedStatement) throws SQLException {
					preparedStatement.setString(1, category.getCategoryDescription().trim());
				}
			}, new ResultSetExtractor<Long>() {
				@Override
				public Long extractData(ResultSet rs) throws SQLException, DataAccessException {
					if (rs.next())
						return rs.getLong("ID");
					return -1l;
				}
			}));
		}
	}

	@Override
	public void update(Book book) {
	}

	@Override
	public void delete(Long bookId) {
	}

}