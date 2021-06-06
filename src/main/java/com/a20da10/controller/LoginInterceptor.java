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

        System.out.println("Rui: The session is");
        System.out.println(request.getSession());

        //Only allow accessing to WelcomePageController
        if(url.contains("/welcome")){
            System.out.println("Rui: Enter the welcome webpage");
            System.out.println(request.getSession());
            return true;
        }
//        if(url.contains("/rest")){
//            return true;
//        }
        HttpSession session = request.getSession();
        StudentSelfService studentSelfService = (StudentSelfService) session.getAttribute("USER_SESSION");
        if (studentSelfService!=null)
        {
            System.out.println("Rui: Successfully authenticated!!!:");
            System.out.println(request.getSession());
            return true;
        }
        else
        {
            System.out.println("Rui: Not loged in yet! Intercept!" + request.getSession());
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
