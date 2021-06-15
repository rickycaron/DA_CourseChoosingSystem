package com.a20da10.Entity.spring;

import javax.persistence.*;
import java.util.Objects;

@Entity
@DiscriminatorValue("txt")
@Table(name = "TextMessage", schema = "school")
public class TextMessageEntity extends MessageEntity {

    @Column(name = "text",length = 500)
    private String textMessage;


    public String getTextMessage() {
        return textMessage;
    }

    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }

    public String getText() {
        return textMessage;
    }

    public void setText(String text) {
        this.textMessage = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TextMessageEntity that = (TextMessageEntity) o;
        return Objects.equals(textMessage, that.textMessage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), textMessage);
    }
}
