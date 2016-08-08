package com.apress.bookstore.controller;

import com.apress.bookstore.dto.BookFormDTO;
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
import org.springframework.http.HttpEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import org.apache.commons.codec.binary.Base64;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

@Controller
public class BookController {

	@Autowired
	private BookService bookService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private SessionLocaleResolver localeResolver;

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
    public ModelAndView book(@RequestParam("id") Long id, HttpServletRequest request, ModelAndView modelAndView) {

		if (request.isUserInRole("ROLE_ADMIN")) {
			BookFormDTO bookFormDTO = bookService.getBookDTOById(id);
			modelAndView.addObject("bookFormDTO", bookFormDTO);
            modelAndView.setViewName("bookAdmin");
        } else {
			Book book = bookService.getBookById(id);
			System.out.println("book title: " + book.getBookTitle());
			modelAndView.addObject("book", book);
			modelAndView.setViewName("book");
		}

        return modelAndView;
    }

	@InitBinder("bookFormDTO")
	public void initBinder(WebDataBinder binder, WebRequest request) {
		binder.setDisallowedFields("authors", "categories");

		BookFormDTO target = (BookFormDTO)binder.getTarget();

		Long id = Long.parseLong(request.getParameter("id"));
		BookFormDTO bookDTOById = bookService.getBookDTOById(id);

		target.setAuthors(bookDTOById.getAuthors());
		target.setCategories(bookDTOById.getCategories());
		target.setImage(bookDTOById.getImage());
	}

    @RequestMapping(value = "/saveBook.html", method = RequestMethod.POST)
    public ModelAndView editBook(@ModelAttribute("bookFormDTO") @Valid BookFormDTO bookFormDTO, BindingResult result, @RequestParam("file") MultipartFile file,
								  ModelAndView modelAndView, WebRequest request) {

		if (request.isUserInRole("ROLE_ADMIN")) {
			if (result.hasErrors()) {
				modelAndView.setViewName("book.html?id=" + bookFormDTO.getId());
				return modelAndView;
			}

			if (!bookService.validateBook(bookFormDTO, result)) {
				modelAndView.setViewName("book.html?id=" + bookFormDTO.getId());
				return modelAndView;
			} else {
				if (!file.isEmpty()) {

					String scaledImage = bookService.scaleImage(file);

					if (scaledImage != null)
						bookFormDTO.setImage(scaledImage);

				}

//				RestTemplate restTemplate = new RestTemplate();
//				BookFormDTO bookForm = restTemplate.postForObject("http://127.0.0.1:8080/bookstore/api/saveBook.json", bookFormDTO, BookFormDTO.class);
//				System.out.println(bookForm);

//				MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
////				headers.add("_csrf", "abc");
////				headers.add("_csrf_header", "X-CSRF-TOKEN");
////				headers.add("CSRF_TOKEN_HEADER_NAME", UUID.randomUUID().toString());
////				headers.add("X-CSRF-TOKEN", UUID.randomUUID().toString());
//				headers.add("USER_NAME", "Tomek");
//				headers.add("USER_PASSWORD", "pass");
//				RestTemplate restTemplate = new RestTemplate();
//				restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
//				HttpEntity<BookFormDTO> httpEntity = new HttpEntity<>(bookFormDTO, headers);
//				BookFormDTO bookForm = restTemplate.postForObject("http://127.0.0.1:8080/bookstore/api/saveBook.json", httpEntity, BookFormDTO.class);
//				System.out.println(bookForm);

				bookService.saveBookFromDTO(bookFormDTO);

				modelAndView.setViewName("redirect:/book.html?id=" + bookFormDTO.getId());

				return modelAndView;

			}
		}

		modelAndView.setViewName("book.html?id=" + bookFormDTO.getId());
		return modelAndView;
    }

	@RequestMapping(value = "/downloadImage.do")
	public ModelAndView downloadImage(@RequestParam("id") Long id, HttpServletResponse response, ModelAndView modelAndView) {

		bookService.downloadImage(id, response);

		modelAndView.setViewName("book.html?id=" + id);

		return modelAndView;
	}

	@RequestMapping("/language.html")
	public ModelAndView language(@RequestParam("language") String language, HttpServletRequest request, HttpServletResponse response, ModelAndView modelAndView) {
		localeResolver.setLocale(request, response, new Locale(language));

		String referer = request.getHeader("Referer");
		String redirect = referer.substring(referer.lastIndexOf("/"));

		modelAndView.setViewName("redirect:" + redirect);
		return modelAndView;
	}

	@ModelAttribute("catList")
	public Set<Category> catList() {
		return categoryService.getCategoryList();
	}

}
