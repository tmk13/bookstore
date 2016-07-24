package com.apress.bookstore.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.apress.bookstore.repository.CategoryRepository;
import com.apress.bookstore.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.apress.bookstore.entity.Category;
import com.apress.bookstore.entity.User;
import com.apress.bookstore.service.BookService;
import com.apress.bookstore.validator.UserValidator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@Scope("session")
@SessionAttributes("loggedUser")
public class LoginController {

//	@Autowired
//	private User user;
	@Autowired
	private UserValidator userValidator;
	@Autowired
	private CategoryService categoryService;

	@RequestMapping(value = "/loginOld.html", method = RequestMethod.POST)
	public ModelAndView loginOld(@ModelAttribute("catList") ArrayList<Category> catList,
			@ModelAttribute("user") User user,
			BindingResult result,
			SessionStatus status, WebRequest request, ModelAndView mav) {

		String referer = request.getHeader("Referer");
		String redirect = "";
		if (referer.endsWith(".html"))
			redirect = referer.substring(referer.lastIndexOf("/") + 1);
		else
			redirect = "home.html";

		userValidator.validate(user, result);

		if (redirect.equals("login.html"))
			redirect = "home.html";

		if (result.hasErrors()) {
			user.setUserName(null);
			user.setUserPassword(null);

			mav.setViewName(redirect.substring(0, redirect.lastIndexOf(".html")));
			mav.addObject("catList", catList);
			return mav;
		} else {
			user.setUserName(user.getUserName());
			user.setUserPassword(user.getUserPassword());
			System.out.println(user.getUserName() + " " + user.getUserPassword());

			mav.setViewName("redirect:/" + redirect);
			mav.addObject("loggedUser", user);
			return mav;
		}

	}

	@RequestMapping(value = "/logoutOld.html", method = RequestMethod.POST)
	public ModelAndView logoutOld(@ModelAttribute("user") User user, WebRequest request, ModelAndView mav) {

		user.setUserName(null);
		user.setUserPassword(null);

		String referer = request.getHeader("Referer");
		String redirect = referer.substring(referer.lastIndexOf("/"));

		mav.setViewName("redirect:" + redirect);
		return mav;
	}

	@Secured("ROLE_ANONYMOUS")
	@RequestMapping(value = "/loginPage.html")
	public ModelAndView loginPage(ModelAndView mav) {
		mav.setViewName("loginPage");
		return mav;
	}

	@RequestMapping(value = "/logout.do", method = RequestMethod.POST)
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response, ModelAndView mav) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth != null)
			new SecurityContextLogoutHandler().logout(request, response, auth);

		mav.setViewName("redirect:/loginPage.html?logout");
		return mav;
	}

	@ModelAttribute("user")
	public User getUser() {
		return new User();
	}

	@ModelAttribute("catList")
	public Set<Category> catList() {
		return categoryService.getCategoryList();
	}

}
