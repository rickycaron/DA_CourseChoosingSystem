package com.a20da10.controller;
//
//import com.a20da10.activemq.MyMessageListener;
//import com.a20da10.activemq.StatefulMessageListener;
import com.a20da10.activemq.StudentReceiver;
import com.a20da10.service.spring.LoginOutAndRegisterService;
import com.a20da10.service.spring.StudentSelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/welcome")
public class HomePageController<LoginOutAndRegisterSer> {

    @Autowired
    private StudentSelfService studentSelfService;

    @Autowired
    private LoginOutAndRegisterService logService;

    @Autowired
    private StudentReceiver studentReceiver;
//    @Autowired
//    private StatefulMessageListener statefulMessageListener;


    @RequestMapping("/loginStudent")
    public String Login(HttpServletRequest request,HttpSession session){
        //0.Fetching parameters
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        //1.Verification
        if (logService.StudentAuthentication(email, password)) {
            //2.Add studentId into service
            Integer id = logService.getStudentIdByEmail(email);
            System.out.println(email + password);
            //3.create stateful bean for later access
            studentSelfService.setStudentId(id);
            System.out.println("studentSelfService id is" + id);
            System.out.println(studentReceiver);
            studentReceiver.connectToReceiverService();
//            statefulMessageListener.setStudentId(id);
            //4.Set session attribute for interceptor checking later
            session.setAttribute("USER_SESSION",studentSelfService);
            //5.redirect to home page
            System.out.println(studentSelfService);
            System.out.println("login success");
            return "success";

        }
        return "success";
    }



    @RequestMapping("/logout")
    public String Logout(HttpSession session, SessionStatus sessionStatus) {
//        springIOC.getAutowireCapableBeanFactory().destroyBean(studentSelfService);
        session.invalidate();
        sessionStatus.setComplete();
        System.out.println(this.getClass().getClassLoader().getResource("main.css").getPath());
        return "home";
    }

    @RequestMapping("/")
    public String welcome(){
        return "login";
    }
}
