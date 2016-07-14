package com.apress.bookstore.validator;

import com.apress.bookstore.dto.BookFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.stereotype.Service;
import org.springframework.validation.*;

import com.apress.bookstore.entity.Book;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Service
public class BookValidator implements Validator {

    @Autowired
    private MessageSource messageSource;
//    @Autowired
//    private LocaleContext localeContext;

    @Override
	public boolean supports(Class<?> aClass) {
		return BookFormDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {

//        BookFormDTO bookFormDTO = (BookFormDTO) obj;
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "bookTitle", "field.required", "Nie może być pusta!");
//		if (!errors.hasFieldErrors("bookTitle"))
//			if (bookFormDTO.getBookTitle().isEmpty())
//				errors.rejectValue("bookTitle", "", "Nie może być pusta!");

//        Locale locale = localeContext.getLocale();
//        System.out.println(locale);
//
//        Map<String, String> map = new HashMap<>();

//        for (ObjectError objectError : errors.getAllErrors()) {
//            if(objectError instanceof FieldError) {
//                FieldError fieldError = (FieldError)objectError;
//                System.out.println(fieldError.toString());
//                String message = messageSource.getMessage(fieldError.getField(), null, locale);
//                System.out.println(message);
//                map.put(fieldError.getField(), message);
//            }
//        }
//
//        for (Map.Entry<String, String> entry : map.entrySet()) {
//            errors.rejectValue(entry.getKey(), "", entry.getValue());
//        }
    }


}
