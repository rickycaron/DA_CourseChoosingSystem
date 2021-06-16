package com.a20da10.dao.spring;

import com.a20da10.Entity.spring.StudentEntity;
import com.a20da10.Entity.spring.TextMessageEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;
import java.util.List;

@Repository
public class MessageDaoImpl implements MessageDao{
    @Autowired
    SessionFactory sessionFactory;
    @Override
    public void persistTextMessge(Integer senderId, Integer receriverId, String txt, Date date) {
        if (txt!=null){
            TextMessageEntity textMessageEntity = new TextMessageEntity();
            textMessageEntity.setTextMessage(txt);
            textMessageEntity.setSenderId(senderId);
            textMessageEntity.setReceiverId(receriverId);
            textMessageEntity.setDate(date);
            System.out.println(textMessageEntity.toString());
            Session session =sessionFactory.getCurrentSession();
            session.persist(textMessageEntity);
            System.out.println("this is the message to be persist" + txt);
            System.out.println("service persiste message");
        }
    }


    @Override
    @Transactional
    public List<TextMessageEntity> getAllTextMessageById(Integer receiver) {

        Session session = sessionFactory.getCurrentSession();
        Query query =session.createQuery("select t from TextMessageEntity t where t.receiverId = :receiverId",TextMessageEntity.class);
        query.setParameter("receiverId",receiver);
        return query.getResultList();

    }
}
