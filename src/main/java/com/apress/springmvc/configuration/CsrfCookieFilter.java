package com.apress.springmvc.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CsrfCookieFilter extends OncePerRequestFilter {

    public static final String XSRF_TOKEN_COOKIE_NAME = "XSRF-TOKEN";

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

//        log.debug("Inside CsrfCookieFilter ...");
        System.out.println("Inside CsrfCookieFilter ...");

        CsrfToken csrf = (CsrfToken)
                request.getAttribute(CsrfToken.class.getName()); // Or "_csrf" (See CSRFFilter.doFilterInternal).

        if (csrf != null) {
            Cookie cookie = WebUtils.getCookie(
                    request, XSRF_TOKEN_COOKIE_NAME);
            String token = csrf.getToken();
            if (cookie==null ||
                    token!=null && !token.equals(cookie.getValue())) {
                cookie = new Cookie(XSRF_TOKEN_COOKIE_NAME, token);
                cookie.setPath("/");
                response.addCookie(cookie);
            }
        }
        filterChain.doFilter(request, response);
    }
}