package com.apress.springmvc.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.JdbcUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@ComponentScan(basePackages = {"com.apress.springmvc.configuration"})
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/").permitAll()
                    .antMatchers("/category.html").authenticated()
                    .and()
                .formLogin()
                    .loginPage("/loginPage.html")
                    .defaultSuccessUrl("/home.html")
                    .failureUrl("/loginPage.html?error=1")
                    .usernameParameter("USER_NAME")
                    .passwordParameter("USER_PASSWORD")
                    .permitAll()
                    .and()
                .logout().logoutSuccessUrl("/loginPage.html?logout=true")
                    .permitAll()
                    .and()
                .csrf()
                    .and()
                .exceptionHandling()
                    .accessDeniedPage("/accessDenied.html");

//                .authorizeRequests()
//                .antMatchers("/").permitAll()
//                .antMatchers("/category.html").authenticated()
//                    .and()
//                .formLogin()
//                    .and()
//                .csrf();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder> authenticationManagerBuilderInMemoryUserDetailsManagerConfigurer = auth.inMemoryAuthentication();

        JdbcUserDetailsManagerConfigurer<AuthenticationManagerBuilder> authenticationManagerBuilderJdbcUserDetailsManagerConfigurer = auth.jdbcAuthentication();
        authenticationManagerBuilderJdbcUserDetailsManagerConfigurer.dataSource(dataSource).usersByUsernameQuery(
                "SELECT USER_NAME, USER_PASSWORD, ENABLED FROM user WHERE USER_NAME=?").authoritiesByUsernameQuery(
                "SELECT u.USER_NAME, r.ROLE_NAME FROM user u" +
                        " JOIN user_role ur ON u.ID = ur.USER_ID" +
                        " JOIN role r ON ur.ROLE_ID = r.ID" +
                        " WHERE USER_NAME=?");

//        authenticationManagerBuilderInMemoryUserDetailsManagerConfigurer
//                .withUser("michal")
//                .password("password")
//                .roles("USER", "ADMIN");
//
//        authenticationManagerBuilderInMemoryUserDetailsManagerConfigurer
//                .withUser("user2")
//                .password("passwd")
//                .roles("USER");
    }

}
