package com.a20da10.controller;

import com.a20da10.Entity.spring.StudentEntity;
import com.a20da10.service.spring.StudentGeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;
import java.util.List;


//cotroller + responseBody
@RestController
@CrossOrigin(origins = "http://localhost:8081",allowCredentials = "true")
@RequestMapping("/rest")
public class RESTController {

    @Autowired
    private StudentGeneralService studentGeneralService;

    @GetMapping("/students")
    public List<StudentEntity> getAllStudentJson(HttpServletResponse response){
//        Response.setHeader.Add("Access-Control-Allow-Origin", "*");
//        response.setHeader("Access-Control-Allow-Origin", "*");
        System.out.println("Data is already sent!!!!!!!!!!!!");
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
}
