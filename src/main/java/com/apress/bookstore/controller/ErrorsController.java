package com.apress.bookstore.controller;

import com.apress.bookstore.entity.Category;
import com.apress.bookstore.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Set;

@Controller
public class ErrorsController {

    @Autowired
    private CategoryService categoryService;

    @ModelAttribute(value = "catList")
    public Set<Category> catList() {
        return categoryService.getCategoryList();
    }

    @RequestMapping(value = "/accessDenied.html")
    public ModelAndView denied(ModelAndView modelAndView) {
        modelAndView.setViewName("accessDenied");
        return modelAndView;
    }

}
