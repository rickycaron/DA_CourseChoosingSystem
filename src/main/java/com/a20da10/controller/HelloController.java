package com.a20da10.controller;

import com.a20da10.Entity.ejb.EJBCourseEntity;
import com.a20da10.Entity.spring.CourseEntity;
import com.a20da10.Entity.spring.StudentEntity;
import com.a20da10.activemq.ProducerTest;
import com.a20da10.dao.spring.StudentDao;
import com.a20da10.service.ejb.CourseService;
import com.a20da10.service.ejb.CourseServiceLocal;
import com.a20da10.service.ejb.MyFirstBeanLocal;
import com.a20da10.service.spring.StudentGeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.ejb.EJB;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

@Controller
public class HelloController {
//    @Autowired
//    private StudentDao studentDao;

    @Autowired
    private StudentGeneralService studentGeneralService;

    @Autowired
    private  WebApplicationContext springMVCIOC;

    @Autowired
    private CourseServiceLocal courseService;

    @Autowired
    private ProducerTest producerTest;

    @RequestMapping(value = "/hello1")
    @ResponseBody
    @Transactional
    public String getAllStudent(HttpServletRequest request,Model model) throws SQLException, PropertyVetoException, ClassNotFoundException {

        System.out.println("this is the student info"+studentGeneralService.getAllStudent());

        model.addAttribute("student","name is shuai");


        return "success!!";
//        return studentDao.getAllStudents();
//       return "forward:/WEB-INF/222.html";
    }

    @ResponseBody
    @GetMapping("/hello3")
    @Transactional
    public List<StudentEntity> getAllStudentJson(){
//        return studentDao.getAllStudents();
        return studentGeneralService.getAllStudent();
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

        DataSource bean2 = (DataSource) springIOC.getBean("dsConnection");
        System.out.println("THIS IS THE DS ADDRESS"+bean2);
        System.out.println("This is the value calculated by ejb "+bean1.doOperation(3,5));

        return "success";
    }

    @RequestMapping("/hello4")
    @Transactional
    @ResponseBody
    public List<CourseEntity> getStudentCourseTest(){
//       return studentDao.getStudentEntity(1).getCourseEntities();

       return studentGeneralService.getSingleStudent(1).getCourseEntities();

    }

    @RequestMapping("/hello5")
    @ResponseBody
    @Transactional
    public List<EJBCourseEntity> findCourceTest(){

        return courseService.findStudent("testcourse");
    }

    @RequestMapping("/hello6")
    public String sendMessage(){
        producerTest.sendMessage("time now is 2020");
        return "success";
    }

}
