package com.a20da10.controller;

import com.a20da10.Entity.spring.TextMessageEntity;
import com.a20da10.activemq.ProducerTest;
import com.a20da10.dao.spring.MessageDao;
import com.a20da10.service.spring.StudentSelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/message")
@CrossOrigin(origins = "http://localhost:8081",allowCredentials = "true")
public class MessageController {
    @Autowired
    ProducerTest producerTest;

    @Autowired
    MessageDao messageDao;

    @Autowired
    private StudentSelfService studentSelfService;

    /*send a JSON */
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
}
