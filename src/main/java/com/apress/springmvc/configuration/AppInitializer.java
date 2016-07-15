package com.apress.springmvc.configuration;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class AppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(RootContextConfiguration.class);

        servletContext.addListener(new ContextLoaderListener(applicationContext));

        AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();
        dispatcherContext.register(BookstoreConfiguration.class);

        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(dispatcherContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("*.html", "*.json", "*.do");
        dispatcher.setInitParameter("home", "/bookstore");
        dispatcher.setInitParameter("imageURL", "/bookstore/images");
        dispatcher.setMultipartConfig(new MultipartConfigElement("/home/tom/mytemp", 10_000_000L, 20_000_000L, 512_000));

        servletContext.setInitParameter("home", "/bookstore");
        servletContext.setInitParameter("imageURL", "/bookstore/images");
    }
}
