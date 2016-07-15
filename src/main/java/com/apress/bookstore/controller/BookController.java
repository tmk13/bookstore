package com.apress.bookstore.controller;

import com.apress.bookstore.entity.Author;
import com.apress.bookstore.entity.Book;
import com.apress.bookstore.entity.Category;
import com.apress.bookstore.entity.User;
import com.apress.bookstore.service.BookService;
import com.apress.bookstore.service.CategoryService;
import org.apache.commons.codec.binary.Base64InputStream;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import org.apache.commons.codec.binary.Base64;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

@Controller
public class BookController {

//	@Autowired
//	private User user;

	@Autowired
	private BookService bookService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private SessionLocaleResolver localeResolver;
//    @Autowired
//    private CookieLocaleResolver localeResolver;

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
		mav.setViewName("allBooks");
		mav.addObject("allBooks", bookService.getBooksByCategoryList(category));
		return mav;
	}

	@RequestMapping("/searchResult.html")
	public ModelAndView searchBooksController(@RequestParam("keyWord") String keyWord, ModelAndView mav) {
		mav.setViewName("searchResult");
		mav.addObject("allBooks", bookService.getBooksByKeyWordList(keyWord));
		return mav;
	}

    @RequestMapping("/book.html")
    public ModelAndView book(@RequestParam("id") Long id, ModelAndView modelAndView) {

        Book book = bookService.getBookById(id);
        modelAndView.addObject("book", book);

		for (Author author : book.getAuthors()) {
			System.out.print(author.getFullName() + ", ");
		}
		System.out.println();

		modelAndView.setViewName("book");

        return modelAndView;
    }

    @RequestMapping(value = "/editImage.html", method = RequestMethod.POST)
    public ModelAndView editImage(@RequestParam("id") Long id, @RequestParam("file") MultipartFile file, ModelAndView modelAndView) {

        if(!file.isEmpty()) {

            Book book = bookService.getBookById(id);
			String scaledImage = bookService.scaleImage(file);

			if(scaledImage != null) {
				book.setImage(scaledImage);
				bookService.saveBook(book);
			}
        }

        modelAndView.setViewName("redirect:/book.html?id=" + id);

        return modelAndView;
    }

	@RequestMapping(value = "/downloadImage.do")
	public ModelAndView downloadImage(@RequestParam("id") Long id, HttpServletResponse response, ModelAndView modelAndView) {

		bookService.downloadImage(id, response);

		modelAndView.setViewName("book.html?id=" + id);

		return modelAndView;
	}

	@RequestMapping("/language.html")
	public ModelAndView language(@RequestParam("language") String language, WebRequest request, ModelAndView modelAndView) {
        localeResolver.setDefaultLocale(new Locale(language));

		String referer = request.getHeader("Referer");
		String redirect = referer.substring(referer.lastIndexOf("/"));

		modelAndView.setViewName("redirect:" + redirect);
		return modelAndView;
	}

	@ModelAttribute("catList")
	public Set<Category> catList() {
		return categoryService.getCategoryList();
	}

	@ModelAttribute("user")
	public User getUser() {
		return new User();
	}

}
