package com.a20da10.controller;

import com.a20da10.Entity.spring.CourseEntity;
import com.a20da10.Entity.spring.StudentEntity;
import com.a20da10.dao.spring.CourseDao;
import com.a20da10.service.spring.StudentGeneralService;
import com.a20da10.service.spring.StudentSelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


/*This controller is for students manipulating with courses*/
@Controller
@CrossOrigin(origins = "http://localhost:8081",allowCredentials = "true")
@RequestMapping("/courseStudent")
public class CourseStudentController {
    @Autowired
    StudentSelfService studentSelfService;

    @Autowired
    private StudentGeneralService studentGeneralService;

    @Autowired
    CourseDao courseDao;

    @RequestMapping("/getCourseById/{courseId}")
    @ResponseBody
    public CourseEntity getCourseById(@PathVariable Integer courseId){
        return studentGeneralService.getCourseById(courseId);
    }

    @RequestMapping("/getMycourses")
    @Transactional
    @ResponseBody
    public List<CourseEntity> getMyCourses(){
        return studentSelfService.getBasicInfo().getCourseEntities();
    }


    @RequestMapping("/getMyAvailableCourses")
    @ResponseBody
    public List<CourseEntity> getAllCoursesAvaliable(){
        StudentEntity studentEntity = studentSelfService.getBasicInfo();
        List<CourseEntity> allCourses = studentGeneralService.getAllCourses();
        allCourses.removeIf(e->e.getStudentEntities().contains(studentEntity));
        System.out.println("----------------------");
        return allCourses;
    }


    @RequestMapping("/subscribeCourse/{courseId}")
    @Transactional
    @ResponseBody
    public CourseEntity subscribeCourse(@PathVariable Integer courseId, HttpServletResponse response){
        CourseEntity courseEntity = studentGeneralService.getCourseById(courseId);
        studentSelfService.subscribeCourse(courseEntity);
        response.setHeader("Access-Control-Allow-Headers", "Accept, Content-Type");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:8081");
        response.setHeader("Access-Control-Allow-Credentials","true");
        return courseEntity;
    }


    @RequestMapping("/disenrollCourse/{courseId}")
    @ResponseBody
    public CourseEntity disenrollStudentFromCourse(@PathVariable Integer courseId, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Headers", "Accept, Content-Type");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:8081");
        response.setHeader("Access-Control-Allow-Credentials","true");

        return studentSelfService.cancelCourse(studentSelfService.getBasicInfo(), courseId);
    }






}
