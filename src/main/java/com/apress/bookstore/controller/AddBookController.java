package com.apress.bookstore.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.apress.bookstore.entity.Author;
import com.apress.bookstore.entity.Book;
import com.apress.bookstore.entity.Category;
import com.apress.bookstore.entity.User;
import com.apress.bookstore.service.AuthorService;
import com.apress.bookstore.service.BookService;
import com.apress.bookstore.service.CategoryService;
import com.apress.bookstore.validator.BookValidator;

@Controller
public class AddBookController {

	@Autowired
	private BookService bookService;
	@Autowired
	private AuthorService authorService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private BookValidator bookValidator;

	@RequestMapping(value = "/addBook.html", method = RequestMethod.GET)
	public ModelAndView initForm(@ModelAttribute("catList") ArrayList<Category> catList, ModelAndView mav) {
		mav.setViewName("addBook");
		mav.addObject("catList", catList);
		Book book = new Book();
		book.setBookTitle("Dodaj książkę :");
		mav.addObject("book", book);
		return mav;
	}


	@InitBinder("book")
	public void initBinder(WebDataBinder binder, WebRequest request) {
		binder.setDisallowedFields(new String[] { "authors", "categories" });
		Book book = (Book) binder.getTarget();
		long authorId = -1;
		long categoryId = -1;

		String[] authorsParams = request.getParameterValues("authors");

		if (authorsParams != null) {
			List<Author> authorList = new ArrayList<>();
			for (String value : authorsParams) {
				authorId = Long.parseLong(value);
				Author author = authorService.getAuthorById(authorId);
				authorList.add(author);
			}
			book.setAuthors(authorList);
		}

		String[] categoriesParams = request.getParameterValues("categories");

		if (categoriesParams != null) {
			List<Category> categoryList = new ArrayList<>();
			for (String value : categoriesParams) {
				categoryId = Long.parseLong(value);
				Category category = categoryService.getCategoryById(categoryId);
				categoryList.add(category);
			}
			book.setCategories(categoryList);
		}
	}

	@ModelAttribute("user")
	public User populateUser() {
		return new User();
	}

	@ModelAttribute("catList")
	public List<Category> catList() {
		return bookService.getCategoryList();
	}

	@ModelAttribute("authorList")
	public List<Author> populateAuthorList() {
		return authorService.getAuthorList();
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView processSubmit(@ModelAttribute("catList") ArrayList<Category> catList,
			@ModelAttribute("book") Book book, BindingResult result,
			SessionStatus status, ModelAndView mav) {
		bookValidator.validate(book, result);
		if (result.hasErrors()) {
			mav.setViewName("addBook");
			mav.addObject("catList", catList);
			return mav;
		} else {
			bookService.createBook(book);
			mav.setViewName("redirect:/bookList.html");
			return mav;
		}

	}
}
