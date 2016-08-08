package com.apress.bookstore.api;

import com.apress.bookstore.dto.BookFormDTO;
import com.apress.bookstore.entity.Book;
import com.apress.bookstore.entity.Category;
import com.apress.bookstore.service.BookService;
import com.apress.bookstore.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Locale;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class BookRestController {

	@Autowired
	private BookService bookService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private SessionLocaleResolver localeResolver;

	@RequestMapping("/bookList.json")
	public Set<Book> bookListController() {
		return  bookService.getAllBooksList();
	}

	@RequestMapping("/allBooks.json")
	public Set<Book> allBooksController() {
		return  bookService.getAllBooksList();
	}

	@RequestMapping("/category.json")
	public Set<Book> byCategoryBooksController(@RequestParam("category") String category) {
		return bookService.getBooksByCategoryList(category);
	}

	@RequestMapping("/searchResult.json")
	public Set<Book> searchBooksController(@RequestParam("keyWord") String keyWord) {
		return bookService.getBooksByKeyWordList(keyWord);
	}

    @RequestMapping("/book.json")
    public Book book(@RequestParam("id") Long id) {
		Book book = bookService.getBookById(id);
        return book;
    }

	@RequestMapping("/bookAdmin.json")
	public BookFormDTO bookAdmin(@RequestParam("id") Long id, HttpServletRequest request) {
		if (request.isUserInRole("ROLE_ADMIN")) {
			BookFormDTO bookFormDTO = bookService.getBookDTOById(id);
			return bookFormDTO;
		}
		return null;
	}

	@RequestMapping(value = "/saveBook.json", method = RequestMethod.POST)
	public BookFormDTO editImage(@RequestBody HttpEntity<BookFormDTO> httpEntity, BindingResult result, HttpServletRequest request) {
		if (request.isUserInRole("ROLE_ADMIN")) {
			BookFormDTO bookFormDTO = httpEntity.getBody();
//			if (result.hasErrors()) {
//				return null;
//			}

			if (!bookService.validateBook(bookFormDTO, result)) {
				return null;
			} else {
				bookService.saveBookFromDTO(bookFormDTO);
				return bookFormDTO;
			}
		}
		return null;
	}

	@RequestMapping(value = "/downloadImage.json")
	public String downloadImage(@RequestParam("id") Long id, HttpServletResponse response) {
		bookService.downloadImage(id, response);
		return null;
	}

	@RequestMapping("/language.json")
	public String language(@RequestParam("language") String language) {
		localeResolver.setDefaultLocale(new Locale(language));
		return null;
	}

	@RequestMapping("/catList.json")
	public Set<Category> catList() {
		return categoryService.getCategoryList();
	}

}
