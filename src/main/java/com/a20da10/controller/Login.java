package com.a20da10.controller;

import com.a20da10.service.spring.StudentSelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Login {

    @Autowired
    private StudentSelfService studentSelfService;

    @RequestMapping("/login")
    public String Login(){

    //1.Verification
    //2.Add studentId into service
        studentSelfService.setStudentId(1);
    //3.redirect to home page
        System.out.println(studentSelfService);
        return "success";
    }
    @RequestMapping("/")
    public String welcome(){
        return "home";
    }
}
