package com.a20da10.controller;

import com.a20da10.Entity.spring.CourseEntity;
import com.a20da10.Entity.spring.CourseTypeEnum;
import com.a20da10.service.ejb.InstructorGenServiceRemote;
import com.a20da10.service.ejb.InstructorSelfServiceRemote;
import com.a20da10.service.spring.UpdateTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
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

    @ResponseBody
    @RequestMapping("/GetCourse/{courseId}")
    public CourseEntity  getCourse(@PathVariable Integer courseId) {
        return instructorGenServiceRemote.getCourseById(courseId);
    }

//    @PostMapping("/UpdateCourse")
//    @Transactional
//    @ResponseBody
//    public boolean UpdateCourse(){
//        return false;
//    }

    @ResponseBody
    @RequestMapping("/AddNewCourse")
    public CourseEntity addNewCourse(@RequestBody CourseEntity courseEntity,HttpServletResponse response) {
        instructorSelfServiceRemote.addNewCourse(courseEntity);
        response.setHeader("Access-Control-Allow-Headers", "Accept, Content-Type");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:8081");
        response.setHeader("Access-Control-Allow-Credentials","true");
        return courseEntity;
    }

    @ResponseBody
    @RequestMapping("/DeleteCourse/{courseId}")
    public boolean deleteCourse(@PathVariable int courseId, HttpServletResponse response){
        CourseEntity courseEntity = instructorGenServiceRemote.getCourseById(courseId);
        if (courseEntity == null){
            System.out.println("course with id = "+courseId+" is not found");
            return false;
        }
//        System.out.println("-------------------------Begin to delete course to the data base----------------------------");
        instructorSelfServiceRemote.deleteCourse(courseId);
//        System.out.println("-------------------------Finishing the delete course to the data base----------------------------");
        response.setHeader("Access-Control-Allow-Headers", "Accept, Content-Type");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:8081");
        response.setHeader("Access-Control-Allow-Credentials","true");
        return true;
    }

    @ResponseBody
    @RequestMapping("/UpdateCourseInfo")
    public CourseEntity updateCourseInfo(@RequestBody CourseEntity courseEntity, HttpServletResponse response) {
        System.out.println("--------------------------------Begin to update the course------------------------------");
        int courseId = courseEntity.getCourseId();
        System.out.println("--------------------------------Begin to update the course of id "+ courseId +"------------------------------");
        if( courseId != 0){
            CourseEntity source = instructorGenServiceRemote.getCourseById(courseId);
            UpdateTool.copyNullProperties(source, courseEntity);
        }
        instructorSelfServiceRemote.updateCourseInfo(courseEntity);
        System.out.println("-------------------------Finishing the update course to the data base----------------------------");
        response.setHeader("Access-Control-Allow-Headers", "Accept, Content-Type");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:8081");
        response.setHeader("Access-Control-Allow-Credentials","true");
        return courseEntity;
    }


}
