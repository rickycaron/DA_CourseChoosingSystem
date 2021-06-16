package com.a20da10.controller;

import com.a20da10.Entity.spring.CourseEntity;
import com.a20da10.Entity.spring.StudentEntity;
import com.a20da10.Entity.spring.TextMessageEntity;
import com.a20da10.activemq.ConsumerTest;
import com.a20da10.activemq.JmsListener11;
import com.a20da10.activemq.ProducerTest;
import com.a20da10.activemq.StudentReceiver;
import com.a20da10.dao.spring.CourseDao;
import com.a20da10.dao.spring.MessageDao;
import com.a20da10.service.spring.StudentGeneralService;
import com.a20da10.service.spring.StudentSelfService;
import com.a20da10.service.spring.UpdateTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

@Controller
@RequestMapping("/hello")
@CrossOrigin(origins = "http://localhost:8081",allowCredentials = "true")
public class HelloController {
//    @Autowired
//    private StudentDao studentDao;

    @Autowired
    private  WebApplicationContext springMVCIOC;

    @Autowired
    private StudentGeneralService studentGeneralService;

    @Autowired
    private StudentSelfService studentSelfService;

    @Autowired
    private ProducerTest producerTest;

    @Autowired
    ConsumerTest consumerTest;

    @Resource(name = "myMessageListener")
    MessageListener messageListener;

    @Autowired
    JmsListener11 jmsListener;

    @Autowired
    MessageDao messageDao;

    @Autowired
    CourseDao courseDao;

    @Autowired
    StudentReceiver studentReceiver;

    @RequestMapping(value = "/hello1")
    @ResponseBody
    public String getAllStudent(HttpServletRequest request,Model model) throws SQLException, PropertyVetoException, ClassNotFoundException {

        System.out.println("this is the student info"+studentGeneralService.getAllStudent());

        model.addAttribute("student","name is shuai");


        return "success!!";
//        return studentDao.getAllStudents();
//       return "forward:/WEB-INF/222.html";
    }

    @ResponseBody
    @GetMapping("/hello3")
    public List<StudentEntity> getAllStudentJson(){

        return studentGeneralService.getAllStudent();
    }


    @RequestMapping(value = "hello2")
    public String getEJB(HttpServletRequest request, HttpServletResponse response) throws NamingException {


        Properties props = new Properties();

//        1.Get the context of server
        ServletContext sc = request.getSession().getServletContext();

//        2.Get the context of Spring IOC
        WebApplicationContext springIOC= WebApplicationContextUtils.getWebApplicationContext(sc);

//        3.Get the context of Spring MVC IOC
        WebApplicationContext springMVCIOC = RequestContextUtils.findWebApplicationContext(request);

//        MyFirstBeanLocal bean1 = (MyFirstBeanLocal) springMVCIOC.getBean("myBean");
//
//        DataSource bean2 = (DataSource) springIOC.getBean("dsConnection");
//        System.out.println("THIS IS THE DS ADDRESS"+bean2);
//        System.out.println("This is the value calculated by ejb "+bean1.doOperation(3,5));
//
        return "success";
    }

    @RequestMapping("/hello4")
    @Transactional
    @ResponseBody
    public List<CourseEntity> getStudentCourseTest(){
       return studentGeneralService.getSingleStudent(1).getCourseEntities();
    }

    @RequestMapping("/hello11")
    @ResponseBody
    public void sendMessage(){
        producerTest.sendMessage("time now is 1305 ",1,3);
    }
    @RequestMapping("/hello12")
    @ResponseBody
    public List<TextMessageEntity> getMessage(){

        return messageDao.getAllTextMessageById(3);
    }

    @PostMapping("/sendMyMessage")
    @ResponseBody
    public void produceMessage(@RequestBody TextMessageEntity textMessageEntity){
        producerTest.sendMessage(textMessageEntity.getTextMessage(),
                                    textMessageEntity.getSenderId(),
                                    textMessageEntity.getReceiverId());
    }
    @GetMapping("/getMyMessages")
    @ResponseBody
    public List<TextMessageEntity> getMessages(){

        return messageDao.getAllTextMessageById(studentSelfService.getStudentId());
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
        StudentEntity studentEntity =studentGeneralService.getSingleStudent(3);
        List<CourseEntity> allCourses = studentGeneralService.getAllCourses();
        allCourses.removeIf(e->e.getStudentEntities().contains(studentEntity));
        System.out.println("----------------------");
        return allCourses;
    }

    @RequestMapping("/subscribeCourse/{courseId}")
    @ResponseBody
    public CourseEntity subscribeCourse(@PathVariable Integer courseId){
        CourseEntity courseEntity = courseDao.getCourseEntity(courseId);
        studentSelfService.subscribeCourse(courseEntity);
        return courseEntity;
    }

    @RequestMapping("/cancelCourse/{courseId}")
    @ResponseBody
    public CourseEntity cancelCourse(@PathVariable Integer courseId){

        return studentSelfService.cancelCourse(studentSelfService.getBasicInfo(), courseId);
    }

    @PutMapping("/updateProfile")
    @ResponseBody
    public StudentEntity updateStudent(@RequestBody StudentEntity studentEntity, HttpServletResponse response, HttpServletRequest request){
        //here the studentId is not null or 0,therefore it will update instead of adding
        int studentId =studentEntity.getStudentId();
        if( studentId!= 0){
            StudentEntity source= studentGeneralService.getSingleStudent(studentId);
            UpdateTool.copyNullProperties(source, studentEntity);
        }
        studentGeneralService.updateStudent(studentEntity);
        return studentEntity;
    }
    @RequestMapping("/getCourseById/{courseId}")
    @ResponseBody
    public CourseEntity getCourseById(@PathVariable Integer courseId){

        return studentGeneralService.getCourseById(courseId);

    }

}
