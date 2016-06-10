package com.apress.bookstore.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.apress.bookstore.entity.Category;
import com.apress.bookstore.entity.User;
import com.apress.bookstore.service.BookService;
import com.apress.bookstore.service.UserService;
import com.apress.bookstore.validator.RegisterUserValidator;

@Controller
public class RegisterController {
	@Autowired
	private User user;
	@Autowired
	private RegisterUserValidator registerUserValidator;
	@Autowired
	private BookService bookService;
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/register.html", method = RequestMethod.GET)
	public ModelAndView register(@ModelAttribute("userRegister") User userRegister,
			@ModelAttribute("catList") ArrayList<Category> catList, ModelAndView mav) {
		mav.addObject("catList", catList);
		mav.addObject("userRegister", userRegister);
		return mav;
	}

	@RequestMapping(value = "/register.html", method = RequestMethod.POST)
	public ModelAndView register(@ModelAttribute("userRegister") User userRegister, BindingResult result,
			SessionStatus status, WebRequest request, ModelAndView mav,
			@ModelAttribute("catList") ArrayList<Category> catList, final RedirectAttributes redirectAttributes) {

		registerUserValidator.validate(userRegister, result);

		if (result.hasErrors()) {
			mav.setViewName("register");
			return mav;
		} else {
			System.out.println(userRegister.getUserName() + " " + userRegister.getUserPassword());
			if (userService.createUser(userRegister)) {
				redirectAttributes.addFlashAttribute("catList", catList);
				mav.setViewName("redirect:/succeed.html");
				return mav;
			} else {
				redirectAttributes.addFlashAttribute("catList", catList);
				mav.setViewName("redirect:/error.html");
				return mav;
			}
		}
	}

	@RequestMapping(value = "/succeed.html", method = RequestMethod.GET)
	public ModelAndView succeed(@ModelAttribute("catList") final ArrayList<Category> catList,
			final BindingResult mapping1BindingResult, final ModelAndView mav) {
		mav.addObject("catList", catList);
		mav.setViewName("succeed");
		return mav;
	}

	@RequestMapping(value = "/error.html", method = RequestMethod.GET)
	public ModelAndView error(@ModelAttribute("catList") final ArrayList<Category> catList,
			final BindingResult mapping1BindingResult, final ModelAndView mav) {
		mav.addObject("catList", catList);
		mav.setViewName("error");
		return mav;
	}

	@ModelAttribute("catList")
	public List<Category> catList() {
		return bookService.getCategoryList();
	}

	@ModelAttribute("userRegister")
	public User getUserRegister() {
		return new User();
	}

	@ModelAttribute("user")
	public User getUser() {
		return user;
	}

}
