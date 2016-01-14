package com.audev.common.Entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by cosxt on 12.01.2016.
 */

@Entity
@Table(name = "chat")
public class Chat {
    @Id
    @GenericGenerator(name = "increment", strategy = "increment")
    @GeneratedValue(generator = "increment")
    @Column(name = "chat_id")
    private long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "lot_id", nullable = true)
    private Lot lot;
    @Temporal(TemporalType.DATE)
    @Column(name = "start_date", nullable = false, length = 10)
    private Date startDate;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "chat")
    private List<Message> messages;

    public Chat(){
        messages = new ArrayList<>();
        startDate = new Date();
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public Lot getLot() {
        return lot;
    }

    public void setLot(Lot lot) {
        this.lot = lot;
    }

    public long getId() {
        return id;
    }
}
