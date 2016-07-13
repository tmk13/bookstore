package com.apress.bookstore.controller;

import java.util.ArrayList;
import java.util.List;

import com.apress.bookstore.repository.BookRepository;
import com.apress.bookstore.repository.CategoryRepository;
import com.apress.bookstore.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.apress.bookstore.entity.Book;
import com.apress.bookstore.entity.Category;
import com.apress.bookstore.entity.User;
import com.apress.bookstore.service.BookService;

@Controller
public class BookController {

//	@Autowired
//	private User user;

	@Autowired
	private BookService bookService;
	@Autowired
	private CategoryService categoryService;

	@RequestMapping("/index.html")
	public String indexController() {
		return "redirect:/home.html";
	}

	@RequestMapping("/home.html")
	public ModelAndView homeController(ModelAndView mav) {
		mav.setViewName("home");
		return mav;
	}

	@RequestMapping("/bookList.html")
	public ModelAndView bookListController(@ModelAttribute("allBooks") ArrayList<Book> allBooks, ModelAndView mav) {
		mav.setViewName("bookList");
		mav.addObject("bookList", bookService.getAllBooksList());
		return mav;
	}
	@RequestMapping("/allBooks.html")
	public ModelAndView allBooksController(@ModelAttribute("allBooks") ArrayList<Book> allBooks, ModelAndView mav) {
		mav.setViewName("allBooks");
		mav.addObject("allBooks", bookService.getAllBooksList());
		return mav;
	}
	@RequestMapping("/category.html")
	public ModelAndView byCategoryBooksController(@RequestParam("category") String category, ModelAndView mav) {
		mav.setViewName("category");
		mav.addObject("allBooks", bookService.getBooksByCategoryList(category));
		return mav;
	}

	@RequestMapping("/searchResult.html")
	public ModelAndView searchBooksController(@RequestParam("keyWord") String keyWord, ModelAndView mav) {
		mav.setViewName("searchResult");
		mav.addObject("allBooks", bookService.getBooksByKeyWordList(keyWord));
		return mav;
	}

	@ModelAttribute("catList")
	public List<Category> catList() {
		return categoryService.getCategoryList();
	}

	@ModelAttribute("user")
	public User getUser() {
		return new User();
	}

}
