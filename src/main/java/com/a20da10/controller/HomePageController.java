package com.a20da10.controller;

import com.a20da10.Entity.spring.StudentEntity;
import com.a20da10.activemq.MyMessageListener;
import com.a20da10.activemq.StatefulMessageListener;
import com.a20da10.service.spring.LoginOutAndRegisterService;
import com.a20da10.service.spring.StudentSelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

@Controller
@CrossOrigin(origins = "http://localhost:8081",allowCredentials = "true")
@RequestMapping("/welcome")
public class HomePageController<LoginOutAndRegisterSer> {

    @Autowired
    private StudentSelfService studentSelfService;

    @Autowired
    private LoginOutAndRegisterService logService;

    @Autowired
    private StatefulMessageListener statefulMessageListener;

    private final List<String> allowedOrigins = Arrays.asList("http://localhost:8081");// 允许跨域的地址
    @PostMapping("/loginStudent")
    @ResponseBody
//    public boolean Login(HttpServletRequest request,HttpSession session){
    public boolean Login(@RequestBody StudentEntity studentEntity, HttpSession session, HttpServletResponse response,HttpServletRequest request){

        //0.Fetching parameters
//        String email = request.getParameter("email");
//        String password = request.getParameter("password");
        String email = studentEntity.getEmail();
        String password = studentEntity.getPassword();
        System.out.println("Email got from Vue" + email);
        System.out.println("Password got from Vue" + password);

        //1.Verification
        if (logService.StudentAuthentication(email, password)) {
            //2.Add studentId into service
            Integer id = logService.getStudentIdByEmail(email);
            System.out.println(email + password);
            //3.create stateful bean for later access
            studentSelfService.setStudentId(id);
            System.out.println("studentSelfService id is" + id);
            statefulMessageListener.setStudentId(id);
            //4.Set session attribute for interceptor checking later
            session.setAttribute("USER_SESSION",studentSelfService);
            //5.redirect to home page
            System.out.println(studentSelfService);
            System.out.println("login success");



            response.setHeader("Access-Control-Allow-Headers", "Accept, Content-Type");
            response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
            String origin = request.getHeader("Origin");
            response.setHeader("Access-Control-Allow-Origin", "http://localhost:8081");
            // 是否允许浏览器携带用户身份信息（cookie）
            response.setHeader("Access-Control-Allow-Credentials","true");

            return true;

        }
        return false;
    }



    @RequestMapping("/logout")
    public String Logout(HttpSession session, SessionStatus sessionStatus) {
//        springIOC.getAutowireCapableBeanFactory().destroyBean(studentSelfService);
        session.invalidate();
        sessionStatus.setComplete();
        System.out.println(this.getClass().getClassLoader().getResource("main.css").getPath());
        return "home";
    }

    @RequestMapping("/")
    public String welcome(){
        return "login";
    }

    @RequestMapping("/hash")
    public void hashCodepassRui(){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        System.out.println("This is my password hashcode  :  "+passwordEncoder.encode("rui"));

    }

    @GetMapping("/myinfo")
    @ResponseBody
    public StudentEntity getMyInfo(){
        System.out.println(studentSelfService.getBasicInfo());
        return studentSelfService.getBasicInfo();
    }
}


