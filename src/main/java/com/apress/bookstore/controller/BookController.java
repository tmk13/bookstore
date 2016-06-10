package com.apress.bookstore.controller;

import java.util.ArrayList;
import java.util.List;

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

	@Autowired
	private User user;

	@Autowired
	private BookService bookService;

	@RequestMapping("/index.html")
	public String indexController() {
		return "redirect:/home.html";
	}

	@RequestMapping("/home.html")
	public ModelAndView homeController(@ModelAttribute("catList") ArrayList<Category> catList, ModelAndView mav) {
		mav.setViewName("home");
		mav.addObject("catList", catList);
		return mav;
	}

	@RequestMapping("/bookList.html")
	public ModelAndView bookListController(@ModelAttribute("allBooks") ArrayList<Book> allBooks,
			@ModelAttribute("catList") ArrayList<Category> catList, ModelAndView mav) {
		mav.setViewName("bookList");
		mav.addObject("bookList", bookService.getAllBooksList());
		mav.addObject("catList", catList);
		return mav;
	}
	@RequestMapping("/allBooks.html")
	public ModelAndView allBooksController(@ModelAttribute("allBooks") ArrayList<Book> allBooks,
			@ModelAttribute("catList") ArrayList<Category> catList, ModelAndView mav) {
		mav.setViewName("allBooks");
		mav.addObject("allBooks", bookService.getAllBooksList());
		mav.addObject("catList", catList);
		return mav;
	}
	@RequestMapping("/category.html")
	public ModelAndView byCategoryBooksController(@RequestParam("category") String category,
			@ModelAttribute("catList") ArrayList<Category> catList, ModelAndView mav) {
		mav.setViewName("category");
		mav.addObject("allBooks", bookService.getBooksByCategoryList(category));
		mav.addObject("catList", catList);
		return mav;
	}

	@RequestMapping("/searchResult.html")
	public ModelAndView searchBooksController(@RequestParam("keyWord") String keyWord,
			@ModelAttribute("catList") ArrayList<Category> catList, ModelAndView mav) {
		mav.setViewName("searchResult");
		mav.addObject("allBooks", bookService.getBooksByKeyWordList(keyWord));
		mav.addObject("catList", catList);
		return mav;
	}

	@ModelAttribute("catList")
	public List<Category> catList() {
		return bookService.getCategoryList();
	}

	@ModelAttribute("user")
	public User getUser() {
		return user;
	}

}
