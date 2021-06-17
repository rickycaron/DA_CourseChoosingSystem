package com.a20da10.controller;

import com.a20da10.Entity.ejb.EJBInstructorEntity;
import com.a20da10.Entity.spring.CourseEntity;
import com.a20da10.service.ejb.InstructorGenServiceRemote;
import com.a20da10.service.ejb.InstructorSelfServiceRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@CrossOrigin(origins = "http://localhost:8081",allowCredentials = "true")
@RequestMapping("/instructorRest")
public class InstructorController {

    @Autowired
    InstructorGenServiceRemote instructorGenServiceRemote;

    @Autowired
    InstructorSelfServiceRemote instructorSelfServiceRemote;

    @ResponseBody
    @RequestMapping("/instructors")
    public List<EJBInstructorEntity> getAllIns() {
        return instructorGenServiceRemote.getAllInstructors();
    }

    @ResponseBody
    @RequestMapping("/instructor/{insId}")
    public EJBInstructorEntity getInsById(@PathVariable Integer insId) {
        return instructorGenServiceRemote.getInstructorByInsId(insId);
    }

    @ResponseBody
    @RequestMapping("/CoursesOfInstructor/{insId}")
    public List<CourseEntity>  getCourseByInsId(@PathVariable Integer insId) {
        return instructorGenServiceRemote.getCoursesByInsId(insId);
    }

}
