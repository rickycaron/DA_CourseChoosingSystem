package com.a20da10.controller;

import com.a20da10.service.spring.StudentSelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpSession;

@Controller
public class HomePageController {

    @Autowired
    private StudentSelfService studentSelfService;


    @RequestMapping("/login")
    public String Login(String email,String password,Integer id){
    //1.Verification
    //2.Add studentId into service
        System.out.println(email + password);
        studentSelfService.setStudentId(id);
    //3.redirect to home page
        System.out.println(studentSelfService);
        System.out.println("studentSelfService id is" + id);
        return "success";
    }

    @RequestMapping("/logout")
    public String Logout(HttpSession session, SessionStatus sessionStatus){
//        springIOC.getAutowireCapableBeanFactory().destroyBean(studentSelfService);
        session.invalidate();
        sessionStatus.setComplete();
        return "home";
    }



    @RequestMapping("/")
    public String welcome(){
        return "home";
    }
}
