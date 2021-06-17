package com.a20da10.controller;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

@WebFilter("/hello-servlet")
public class passwordFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("You are in a filter now !");
        PrintWriter out = response.getWriter();
        HttpServletRequest req = (HttpServletRequest) request;
        String passwordInput = request.getParameter("passwordInput");
        String passwordConfirm = request.getParameter("passwordConfirm");

        Pattern specailCharPatten = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Pattern UpperCasePatten = Pattern.compile("[A-Z ]");
        Pattern lowerCasePatten = Pattern.compile("[a-z ]");
        Pattern digitCasePatten = Pattern.compile("[0-9 ]");
        boolean flag=true;

        if (!passwordInput.equals(passwordConfirm)) {
            System.out.println("password and confirm password does not match");
            flag=false;
        }
        if (passwordInput.length() < 8) {
            System.out.println("Password lenght must have alleast 8 character !!");
            flag=false;
        }
        if (!specailCharPatten.matcher(passwordInput).find()) {
            System.out.println("Password must have atleast one specail character !!");
            flag=false;
        }
        if (!UpperCasePatten.matcher(passwordInput).find()) {
            System.out.println("Password must have atleast one uppercase character !!");
            flag=false;
        }
        if (!lowerCasePatten.matcher(passwordInput).find()) {
            System.out.println("Password must have atleast one lowercase character !!");
            flag=false;
        }
        if (!digitCasePatten.matcher(passwordInput).find()) {
            System.out.println("Password must have atleast one digit character !!");
            flag=false;
        }

        if(flag) {
            chain.doFilter(request, response);
            System.out.println("Password is valid !");
        }
        else
            System.out.println("Please input a valid password !");
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
