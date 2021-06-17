package com.a20da10.controller;

import com.a20da10.Entity.ejb.EJBInstructorEntity;
import com.a20da10.Entity.spring.StudentEntity;
import com.a20da10.service.ejb.AccountServiceLocal;
import com.a20da10.service.ejb.InstructorSelfServiceRemote;
import com.a20da10.service.spring.LoginOutAndRegisterService;
import com.a20da10.service.spring.StudentGeneralService;
import com.a20da10.service.spring.StudentSelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

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
    private StudentGeneralService studentGeneralService;

    @Autowired
    private StudentSelfService studentSelfService;

    @Autowired
    private LoginOutAndRegisterService logService;

    @Autowired
    private AccountServiceLocal accountServiceLocal;

    @Autowired
    private InstructorSelfServiceRemote instructorSelfServiceRemote;

    private final List<String> allowedOrigins = Arrays.asList("http://localhost:8081");//
    @PostMapping("/loginStudent")
    @ResponseBody
    public boolean Login(@RequestBody StudentEntity studentEntity, HttpSession session, HttpServletResponse response,HttpServletRequest request){
        //0.Fetching parameters
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
            //4.Set session attribute for interceptor checking later
            session.setAttribute("LOGIN_TYPE", "student");
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

    @PostMapping("/loginInstructor")
    @ResponseBody
    public boolean LoginIns(@RequestBody EJBInstructorEntity ejbInstructorEntity, HttpSession session, HttpServletResponse response, HttpServletRequest request){
        String email = ejbInstructorEntity.getEmail();
        String password = ejbInstructorEntity.getPassword();
        System.out.println("Email got from Vue" + email);
        System.out.println("Password got from Vue" + password);
        //1.Verification
        if (accountServiceLocal.InstructorAuthentication(email, password)) {
            //2.Add studentId into service
            Integer id = accountServiceLocal.getInstructorIdByEmail(email);
            System.out.println(email + password);
            //3.create stateful bean for later access
            instructorSelfServiceRemote.setInsId(id);
            System.out.println("InstructorSelfService id is" + id);
            //4.Set session attribute for interceptor checking later
            session.setAttribute("LOGIN_TYPE", "instructor");
            session.setAttribute("USER_SESSION",instructorSelfServiceRemote);
            //5.redirect to home page
            System.out.println(instructorSelfServiceRemote);
            System.out.println("instructor login success");

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
    @ResponseBody
    public boolean Logout(HttpSession session, SessionStatus sessionStatus) {
//        springIOC.getAutowireCapableBeanFactory().destroyBean(studentSelfService);
        session.invalidate();
        sessionStatus.setComplete();
//        System.out.println(this.getClass().getClassLoader().getResource("main.css").getPath());
        return true;
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

    @GetMapping("/students")
    @ResponseBody
    public List<StudentEntity> getAllStudentJson(HttpServletResponse response){
//        Response.setHeader.Add("Access-Control-Allow-Origin", "*");
//        response.setHeader("Access-Control-Allow-Origin", "*");
        System.out.println("Data is already sent!!!!!!!!!!!!");
        return studentGeneralService.getAllStudent();
    }

    @PostMapping("/registerStudent")
    @ResponseBody
    public boolean registerStudent(@RequestBody StudentEntity studentEntity) {

        if (studentGeneralService.getAllStudent().contains(studentEntity)) {
            return false;
        } else {
            String rawPass = studentEntity.getPassword();
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            studentEntity.setPassword(passwordEncoder.encode(rawPass));
            logService.register(studentEntity);
        }
        return true;
    }
    @PostMapping("/resetStudentPassword")
    @ResponseBody
    public boolean resetStudentPassword(@RequestBody StudentEntity studentEntity) {

        if (studentGeneralService.getAllStudent().contains(studentEntity)) {
            return false;
        } else {
            String rawPass = studentEntity.getPassword();
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            studentEntity.setPassword(passwordEncoder.encode(rawPass));
            studentGeneralService.updateStudent(studentEntity);
        }
        return true;
    }

    }


