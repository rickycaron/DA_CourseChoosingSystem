package com.a20da10.controller;

import com.a20da10.Entity.spring.CourseEntity;
import com.a20da10.Entity.spring.CourseTypeEnum;
import com.a20da10.service.ejb.InstructorGenServiceRemote;
import com.a20da10.service.ejb.InstructorSelfServiceRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*This controller is for students manipulating with courses*/
@Controller
@CrossOrigin(origins = "http://localhost:8081",allowCredentials = "true")
@RequestMapping("/courseInstructor")
public class CourseInstructorController {
    @Autowired
    InstructorGenServiceRemote instructorGenServiceRemote;

    @Autowired
    InstructorSelfServiceRemote instructorSelfServiceRemote;

    @Transactional
    @ResponseBody
    @RequestMapping("/getMycourses")
    public List<CourseEntity> getCoursesOfMine (){
        return instructorSelfServiceRemote.getCoursesOfMine();
    }


    @ResponseBody
    @RequestMapping("/GetCourseByInsId/{insId}")
    public List<CourseEntity>  getCourseByInsId(@PathVariable Integer insId) {
        return instructorGenServiceRemote.getCoursesByInsId(insId);
    }

    @PostMapping("/UpdateCourse")
    @Transactional
    @ResponseBody
    public boolean UpdateCourse(){
        return false;
    }


    @RequestMapping("/RemoveCourse")
    @Transactional
    @ResponseBody
    public List<CourseEntity> removeCourse(){
        return null;
    }

}
