package com.a20da10.controller;

import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@WebFilter(urlPatterns = "/welcome/*")
public class passwordFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("------------------------------------------You are in a filter now !--------------------------------------------------");
        HttpServletRequest request = (HttpServletRequest) req;
        System.out.println(request.getRequestURL());

        Logger logger = Logger.getGlobal();
        logger.info("The user "+ request.getSession()+ " is visiting!");
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
