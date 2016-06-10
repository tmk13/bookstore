package com.apress.bookstore.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import com.apress.bookstore.dao.BookDAO;
import com.apress.bookstore.entity.Book;
import com.apress.bookstore.entity.Category;
import com.apress.springmvc.configuration.DBManager;

public class BookService {
	
	@Autowired
	private BookDAO bookDao;

	public List<Book> getAllBooksList() {
		EntityManager em = DBManager.getManager().createEntityManager();
		@SuppressWarnings("unchecked")
		List<Book> allBooks = em.createNamedQuery("Book.findAll").getResultList();
		
		//List<Book> allBooks = bookDao.findAllBooks();
		return allBooks;
	}
	public List<Category> getCategoryList() {
		EntityManager em = DBManager.getManager().createEntityManager();
		@SuppressWarnings("unchecked")
		List<Category> catList = em.createNamedQuery("Category.findAll").getResultList();

		//List<Category> catList = bookDao.findAllCategories();

		return catList;
	}

	public List<Book> getBooksByCategoryList(String category) {
		EntityManager em = DBManager.getManager().createEntityManager();
		@SuppressWarnings("unchecked")
		List<Book> allBooks = em.createNamedQuery("Book.findByCategory").setParameter("category", category)
				.getResultList();

		// delete all
		// first add
		// second add

		//List<Book> allBooks = bookDao.findBookByCategory(category);
		return allBooks;
	}

	public List<Book> getBooksByKeyWordList(String keyWord) {
		EntityManager em = DBManager.getManager().createEntityManager();
		@SuppressWarnings("unchecked")
		List<Book> allBooks = em.createNamedQuery("Book.findByKeyWord").setParameter("keyWord", "%" + keyWord + "%")
				.getResultList();

		//List<Book> allBooks = bookDao.searchBooksByKeyword(keyWord);
		return allBooks;
	}

	public Book createBook(Book book) {
		EntityManager em = DBManager.getManager().createEntityManager();
		em.getTransaction().begin();
		book.setId(null);
		em.merge(book);
		em.getTransaction().commit();
		em.refresh(book);
		em.close();
		return book;

		/*
		Book book = new BookEntity();
		
		book.setBookTitle(b.getBookTitle());
		book.setPublisherName(b.getPublisherName());
		book.setPrice(b.getPrice());
		book.setAuthors(b.getAuthors());
		book.setCategories(b.getCategories());
		
		bookDao.insert(book);
		
		return book;
		*/
	}

}