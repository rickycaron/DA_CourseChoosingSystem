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


    /*Return all the details information together with teacher information about a course
    * input:courseId
    * output:JSON course details + teacher details
    * */
    @RequestMapping("/getCourseById/{courseId}")
    @ResponseBody
    public CourseEntity getCourseById(@PathVariable Integer courseId){
        return studentGeneralService.getCourseById(courseId);
    }

    /*Get all the courses this student currently is enrolled
    * input:(default the current student id)
    * output: courses basic information in JSON array
    * */
    @RequestMapping("/getMycourses")
    @Transactional
    @ResponseBody
    public List<CourseEntity> getMyCourses(){
        return studentSelfService.getBasicInfo().getCourseEntities();
    }


    /*Get all the courses this student currently is not enrolled, but can enroll later
     * input:(default the current student id)
     * output: courses basic information in JSON array
     * */
    @RequestMapping("/getMyAvailableCourses")
    @ResponseBody
    public List<CourseEntity> getAllCoursesAvaliable(){
        StudentEntity studentEntity = studentSelfService.getBasicInfo();
        List<CourseEntity> allCourses = studentGeneralService.getAllCourses();
        allCourses.removeIf(e->e.getStudentEntities().contains(studentEntity));
        System.out.println("----------------------");
        return allCourses;
    }

    /*subscribe a student to a course
     * input: courseId, (current studentId)
     * output: true/false
     */
    @RequestMapping("/subscribeCourse/{courseId}")
    @Transactional
    @ResponseBody
    public CourseEntity subscribeCourse(@PathVariable Integer courseId){
        CourseEntity courseEntity = courseDao.getCourseEntity(courseId);
        studentSelfService.subscribeCourse(courseEntity);
        return courseEntity;
    }

    /*cancel a student to a course
     * input: courseId, (current studentId)
     * output: true/false
     * */
    @RequestMapping("/cancelCourse/{courseId}")
    @ResponseBody
    public CourseEntity cancelCourse(@PathVariable Integer courseId){
        return null;
    }

    /*Below is some optional api functions*/

    /*Get all the courses of a student
     * input: the student id)
     * output: courses basic information in JSON array
     * */
    @RequestMapping("/getEnrolledCourses")
    @Transactional
    @ResponseBody
    public List<CourseEntity> getEnrolledCourses(){
        return null;
    }

    /*enroll a student to a course, this function is used by instructor
     * input: courseId, studentId
     * output: true/false
     */


    /* disenroll a student from a course, this function is used by instructor
     * input: courseId,  studentId
     * output: true/false
     * */
    @RequestMapping("/disenrollCourse/{courseId}")
    @ResponseBody
    public CourseEntity disenrollStudentFromCourse(@PathVariable Integer courseId){

        return studentSelfService.cancelCourse(studentSelfService.getBasicInfo(), courseId);
    }






}
