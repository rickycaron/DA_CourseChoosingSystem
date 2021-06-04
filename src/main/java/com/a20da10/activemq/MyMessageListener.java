package com.a20da10.activemq;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import javax.jms.*;

@Service
public class MyMessageListener implements MessageListener {
    @Override
    public void onMessage(Message message) {

        if (message !=null){
            TextMessage textMessage = (TextMessage) message;
            try {
                String txtMessage = textMessage.getText();
                System.out.println("receive message from listner with selector"+txtMessage);

            } catch (JMSException e) {
                e.printStackTrace();
            }

        }
    }
}
