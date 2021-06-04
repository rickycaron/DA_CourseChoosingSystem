package com.a20da10.activemq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;

@Service
public class ConsumerTest {
    @Autowired
    private JmsTemplate jmsTemplate;


    private int studentId =1;

    public void receiveMessage(){
        String messageSelector = "studentIdSelector='" +studentId+"'";
        jmsTemplate.receiveSelectedAndConvert(messageSelector);
        String receivedMessage = (String) jmsTemplate.receiveAndConvert();
        System.out.println("this is the received message from "+studentId+" "+receivedMessage);
    }
}
