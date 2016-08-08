package com.apress.bookstore.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import com.apress.bookstore.dto.BookFormDTO;
import com.apress.bookstore.entity.Book;
import com.apress.bookstore.repository.BookRepository;
import com.apress.bookstore.validator.BookValidator;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private BookValidator bookValidator;
	//	@PersistenceContext
	//	private EntityManager entityManager;

	@Transactional(readOnly = true)
	public Set<Book> getAllBooksList() {
		return bookRepository.findAll();
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
	public Set<Book> getBooksByCategoryList(String category) {
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
	public Set<Book> getBooksByKeyWordList(String keyWord) {
		return bookRepository.findByKeyWord(keyWord);
	}

	@Transactional(readOnly = true)
	public Book getBookById(Long id) {
		return bookRepository.findOne(id);
	}

	@Transactional
	public BookFormDTO getBookDTOById(Long id) {

		Book book = getBookById(id);

		BookFormDTO bookFormDTO = new BookFormDTO.Builder()
				.withId(book.getId())
				.withBookTitle(book.getBookTitle())
				.withPublisherName(book.getPublisherName())
				.withPrice(book.getPrice())
				.withAuthors(book.getAuthors())
				.withCategories(book.getCategories())
				.withImage(book.getImage())
				.build();

		return bookFormDTO;
	}

	@Transactional
	public Book saveBook(Book book) {
		return bookRepository.save(book);
	}

	@Transactional
	public Book saveBookFromDTO(BookFormDTO bookFormDTO) {
		Book book = getBookById(bookFormDTO.getId());

		book.setBookTitle(bookFormDTO.getBookTitle());
		book.setPublisherName(bookFormDTO.getPublisherName());
		book.setPrice(bookFormDTO.getPrice());
		book.setAuthors(bookFormDTO.getAuthors());
		book.setCategories(bookFormDTO.getCategories());
		book.setImage(bookFormDTO.getImage());

		return saveBook(book);
	}

	public String scaleImage(MultipartFile file) {
		try {
			byte[] bytes = file.getBytes();

			ByteArrayInputStream in = new ByteArrayInputStream(bytes);
			BufferedImage bufferedImage = ImageIO.read(in);
			bufferedImage = Scalr.resize(bufferedImage, Scalr.Method.AUTOMATIC, 500, 300);

			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			ImageIO.write(bufferedImage, "png", outputStream);
			outputStream.flush();
			byte[] resizedBytes = outputStream.toByteArray();
			outputStream.close();

			String image = Base64.encodeBase64String(resizedBytes);

			return image;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public boolean downloadImage(Long id, HttpServletResponse response) {

		Book book = getBookById(id);

		if(book != null) {

			String image = book.getImage();

			byte[] bytes = Base64.decodeBase64(image);

			response.setContentType("image/png");
			response.setContentLength(bytes.length);

			String headerValue = String.format("attachment; filename=\"%s\"", book.getBookTitle() + ".png");
			response.setHeader("Content-Disposition", headerValue);

			try {
				IOUtils.copy(new ByteArrayInputStream(bytes), response.getOutputStream());
				response.flushBuffer();
				return true;
			} catch (IOException e) {
				e.printStackTrace();
				response.setStatus(404);
			}

//			byte[] buffer = new byte[4096];
//			int byteRead;
//
//			try (ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes); OutputStream outputStream = response.getOutputStream()) {
//
//				while ((byteRead = inputStream.read(buffer)) != -1) {
//					outputStream.write(buffer, 0, byteRead);
//				}
//
//				return true;
//
//			} catch (IOException e) {
//				e.printStackTrace();
//			}

		}

		return false;
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

	public boolean validateBook(@Valid BookFormDTO bookFormDTO, BindingResult result) {
		bookValidator.validate(bookFormDTO, result);
		return !result.hasErrors();
	}

	@Transactional
	public Book createBook(BookFormDTO bookFormDTO) {
//		book.setId(null);
//		entityManager.merge(book);

		Book book = new Book(bookFormDTO.getBookTitle(), bookFormDTO.getPublisherName(), bookFormDTO.getPrice(), bookFormDTO.getAuthors(),
				bookFormDTO.getCategories(), bookFormDTO.getImage());

//		book.setBookTitle(bookFormDTO.getBookTitle());
//		book.setPublisherName(bookFormDTO.getPublisherName());
//		book.setPrice(bookFormDTO.getPrice());
//		book.setAuthors(bookFormDTO.getAuthors());
//		book.setCategories(bookFormDTO.getCategories());
//		book.setImage(bookFormDTO.getImage());

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