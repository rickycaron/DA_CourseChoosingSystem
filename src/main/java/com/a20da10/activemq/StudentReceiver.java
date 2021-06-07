package com.a20da10.activemq;

import com.a20da10.service.spring.StudentGeneralService;
import com.a20da10.service.spring.StudentSelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

@Component
//@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class StudentReceiver {

@Autowired
    StudentSelfService studentSelfService;

@Autowired
    StudentGeneralService studentGeneralService;

public void connectToReceiverService(){
    System.out.println(studentSelfService.getStudentId());
    System.out.println("---------------------------------------");
//    5c3ae3d2
    System.out.println(studentSelfService);
    System.out.println("---------------------------------------");

}
    @JmsListener(destination = "myQueue", containerFactory = "jmsContainerFactory", selector = "role = 'student'")
    public void receiveStudentMessage(Message message, Session session) throws JMSException {

        System.out.println("-------------------------------------");
        System.out.println(message.getJMSCorrelationID());
        //this is the id of sending address
        Integer senderId =message.getIntProperty("senderId");
        Integer receiver =message.getIntProperty("receiverId");
        System.out.println("---------------------------1111----------");

        System.out.println("sender id is  "+senderId);
        System.out.println("sender id is  "+receiver);

        System.out.println("-------------------------------------");

//        System.out.println("send to user  "+ id + "from user" + userId);
        System.out.println("-------------------------------------");

        if (message !=null ){
            TextMessage textMessage = (TextMessage) message;
            try {

                String txtMessage = textMessage.getText();
                System.out.println("this is the message get from controller with selector"+txtMessage);
                //here persist the message
                System.out.println(studentGeneralService.getAllStudent());
            } catch (JMSException e) {
                e.printStackTrace();
            }

        }

    }
}
