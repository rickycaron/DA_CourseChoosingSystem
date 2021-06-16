package com.a20da10.controller;

import com.a20da10.Entity.spring.CourseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*This controller is for students manipulating with courses*/
@Controller
@CrossOrigin(origins = "http://localhost:8081",allowCredentials = "true")
@RequestMapping("/courseInstructor")
public class CourseInstructorController {

    /*Get all the courses this instructor currently is teaching
     * input:(default the current instructor id)
     * output: all the courses basic information in JSON array
     * */
    @RequestMapping("/getMycourses")
    @Transactional
    @ResponseBody
    public List<CourseEntity> getMyCourses(){
        return null;
    }

    /* Add a course
     * input: (default the current instructor id) + JSON Array:{courseName, courseDeatils,...}
     * output: true/false
     * */
    @PostMapping("/AddCourse")
    @Transactional
    @ResponseBody
    public boolean addCourse(){
        return false;
    }

    /* Update a course info
     * input: course
     * output: true/false
     * */
    @PostMapping("/AddCourse")
    @Transactional
    @ResponseBody
    public boolean UpdateCourse(){
        return false;
    }

    /* Remove a course
     * input: courseId
     * output: true/false
     * */
    @RequestMapping("/RemoveCourse")
    @Transactional
    @ResponseBody
    public List<CourseEntity> removeCourse(){
        return null;
    }




}
