package com.apress.bookstore.controller;

import java.util.ArrayList;
import java.util.List;

import com.apress.bookstore.dto.BookFormDTO;
import com.apress.bookstore.repository.BookRepository;
import com.apress.bookstore.repository.CategoryRepository;
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

import javax.validation.Valid;

@Controller
public class AddBookController {

	@Autowired
	private BookService bookService;
	@Autowired
	private AuthorService authorService;
	@Autowired
	private CategoryService categoryService;

	@RequestMapping(value = "/addBook.html", method = RequestMethod.GET)
	public ModelAndView initForm(ModelAndView mav) {
		mav.setViewName("addBook");
		BookFormDTO bookFormDTO = new BookFormDTO();
		bookFormDTO.setBookTitle("Dodaj książkę :");
		mav.addObject("bookFormDTO", bookFormDTO);
		return mav;
	}


	@InitBinder("bookFormDTO")
	public void initBinder(WebDataBinder binder, WebRequest request) {
		binder.setDisallowedFields(new String[] { "authors", "categories" });
		BookFormDTO bookFormDTO = (BookFormDTO) binder.getTarget();
		long authorId;
		long categoryId;

		String[] authorsParams = request.getParameterValues("authors");

		if (authorsParams != null) {
			List<Author> authorList = new ArrayList<>();
			for (String value : authorsParams) {
				authorId = Long.parseLong(value);
				Author author = authorService.getAuthorById(authorId);
				authorList.add(author);
			}
			bookFormDTO.setAuthors(authorList);
		}

		String[] categoriesParams = request.getParameterValues("categories");

		if (categoriesParams != null) {
			List<Category> categoryList = new ArrayList<>();
			for (String value : categoriesParams) {
				categoryId = Long.parseLong(value);
				Category category = categoryService.getCategoryById(categoryId);
				categoryList.add(category);
			}
			bookFormDTO.setCategories(categoryList);
		}
	}

	@ModelAttribute("catList")
	public List<Category> catList() {
		return categoryService.getCategoryList();
	}

	@ModelAttribute("authorList")
	public List<Author> populateAuthorList() {
		return authorService.getAuthorList();
	}

	@RequestMapping(value = "/addBook.html", method = RequestMethod.POST)
	public ModelAndView processSubmit(@ModelAttribute("bookFormDTO") @Valid BookFormDTO bookFormDTO, BindingResult result,
									  SessionStatus status, ModelAndView mav) {
//
		if (result.hasErrors()) {
			mav.setViewName("addBook");
			return mav;
		}

		bookService.validateBook(bookFormDTO, result);

		if (result.hasErrors()) {
			mav.setViewName("addBook");
			return mav;
		} else {
			bookService.createBook(bookFormDTO);
			mav.setViewName("redirect:/bookList.html");
			return mav;
		}

	}
}
