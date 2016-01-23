package com.audev.common.Entity;

import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by cosxt on 12.01.2016.
 */
@Entity
@Table(name = "message")
public class Message {

    public interface PublicMessage{}

    @Id
    @GenericGenerator(name = "increment", strategy = "increment")
    @GeneratedValue(generator = "increment")
    @Column(name = "message_id")
    private long id;
    @Column(name = "message")
    private String message;
    @Temporal(TemporalType.DATE)
    @Column(name = "date", nullable = false, length = 10)
    private Date date;
    @Column(name = "is_readed")
    private boolean isReaded;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "chat_id", nullable = true)
    private Chat chat;
    @Column(name = "autor_name")
    private String author;

    public Message() {
        date = new Date();
    }

    public long getId() {
        return id;
    }

    @JsonView(PublicMessage.class)
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @JsonView(PublicMessage.class)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @JsonView(PublicMessage.class)
    public boolean isReaded() {
        return isReaded;
    }

    public void setIsReaded(boolean isReaded) {
        this.isReaded = isReaded;
    }

    @JsonView(PublicMessage.class)
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String authorName) {
        this.author = authorName;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }
}
