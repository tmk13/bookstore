package com.apress.bookstore.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.apress.bookstore.entity.User;
import com.apress.bookstore.service.UserService;

public class RegisterUserValidator implements Validator {

	@Autowired
	private UserService userService;

	@Override
	public boolean supports(Class<?> aClass) {
		return User.class.equals(aClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {
		User user = (User) obj;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "field.required",
				"Nazwa nie może być pusta!");

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userPassword", "field.required",
				"Hasło nie może być puste!");

		if (errors.hasFieldErrors("userName") || errors.hasFieldErrors("userPassword"))
			return;

		if (!userService.isAvailableUser(user))
			errors.rejectValue("userName", "", "Nazwa niedostępna!");
    }


}
