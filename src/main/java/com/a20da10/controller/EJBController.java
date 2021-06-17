package com.a20da10.controller;

import com.a20da10.Entity.ejb.EJBInstructorEntity;
import com.a20da10.Entity.spring.CourseEntity;
import com.a20da10.Entity.spring.CourseTypeEnum;
import com.a20da10.service.ejb.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@RequestMapping("/EJB")
public class EJBController {

    @Autowired
    private InstructorSelfServiceLocal instructorSelfServiceLocal;

    @Autowired
    private InstructorSelfServiceRemote instructorSelfServiceRemote;

    @Autowired
    private InstructorGenServiceLocal instructorGenServiceLocal;

    @Autowired
    private InstructorGenServiceRemote instructorGenServiceRemote;

    @Autowired
    private AccountServiceLocal accountServiceLocal;

    @Autowired
    private MyTimerServiceRemote myTimerServiceRemote;

//    @Autowired
//    private MyTimerServiceRemote myTimerServiceRemote;

    @RequestMapping("/loginInstructor")
    public String LoginIns(HttpServletRequest request, HttpSession session){
        //0.Fetching parameters
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        //1.Verification
        if (accountServiceLocal.InstructorAuthentication(email, password)) {
            //2.Add studentId into service
            Integer id = accountServiceLocal.getInstructorIdByEmail(email);
            System.out.println(email + password);
            //3.create stateful bean for later access
            instructorSelfServiceRemote.setInsId(id);
            System.out.println("InstructorSelfService id is" + id);
            //4.Set session attribute for interceptor checking later
            session.setAttribute("USER_SESSION",instructorSelfServiceRemote);
            //5.redirect to home page
            System.out.println(instructorSelfServiceRemote);
            System.out.println("login success");
            return "InstructorMainPage";
        }
        return "loginFail";
    }

    @ResponseBody
    @RequestMapping("/TestSout")
    public String sout(){
        String message = "This is a test print out method";
        System.out.println(message);
        return message;
    }

    @ResponseBody
    @RequestMapping("/SetInsId")
    public int setInsId(){
        instructorSelfServiceRemote.setInsId(2);
        return instructorSelfServiceRemote.getInsId();
    }

    @ResponseBody
    @RequestMapping("/GetCoursesOfMine")
    public List<CourseEntity> getCoursesOfMine (){
        return instructorSelfServiceRemote.getCoursesOfMine();
    }

    @ResponseBody
    @RequestMapping("/SetTimeOut")
    public String setTimeOut() {
        myTimerServiceRemote.setTimer(5000);
        String message = "Test the set timeout method";
        return message;
    }

    @ResponseBody
    @RequestMapping("/GetAll")
    public List<EJBInstructorEntity> getAllIns() {
        return instructorGenServiceRemote.getAllInstructors();
    }

    @ResponseBody
    @RequestMapping("/GetInsById")
    public EJBInstructorEntity getInsById() {
        return instructorGenServiceRemote.getInstructorByInsId(1);
    }

    @ResponseBody
    @RequestMapping("/GetCourseByInsId")
    public List<CourseEntity>  getCourseByInsId() {
        return instructorGenServiceRemote.getCoursesByInsId(1);
    }

    @ResponseBody
    @RequestMapping("/GetMyInfo")
    public EJBInstructorEntity getMyInfo() {
        return instructorSelfServiceLocal.getMyInfo();
    }

}
