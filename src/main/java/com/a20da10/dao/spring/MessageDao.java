package com.a20da10.dao.spring;

import com.a20da10.Entity.spring.TextMessageEntity;

import java.util.Date;
import java.util.List;

public interface MessageDao {

    public void persistTextMessge(Integer senderId, Integer receriverId, String txt, Date date);
    public List<TextMessageEntity> getAllTextMessageById(Integer receiver);

}
