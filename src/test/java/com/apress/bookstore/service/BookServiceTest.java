package com.apress.bookstore.service;

import com.apress.bookstore.dto.BookFormDTO;
import com.apress.bookstore.entity.Author;
import com.apress.bookstore.entity.Book;
import com.apress.bookstore.entity.Category;
import com.apress.bookstore.repository.BookRepository;
import com.apress.bookstore.validator.BookValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.validation.BindingResult;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

//@WebAppConfiguration
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {AppConfiguration.class, EntityManagerFactoriesConfiguration.class, SecurityConfig.class, SpringSecurityInitializer.class,
//        TransactionManagersConfig.class})
@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {

	//    @Autowired
	//    private BookService bookService;

	@Mock
	private BookRepository bookRepository;
	@Mock
	private BookValidator bookValidator;

	//	@InjectMocks
	private BookService bookService;

	@Before
	public void setUp() {
		bookService = new BookService(bookRepository, bookValidator);

		HashSet<Author> authors = new HashSet<>();
		authors.add(new Author(1l, "test firstName", "test lastName"));
		HashSet<Category> categories = new HashSet<>();
		categories.add(new Category(1l, "test category", null));

		Mockito.when(bookRepository.findAll()).thenReturn(createBookByAmountAndCategory(10, "Java"));

		Mockito.when(bookRepository.findByCategory(Matchers.anyString())).thenAnswer(new Answer<Set<Book>>() {
			@Override
			public Set<Book> answer(InvocationOnMock invocation) throws Throwable {
				return createBookByAmountAndCategory(5, (String) invocation.getArguments()[0]);
			}
		});

		Mockito.when(bookRepository.findByKeyWord(Matchers.anyString())).thenAnswer(new Answer<Set<Book>>() {
			@Override
			public Set<Book> answer(InvocationOnMock invocation) throws Throwable {
				return createBookByAmountAndKeyWord(5, (String) invocation.getArguments()[0]);
			}
		});

		Mockito.when(bookRepository.findOne(Matchers.anyLong())).thenAnswer(new Answer<Book>() {
			@Override
			public Book answer(InvocationOnMock invocation) throws Throwable {
				return new Book((Long) invocation.getArguments()[0], "test book", "test publisher", 10.0f, authors, categories, null);
			}
		});

		Mockito.when(bookRepository.save(Matchers.any(Book.class))).thenAnswer(new Answer<Book>() {
			@Override
			public Book answer(InvocationOnMock invocation) throws Throwable {
				Book book = invocation.getArgumentAt(0, Book.class);
				return new Book(1L, book.getBookTitle(), book.getPublisherName(), book.getPrice(),
						book.getAuthors(), book.getCategories(), book.getImage());
			}
		});

	}

	@Test
	public void isBookServiceAutowired() {
		assertNotNull(bookService);
		assertNotNull(bookRepository);
		assertNotNull(bookValidator);
	}

	@Test
	public void getAllBooksList() {
		Set<Book> booksByAmount = createBookByAmountAndCategory(10, "Java");
		Set<Book> returnedBooks = bookService.getAllBooksList();

		assertNotNull(returnedBooks);
		assertThat(returnedBooks, is(equalTo(booksByAmount)));
	}

	@Test
	public void getAllBooksListByAmountOfTwenty() {
		Mockito.when(bookRepository.findAll()).thenReturn(createBookByAmountAndCategory(20, "Java"));

		Set<Book> returnedBooks = bookService.getAllBooksList();
		Set<Book> booksByAmount = createBookByAmountAndCategory(20, "Java");

		assertNotNull(returnedBooks);
		assertThat(returnedBooks.size(), is(equalTo(booksByAmount.size())));
	}

	@Test
	public void getBooksByCategoryList() {
		Set<Book> booksByCategory = createBookByAmountAndCategory(5, "Java");
		Set<Book> returnedBooks = bookService.getBooksByCategoryList("Java");

		assertNotNull(returnedBooks);
		assertThat(returnedBooks, is(equalTo(booksByCategory)));
		for (Book returnedBook : returnedBooks) {
			assertThat(returnedBook.getCategories(), is(equalTo(booksByCategory.iterator().next().getCategories())));
		}
	}

	@Test
	public void getBooksByKeyWordList() {
		Set<Book> booksByKeyWord = createBookByAmountAndKeyWord(5, "some");

		Set<Book> returnedBooks = bookService.getBooksByKeyWordList("some");

		assertNotNull(returnedBooks);
		for (Book returnedBook : returnedBooks) {
			assertThat(returnedBook.getBookTitle().contains("some"), is(equalTo(booksByKeyWord.iterator().next().getBookTitle().contains("some"))));
		}
	}

	@Test
	public void getBookById() {
		Book book = bookService.getBookById(50l);

		assertNotNull(book);
		assertThat(book.getId(), is(equalTo(new Long(50))));
		assertThat(book.getId(), is(not(equalTo(new Long(49)))));
		assertThat(book.getId(), is(not(equalTo(null))));
	}

	@Test
	public void getBookDTOById() {
		BookFormDTO bookFormDTO = bookService.getBookDTOById(50l);

		assertNotNull(bookFormDTO);
		assertThat(bookFormDTO.getId(), is(equalTo(new Long(50))));
		assertThat(bookFormDTO.getId(), is(not(equalTo(new Long(49)))));
		assertThat(bookFormDTO.getId(), is(not(equalTo(null))));
	}

	@Test
	public void saveBook() {
		Set<Author> authors = getAuthors();
		Set<Category> categories = getCategories();

		Book bookToSave = new Book("test book", "test publisher", 10.0f, authors, categories, null);

		Book returnedBook = bookService.saveBook(bookToSave);

		assertNotNull(returnedBook);
		assertThat(returnedBook.getBookTitle(), is(equalTo(bookToSave.getBookTitle())));
		assertThat(returnedBook.getPublisherName(), is(equalTo(bookToSave.getPublisherName())));
		assertThat(returnedBook.getPrice(), is(equalTo(bookToSave.getPrice())));
		assertThat(returnedBook.getAuthors(), is(equalTo(bookToSave.getAuthors())));
		assertThat(returnedBook.getCategories(), is(equalTo(bookToSave.getCategories())));
		assertThat(returnedBook.getImage(), is(equalTo(bookToSave.getImage())));
	}

	@Test
	public void saveBookFromDTO() {
		Set<Author> authors = getAuthors();
		Set<Category> categories = getCategories();

		BookFormDTO bookDTOToSave = new BookFormDTO("test book", "test publisher", 10.0f, authors, categories, null);

		Book returnedBook = bookService.saveBookFromDTO(bookDTOToSave);

		assertNotNull(returnedBook);
		assertThat(returnedBook.getBookTitle(), is(equalTo(bookDTOToSave.getBookTitle())));
		assertThat(returnedBook.getPublisherName(), is(equalTo(bookDTOToSave.getPublisherName())));
		assertThat(returnedBook.getPrice(), is(equalTo(bookDTOToSave.getPrice())));
		assertThat(returnedBook.getAuthors(), is(equalTo(bookDTOToSave.getAuthors())));
		assertThat(returnedBook.getCategories(), is(equalTo(bookDTOToSave.getCategories())));
		assertThat(returnedBook.getImage(), is(equalTo(bookDTOToSave.getImage())));
	}

	@Test
	public void scaleImage() {
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream("/media/veracrypt3/git-workspace/bookstore/src/main/webapp/resources/pl.png");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		String returnedImage = null;
		try {
			returnedImage = bookService.scaleImage(new MockMultipartFile("MockFile", "mock.png", "image/png", fileInputStream));
		} catch (IOException e) {
			e.printStackTrace();
		}

		assertNotNull(returnedImage);
	}

	@Test
	public void validateBookTrue() {
		Set<Author> authors = getAuthors();
		Set<Category> categories = getCategories();

		BookFormDTO bookDTOToSave = new BookFormDTO("test book", "test publisher", 10.0f, authors, categories, null);

		BindingResult result = mock(BindingResult.class);

		Mockito.when(result.hasErrors()).thenReturn(false);

		assertTrue(bookService.validateBook(bookDTOToSave, result));
	}

	@Test
	public void validateBookEmptyTitle() {
		Set<Author> authors = getAuthors();
		Set<Category> categories = getCategories();

		BookFormDTO bookDTOToSave = new BookFormDTO("", "test publisher", 10.0f, authors, categories, null);

		BindingResult result = mock(BindingResult.class);

		Mockito.when(!result.hasErrors()).thenReturn(true);

		assertFalse(bookService.validateBook(bookDTOToSave, result));
	}

	@Test
	public void validateBookEmptyPublisher() {
		Set<Author> authors = getAuthors();
		Set<Category> categories = getCategories();

		BookFormDTO bookDTOToSave = new BookFormDTO("test book", "", 10.0f, authors, categories, null);

		BindingResult result = mock(BindingResult.class);

		Mockito.when(!result.hasErrors()).thenReturn(true);

		assertFalse(bookService.validateBook(bookDTOToSave, result));
	}

	@Test
	public void validateBookNullPrice() {
		Set<Author> authors = getAuthors();
		Set<Category> categories = getCategories();

		BookFormDTO bookDTOToSave = new BookFormDTO("test book", "test publisher", null, authors, categories, null);

		BindingResult result = mock(BindingResult.class);

		Mockito.when(!result.hasErrors()).thenReturn(true);

		assertFalse(bookService.validateBook(bookDTOToSave, result));
	}

	@Test
	public void validateBookNullAuthors() {
		Set<Category> categories = getCategories();

		BookFormDTO bookDTOToSave = new BookFormDTO("test book", "test publisher", 10.0f, null, categories, null);

		BindingResult result = mock(BindingResult.class);

		Mockito.when(!result.hasErrors()).thenReturn(true);

		assertFalse(bookService.validateBook(bookDTOToSave, result));
	}

	@Test
	public void validateBookNullCategories() {
		Set<Author> authors = getAuthors();

		BookFormDTO bookDTOToSave = new BookFormDTO("test book", "test publisher", 10.0f, authors, null, null);

		BindingResult result = mock(BindingResult.class);

		Mockito.when(!result.hasErrors()).thenReturn(true);

		assertFalse(bookService.validateBook(bookDTOToSave, result));
	}

	@Test
	public void createBook() {
		Set<Author> authors = getAuthors();
		Set<Category> categories = getCategories();

		Book book = new Book(1L, "test book", "test publisher", 10.0f, authors, categories, null);
		BookFormDTO bookDTOToSave = new BookFormDTO("test book", "test publisher", 10.0f, authors, categories, null);

		Book returnedBook = bookService.createBook(bookDTOToSave);

		assertThat(returnedBook.getBookTitle(), is(equalTo(book.getBookTitle())));
		assertThat(returnedBook.getPublisherName(), is(equalTo(book.getPublisherName())));
		assertThat(returnedBook.getPrice(), is(equalTo(book.getPrice())));
		assertThat(returnedBook.getAuthors(), is(equalTo(book.getAuthors())));
		assertThat(returnedBook.getCategories(), is(equalTo(book.getCategories())));
		assertThat(returnedBook.getImage(), is(equalTo(book.getImage())));
	}

	private Set<Category> getCategories() {
		Set<Category> categories = new LinkedHashSet<>();
		categories.add(new Category(1l, "test category", null));
		return categories;
	}

	private Set<Author> getAuthors() {
		Set<Author> authors = new LinkedHashSet<>();
		authors.add(new Author(1l, "test first name", "test last name"));
		return authors;
	}

	private Set<Book> createBookByAmountAndCategory(int amount, String category) {
		Set<Book> books = new LinkedHashSet<>();

		Set<Author> authors = getAuthors();

		Set<Category> categories = new LinkedHashSet<>();
		categories.add(new Category(1l, category, null));


		for (int i = 1; i <= amount; ++i) {
			books.add(new Book((long)i, "test book " + i, "test publisher " + 1, (float)((Math.random() * 10) + 5), authors, categories, null ));
		}

		return books;
	}

	private Set<Book> createBookByAmountAndKeyWord(int amount, String keyWord) {
		Set<Book> books = new LinkedHashSet<>();
		Set<Category> categories = new LinkedHashSet<>();
		categories.add(new Category(1l, "Java", null));

		for (int i = 1; i <= amount; ++i) {
			books.add(new Book((long)i, "test book " + keyWord + " " + i, "test publisher " + 1, (float)((Math.random() * 10) + 5), null, categories, null ));
		}

		return books;
	}

}
