package com.apress.bookstore.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.apress.bookstore.entity.User;
import com.apress.bookstore.service.UserService;

@Service
public class UserValidator implements Validator {

	@Autowired
	private UserService userService;

    @Override
	public boolean supports(Class<?> aClass) {
		return User.class.equals(aClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {
//		User user = (User) obj;
//
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "field.required",
//				"Niepoprawna nazwa użytkownika lub błędne hasło!");
//
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userPassword", "field.required",
//				"Niepoprawna nazwa użytkownika lub błędne hasło!");
//
//		if (errors.hasFieldErrors("userName") || errors.hasFieldErrors("userPassword"))
//			return;
//
//		User loggedUser;
//		if ((loggedUser = userService.checkLogin(user)) == null)
//			errors.rejectValue("userName", "", "Niepoprawna nazwa użytkownika lub błędne hasło!");
//		else {
//			user.setId(loggedUser.getId());
//			user.setUserName(loggedUser.getUserName());
//			user.setUserPassword(loggedUser.getUserPassword());
//		}

    }


}
