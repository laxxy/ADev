package com.audev.common.Entity;

import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.annotations.GenericGenerator;

import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Lot
 */
@Entity
@Table(name = "lot")
public class Lot {

    public interface Public{}

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name= "increment", strategy= "increment")
    @Column(name = "lot_id")
    private long id;
    @Size(min = 1, max = 40, message = "Sorry, name must have at least 1 dig.")
    @Column(name = "name")
    private String lotName;
    @Size(min = 1, message = "Please, enter information about your lot")
    @Column(name = "lot_info")
    private String lotInfo;
    @Temporal(TemporalType.DATE)
    @Column(name = "start_date", nullable = false, length = 10)
    private Date dateOfStart;
    @Temporal(TemporalType.DATE)
    @Column(name = "end_date", nullable = true, length = 10)
    private Date dateOfEnd;
    @Column(name = "bid_current")
    private double bidCurrent;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_id", nullable = false)
    private SubCategory subCategory;
    @Column(name = "view_counter")
    private int viewCounter;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "lot")
    private Set<Chat> chats = new HashSet<>();
    @Column(name = "images")
    ArrayList<String> images;

    public Lot() {
        dateOfStart = new Date();
    }

    public Lot(SubCategory subCategory) {
        this.subCategory = subCategory;
        dateOfStart = new Date();
    }

    @JsonView(Public.class)
    public long getId() {
        return id;
    }

    @JsonView(Public.class)
    public String getLotName() {
        return lotName;
    }

    public void setLotName(String lotName) {
        this.lotName = lotName;
    }

    @JsonView(Public.class)
    public String getLotInfo() {
        return lotInfo;
    }

    public void setLotInfo(String lotInfo) {
        this.lotInfo = lotInfo;
    }

    @JsonView(Public.class)
    public Date getDateOfStart() {
        return dateOfStart;
    }

    public void setDateOfStart(Date dateOfStart) {
        this.dateOfStart = dateOfStart;
    }

    public Date getDateOfEnd() {
        return dateOfEnd;
    }

    public void setDateOfEnd(Date dateOfEnd) {
        this.dateOfEnd = dateOfEnd;
    }

    @JsonView(Public.class)
    public double getBidCurrent() {
        return bidCurrent;
    }

    public void setBidCurrent(double bidCurrent) {
        this.bidCurrent = bidCurrent;
    }

    public SubCategory getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(SubCategory subCategory) {
        this.subCategory = subCategory;
    }

    public int getViewCounter() {
        return viewCounter;
    }

    public void incrementViewCounter() {
        this.viewCounter++;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Chat> getChats() {
        return chats;
    }

    public void setChats(Set<Chat> chats) {
        this.chats = chats;
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lot lot = (Lot) o;
        return Objects.equals(id, lot.id) &&
                Objects.equals(bidCurrent, lot.bidCurrent) &&
                Objects.equals(lotName, lot.lotName) &&
                Objects.equals(lotInfo, lot.lotInfo) &&
                Objects.equals(dateOfStart, lot.dateOfStart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lotName, lotInfo, dateOfStart, bidCurrent, subCategory, user);
    }
}
