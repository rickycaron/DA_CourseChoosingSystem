package com.a20da10.controller;

import com.a20da10.dao.spring.StudentDao;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DriverManagerDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Controller
public class HelloController {

    @Autowired
    private StudentDao studentDao;

    @RequestMapping("/hello1")
    public String mySixthRequest(HttpServletRequest request, HttpServletResponse response) throws SQLException, PropertyVetoException, ClassNotFoundException {
//        1.Get the context of server
        ServletContext sc = request.getSession().getServletContext();

//        2.Get the context of Spring IOC
        WebApplicationContext springIOC= WebApplicationContextUtils.getWebApplicationContext(sc);

//        3.Get the context of Spring MVC IOC
        WebApplicationContext springMVCIOC = RequestContextUtils.findWebApplicationContext(request);

//
//        ComboPooledDataSource dataSource1 = (ComboPooledDataSource) springIOC.getBean("dsConnection");
//
//        System.out.println(dataSource1.getConnection());

        System.out.println(studentDao.getCustomers());

//        System.out.println("this is from spring mvc ioc of HelloController" + springMVCContext.getBean(HelloController.class));
//        System.out.println("this is from spring mvc ioc of book" + springContext.getBean(Book.class));
        return "success";
//       return "forward:/WEB-INF/222.html";
    }

}
