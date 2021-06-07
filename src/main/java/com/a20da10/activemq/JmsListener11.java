package com.a20da10.activemq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.support.JmsAccessor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageListener;

@Service
public class JmsListener11 {


//    @Resource(name = "defaultMessageListenerContainer")
//    DefaultMessageListenerContainer defaultMessageListenerContainer;
//
//
//    public void setMyMessageListener(MessageListener myMessageListener) {
//        defaultMessageListenerContainer.setMessageListener(myMessageListener);
//    }
//    public void setMyMessageSelector(String myMessageSelector) {
//        defaultMessageListenerContainer.setMessageSelector(myMessageSelector);
//    }

}
