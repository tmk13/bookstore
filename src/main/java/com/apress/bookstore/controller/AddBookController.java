package com.apress.bookstore.controller;

import com.apress.bookstore.dto.BookFormDTO;
import com.apress.bookstore.entity.Author;
import com.apress.bookstore.entity.Category;
import com.apress.bookstore.service.AuthorService;
import com.apress.bookstore.service.BookService;
import com.apress.bookstore.service.CategoryService;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

@Controller
public class AddBookController {

	@Autowired
	private BookService bookService;
	@Autowired
	private AuthorService authorService;
	@Autowired
	private CategoryService categoryService;

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/addBook.html", method = RequestMethod.GET)
	public ModelAndView initForm(ModelAndView mav) {
		mav.setViewName("addBook");
		BookFormDTO bookFormDTO = new BookFormDTO();
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
			Set<Author> authorList = new LinkedHashSet<>();
			for (String value : authorsParams) {
				authorId = Long.parseLong(value);
				Author author = authorService.getAuthorById(authorId);
				authorList.add(author);
			}
			bookFormDTO.setAuthors(authorList);
		}

		String[] categoriesParams = request.getParameterValues("categories");

		if (categoriesParams != null) {
			Set<Category> categoryList = new LinkedHashSet<>();
			for (String value : categoriesParams) {
				categoryId = Long.parseLong(value);
				Category category = categoryService.getCategoryById(categoryId);
				categoryList.add(category);
			}
			bookFormDTO.setCategories(categoryList);
		}
	}

	@ModelAttribute("catList")
	public Set<Category> catList() {
		return categoryService.getCategoryList();
	}

	@ModelAttribute("authorList")
	public Set<Author> populateAuthorList() {
		return authorService.getAuthorList();
	}

	@RequestMapping(value = "/addBook.html", method = RequestMethod.POST)
	public ModelAndView processSubmit(@ModelAttribute("bookFormDTO") @Valid BookFormDTO bookFormDTO, BindingResult result,
									  SessionStatus status, @RequestParam("file")MultipartFile file, ModelAndView mav) {

		if (result.hasErrors()) {
			mav.setViewName("addBook");
			return mav;
		}

		if (!bookService.validateBook(bookFormDTO, result)) {
			mav.setViewName("addBook");
			return mav;
		} else {

			if(!file.isEmpty()) {

				String scaledImage = bookService.scaleImage(file);
				if(scaledImage != null)
					bookFormDTO.setImage(scaledImage);

			} else {
				System.out.println("Uploaded file is empty"); // add to logger instead of console
			}

			bookService.createBook(bookFormDTO);

			mav.setViewName("redirect:/bookList.html");
			return mav;
		}

	}
}
