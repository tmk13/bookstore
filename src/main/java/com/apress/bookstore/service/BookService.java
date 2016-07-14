package com.apress.bookstore.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.TransactionManager;

import com.apress.bookstore.dto.BookFormDTO;
import com.apress.bookstore.repository.BookRepository;
import com.apress.bookstore.validator.BookValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.apress.bookstore.dao.BookDAO;
import com.apress.bookstore.entity.Book;
import com.apress.bookstore.entity.Category;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private BookValidator bookValidator;
	@PersistenceContext
	private EntityManager entityManager;

	@Secured("ROLE_ADMIN")
	@Transactional(readOnly = true)
	public List<Book> getAllBooksList() {
		return (List<Book>)bookRepository.findAll();
	}
//	public List<Book> getAllBooksList() {
//		EntityManager em = DBManager.getManager().createEntityManager();
//		@SuppressWarnings("unchecked")
//		List<Book> allBooks = em.createNamedQuery("Book.findAll").getResultList();
//
//		//List<Book> allBooks = bookDao.findAllBooks();
//		return allBooks;
//	}
//	public List<Category> getCategoryList() {
//		EntityManager em = DBManager.getManager().createEntityManager();
//		@SuppressWarnings("unchecked")
//		List<Category> catList = em.createNamedQuery("Category.findAll").getResultList();
//
//		//List<Category> catList = bookDao.findAllCategories();
//
//		return catList;
//	}

	@Transactional(readOnly = true)
	public List<Book> getBooksByCategoryList(String category) {
		return bookRepository.findByCategory(category);
	}

//	public List<Book> getBooksByCategoryList(String category) {
//		EntityManager em = DBManager.getManager().createEntityManager();
//		@SuppressWarnings("unchecked")
//		List<Book> allBooks = em.createNamedQuery("Book.findByCategory").setParameter("category", category)
//				.getResultList();
//
//		// delete all
//		// first add
//		// second add
//
//		//List<Book> allBooks = bookDao.findBookByCategory(category);
//		return allBooks;
//	}

	@Transactional(readOnly = true)
	public List<Book> getBooksByKeyWordList(String keyWord) {
		return bookRepository.findByKeyWord(keyWord);
	}

//	public List<Book> getBooksByKeyWordList(String keyWord) {
//		EntityManager em = DBManager.getManager().createEntityManager();
//		@SuppressWarnings("unchecked")
//		List<Book> allBooks = em.createNamedQuery("Book.findByKeyWord").setParameter("keyWord", "%" + keyWord + "%")
//				.getResultList();
//
//		//List<Book> allBooks = bookDao.searchBooksByKeyword(keyWord);
//		return allBooks;
//	}

	public boolean validateBook(BookFormDTO bookFormDTO, BindingResult result) {
		bookValidator.validate(bookFormDTO, result);
		return !result.hasErrors();
	}

	@Transactional
	public Book createBook(BookFormDTO bookFormDTO) {
//		book.setId(null);
//		entityManager.merge(book);

		Book book = new Book();
		book.setBookTitle(bookFormDTO.getBookTitle());
		book.setPublisherName(bookFormDTO.getPublisherName());
		book.setPrice(bookFormDTO.getPrice());
		book.setAuthors(bookFormDTO.getAuthors());
		book.setCategories(bookFormDTO.getCategories());

		return bookRepository.save(book);
	}

//	public Book createBook(Book book) {
//		EntityManager em = DBManager.getManager().createEntityManager();
//		em.getTransaction().begin();
//		book.setId(null);
//		em.merge(book);
//		em.getTransaction().commit();
//		em.refresh(book);
//		em.close();
//		return book;
//
//		/*
//		Book book = new BookEntity();
//
//		book.setBookTitle(b.getBookTitle());
//		book.setPublisherName(b.getPublisherName());
//		book.setPrice(b.getPrice());
//		book.setAuthors(b.getAuthors());
//		book.setCategories(b.getCategories());
//
//		bookDao.insert(book);
//
//		return book;
//		*/
//	}

}