package com.apress.springmvc.configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

    public class WebInit extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {

        return new Class<?>[] {AppConfiguration.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {
                "*.html"
                ,"*.json"
                ,"*.do"};
    }
}