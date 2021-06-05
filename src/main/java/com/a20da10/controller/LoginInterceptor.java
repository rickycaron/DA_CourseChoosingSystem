package com.a20da10.controller;

import com.a20da10.service.spring.StudentSelfService;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url =request.getRequestURI();

        System.out.println("66666666666666666666666666666666666666666666666666666666666666");
        System.out.println(request.getSession());
        System.out.println("66666666666666666666666666666666666666666666666666666666666666");

        //Only allow accessing to WelcomePageController
        if(url.contains("/welcome")){
            System.out.println(request.getSession());
            System.out.println("0000000000000000000000000000000000000000000000000000");
            return true;
        }
//        if(url.contains("/rest")){
//            return true;
//        }
        HttpSession session = request.getSession();
        StudentSelfService studentSelfService = (StudentSelfService) session.getAttribute("USER_SESSION");
        if (studentSelfService!=null)
        {
            System.out.println(request.getSession());
            System.out.println("44444444444444444444444444444444444444444444444444444");
            return true;
        }
        else
        {
            System.out.println(request.getSession());
            System.out.println("Not loged in yet! Intercept!");
            System.out.println("555555555555555555555555555555555555555555555555555555");
            //Here can set forward to login page jsp
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
