package com.apress.bookstore.controller;

import java.util.ArrayList;
import java.util.List;

import com.apress.bookstore.dto.RegistrationUserFormDTO;
import com.apress.bookstore.repository.CategoryRepository;
import com.apress.bookstore.service.CategoryService;
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

import javax.validation.Valid;

@Controller
public class RegisterController {
//	@Autowired
//	private User user;
	@Autowired
	private RegisterUserValidator registerUserValidator;
	@Autowired
	private UserService userService;
	@Autowired
	private CategoryService categoryService;

	@RequestMapping(value = "/register.html", method = RequestMethod.GET)
	public ModelAndView register(ModelAndView mav) {
		RegistrationUserFormDTO registrationUserFormDTO = new RegistrationUserFormDTO();
		mav.addObject("registrationUserFormDTO", registrationUserFormDTO);
		return mav;
	}

	@RequestMapping(value = "/register.html", method = RequestMethod.POST)
	public ModelAndView register(@ModelAttribute("registrationUserFormDTO") @Valid RegistrationUserFormDTO registrationUserFormDTO, BindingResult result,
								 SessionStatus status, WebRequest request, ModelAndView mav,
								 @ModelAttribute("catList") ArrayList<Category> catList, final RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			mav.setViewName("register");
			return mav;
		}

		userService.validateUser(registrationUserFormDTO, result);

		if (result.hasErrors()) {
			mav.setViewName("register");
			return mav;
		} else {

			if (userService.createUser(registrationUserFormDTO) != null) {
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
	public ModelAndView succeed(final ModelAndView mav) {
		mav.setViewName("succeed");
		return mav;
	}

	@RequestMapping(value = "/error.html", method = RequestMethod.GET)
	public ModelAndView error(final ModelAndView mav) {
		mav.setViewName("error");
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
