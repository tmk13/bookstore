package com.apress.springmvc.configuration;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.*;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.Ordered;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.EntityManagerFactoryInfo;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.servlet.LocaleContextResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.util.Locale;
import java.util.Properties;

@EnableWebMvc
@Configuration
@Import({SecurityConfig.class})
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"com.apress.bookstore.repository"})
@PropertySource("classpath:persistence-mysql.properties")
@PropertySource("classpath:hibernate.properties")
@ComponentScan(basePackages = {"com.apress.bookstore.controller", "com.apress.bookstore.entity", "com.apress.bookstore.service",
        "com.apress.bookstore.validator", "com.apress.bookstore.dao", "com.apress.bookstore.repository",
        "com.apress.springmvc.configuration"})
public class AppConfiguration extends WebMvcConfigurerAdapter implements WebApplicationInitializer{

    @Value("${dataSource.driverClassName}")
    private String driverClassName;

    @Value("${dataSource.url}")
    private String url;

    @Value("#{systemProperties['dataSource.username']}")
    private String username;

    @Value("#{systemProperties['dataSource.password']}")
    private String password;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

//    @Bean
//    public DataSource dataSource() {
//        BasicDataSource dataSource = new BasicDataSource();
//        dataSource.setDriverClassName(driverClassName);
//        dataSource.setUrl(url);
//        dataSource.setUsername(username);
//        dataSource.setPassword(password);
//        return dataSource;
//    }

    @Bean
    public DataSource dataSource() {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        try {
            dataSource.setDriverClass(driverClassName);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        dataSource.setJdbcUrl(url);
        dataSource.setUser(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Value("${hibernate.hbm2ddl.auto}")
    private String hbm2ddl;

    @Value("${hibernate.dialect}")
    private String dialect;

    @Value("${hibernate.show_sql}")
    private String show_sql;

//    @Bean
//    public SessionFactory sessionFactory() {
//        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
//        SessionFactory sessionFactory = null;
//        factoryBean.setDataSource(dataSource());
//        factoryBean.setPackagesToScan("com.apress.bookstore.entity");
//        Properties hibernateProperties = new Properties();
//        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", hbm2ddl);
//        hibernateProperties.setProperty("hibernate.dialect", dialect);
//        hibernateProperties.setProperty("hibernate.show_sql", show_sql);
//        factoryBean.setHibernateProperties(hibernateProperties);
//        try {
//            factoryBean.afterPropertiesSet();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        try {
//            sessionFactory = factoryBean.getObject();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return sessionFactory;
//    }



//    @Bean
//    public HibernateTransactionManager transactionManager() {
//        HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
//        hibernateTransactionManager.setSessionFactory(sessionFactory());
//        return hibernateTransactionManager;
//    }

//    @Bean
//    public PersistenceAnnotationBeanPostProcessor persistenceAnnotationBeanPostProcessor() {
//        return new PersistenceAnnotationBeanPostProcessor();
//    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

//    @Bean(name = "entityManagerFactory")
//    public EntityManagerFactoryInfo emf() {
//        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
//        emf.setDataSource(dataSource());
//        emf.setPackagesToScan(new String[]{"com.apress.bookstore.entity"});
//        emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
//        return emf;
//    }

//    @Bean(name = "transactionManager")
//    public PlatformTransactionManager transactionManager() {
//        JpaTransactionManager tm = new JpaTransactionManager();
//        tm.setEntityManagerFactory(emf());
//        tm.setDataSource(dataSource());
//        return tm;
//    }

    @Bean
    public ViewResolver getViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames("i18n/messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("language");
        return localeChangeInterceptor;
    }

    @Bean
    public SessionLocaleResolver localeResolver() {
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
//        localeResolver.setDefaultLocale(new Locale("pl_PL"));
        return localeResolver;
    }

    @Bean
    public LocaleContext localeContext(HttpServletRequest httpServletRequest) {
        return localeResolver().resolveLocaleContext(httpServletRequest);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home");
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        servletContext.setInitParameter("home", "/bookstore");
        servletContext.setInitParameter("imageURL", "/bookstore/images");
    }
}
