package com.apress.springmvc.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration
@ComponentScan(basePackages = {"com.apress.bookstore.entity", "com.apress.bookstore.service",
        "com.apress.bookstore.validator", "com.apress.bookstore.dao", "com.apress.bookstore.repository",
        "com.apress.springmvc.configuration"})
public class RootContextConfiguration {

    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setDefaultEncoding("UTF-8");
        multipartResolver.setMaxUploadSize(10000000);
        return multipartResolver;
    }

}
