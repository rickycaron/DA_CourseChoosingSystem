package com.a20da10.controller;

import com.a20da10.Entity.ejb.EJBInstructorEntity;
import com.a20da10.Entity.spring.StudentEntity;
import com.a20da10.service.ejb.InstructorGenServiceRemote;
import com.a20da10.service.spring.StudentGeneralService;
import com.a20da10.service.spring.UpdateTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;
import java.util.List;



@RestController
@CrossOrigin(origins = "http://localhost:8081",allowCredentials = "true")
//@CrossOrigin(origins = "http://localhost:8081",
//        allowCredentials = "true",
//        methods = {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE},
//        allowedHeaders = ("*"),
//        exposedHeaders = {"Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"})
@RequestMapping("/rest")
public class RESTController {

    @Autowired
    private StudentGeneralService studentGeneralService;

    @Autowired
    InstructorGenServiceRemote instructorGenServiceRemote;

    @GetMapping("/students")
    public List<StudentEntity> getAllStudentJson(HttpServletResponse response){
        return studentGeneralService.getAllStudent();
    }

    @GetMapping("/student/{studentId}")
    public StudentEntity getSingleStudent(@PathVariable int studentId){
        return studentGeneralService.getSingleStudent(studentId);
    }

    @PostMapping("/student")
    //auto change data format from json to pojo
    public StudentEntity addStudent(@RequestBody StudentEntity studentEntity){
        studentEntity.setStudentId(0);
        studentGeneralService.saveStudent(studentEntity);
        return studentEntity;
    }
    @PutMapping("/student")
    public StudentEntity updateStudent(@RequestBody StudentEntity studentEntity){
        //here the studentId is not null or 0,therefore it will update instead of adding
        int studentId =studentEntity.getStudentId();
        if( studentId!= 0){
            StudentEntity source= studentGeneralService.getSingleStudent(studentId);
            UpdateTool.copyNullProperties(source, studentEntity);
        }
        studentGeneralService.updateStudent(studentEntity);
        return studentEntity;
    }
    @DeleteMapping("/delete/{studentId}")
    public String deleteStudent(@PathVariable int studentId){

        StudentEntity studentEntity = studentGeneralService.getSingleStudent(studentId);
        if (studentEntity==null){
            return "student with id = "+studentId+" is not found";
        }
        studentGeneralService.deleteStudent(studentId);
        return "success";
    }

//    @ResponseBody
//    @RequestMapping("/Instructors")
//    public List<EJBInstructorEntity> getAllIns() {
//        return instructorGenServiceRemote.getAllInstructors();
//    }


}
