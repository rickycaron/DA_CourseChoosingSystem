package com.a20da10.Entity.spring;

import sun.util.calendar.BaseCalendar;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorValue("mess")
@DiscriminatorColumn(name = "DTYPE")
@Table(name = "message")
public class MessageEntity {
    @Id
    @Column(name = "messageId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int messageId;
    private Integer senderId;
    private Integer receiverId;


    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Temporal(TemporalType.DATE)
    private java.util.Date date;


    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    @Basic
    @Column(name = "senderId", nullable = true)
    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    @Basic
    @Column(name = "receiverId", nullable = true)
    public Integer getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageEntity that = (MessageEntity) o;
        return messageId == that.messageId && Objects.equals(senderId, that.senderId) && Objects.equals(receiverId, that.receiverId) && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(messageId, senderId, receiverId, date);
    }
}
