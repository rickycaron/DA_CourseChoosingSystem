package com.a20da10.activemq;

import com.a20da10.dao.spring.MessageDao;
import com.a20da10.dao.spring.StudentDao;
import com.a20da10.service.spring.StudentGeneralService;
import com.a20da10.service.spring.StudentSelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

@Component
@Transactional
//@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class StudentReceiver {

@Resource(name = "studentDaoImpl")
    StudentDao studentDao;

    @Resource(name = "messageDaoImpl")
    MessageDao messageDao;

    @JmsListener(destination = "myQueue", containerFactory = "jmsContainerFactory", selector = "role = 'student'")
    public void receiveStudentMessage(Message message, Session session) throws JMSException {

        //this is the id of sending address
        Integer senderId =message.getIntProperty("senderId");
        Integer receiverId =message.getIntProperty("receiverId");


        if (message !=null ){
            TextMessage textMessage = (TextMessage) message;
            try {

                String txtMessage = textMessage.getText();
                System.out.println("this is the message get from controller with selector "+txtMessage);
                //here persist the message
                messageDao.persistTextMessge(senderId,receiverId,txtMessage);
            } catch (JMSException e) {
                e.printStackTrace();
            }

        }

    }
}
