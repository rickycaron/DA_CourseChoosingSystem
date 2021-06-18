package com.a20da10.controller;

import com.a20da10.service.ejb.InstructorSelfServiceRemote;
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

        System.out.println(request.getSession());

        response.setHeader("Access-Control-Allow-Headers", "Accept, Content-Type");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:8081");
        response.setHeader("Access-Control-Allow-Credentials","true");

        //used for testing only
        if(url.contains("/hello")){
            return true;
        }
        //Only allow accessing to WelcomePageController
        if(url.contains("/welcome")){
            System.out.println("Rui: Enter the welcome webpage");
            System.out.println(request.getSession());
            return true;
        }
        HttpSession session = request.getSession();
        if(session.getAttribute("LOGIN_TYPE") == "student") {
            System.out.println("Rui: enter the interceptor");
            StudentSelfService service = (StudentSelfService) session.getAttribute("USER_SESSION");
            if (service!=null){
                System.out.println("Rui: passed the interceptor");
                return true;
            }
        }
        else{
            InstructorSelfServiceRemote service = (InstructorSelfServiceRemote) session.getAttribute("USER_SESSION");
            if (service!=null){
                return true;
            }
        }
        //Here can set forward to login page jsp
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
