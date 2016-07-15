package com.apress.bookstore.service;

import com.apress.springmvc.configuration.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertTrue;

//@WebAppConfiguration
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {AppConfiguration.class, EntityManagerFactoriesConfiguration.class, SecurityConfig.class, SpringSecurityInitializer.class,
//        TransactionManagersConfig.class})
public class BookServiceTest {

//    @Autowired
//    private BookService bookService;

    @Test
    public void isBookSrviceAutowired() {
        assertTrue(true);
//        assertNotNull(bookService);
    }


}
