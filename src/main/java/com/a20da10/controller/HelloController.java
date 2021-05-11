package com.a20da10.controller;

import com.a20da10.Entity.spring.StudentEntity;
import com.a20da10.dao.spring.StudentDao;
import com.a20da10.service.ejb.MyFirstBeanLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

@Controller
@RequestMapping("/hello")
public class HelloController {
    @Autowired
    private StudentDao studentDao;

    @Autowired
    private  WebApplicationContext springMVCIOC;

    @RequestMapping(value = "/hello1")
    @ResponseBody
    public String getAllStudent(HttpServletRequest request,Model model) throws SQLException, PropertyVetoException, ClassNotFoundException {

        System.out.println("this is the student info"+studentDao.getAllStudents());

        model.addAttribute("student","name is shuai");

        return "success!";
//        return studentDao.getAllStudents();
//       return "forward:/WEB-INF/222.html";
    }

    @ResponseBody
    @GetMapping("/hello3")
    public List<StudentEntity> getAllStudentJson(){

        return studentDao.getAllStudents();
    }


    @RequestMapping(value = "hello2")
    public String getEJB(HttpServletRequest request, HttpServletResponse response) throws NamingException {


        Properties props = new Properties();

//        1.Get the context of server
        ServletContext sc = request.getSession().getServletContext();

//        2.Get the context of Spring IOC
        WebApplicationContext springIOC= WebApplicationContextUtils.getWebApplicationContext(sc);

//        3.Get the context of Spring MVC IOC
        WebApplicationContext springMVCIOC = RequestContextUtils.findWebApplicationContext(request);

        MyFirstBeanLocal bean1 = (MyFirstBeanLocal) springMVCIOC.getBean("myBean");

        System.out.println("this is the value calculated by ejb "+bean1.doOperation(3,5));

        return "success";
    }

}
