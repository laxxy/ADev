package com.audev.common.Entity;

import com.audev.common.Entity.Enums.Category;
import com.audev.common.Entity.Enums.Delivery;
import com.audev.common.Entity.Enums.Pay;
import org.hibernate.annotations.GenericGenerator;

import java.util.*;

import javax.persistence.*;

/**
 * Created by cosxt on 28.11.2015.
 */
@Entity
@Table(name = "Lot")
public class Lot {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name= "increment", strategy= "increment")
    @Column(name = "id")
    private int id;
    @Column(name = "lot_name")
    private String lotName;
    @Column(name = "lot_info")
    private String lotInfo;
    @Column(name = "date_of_start")
    private Date dateOfStart;
    @Column(name = "date_of_end")
    private Date dateOfEnd;
    @Enumerated(EnumType.STRING)
    private Category category;
    @Column(name = "bid_initial")
    private double bidInitial;
    @Column(name = "bid_current")
    private double bidCurrent;
    @Column(name = "bid_buy_now")
    private double bidBuyNow;
    private transient Set<Delivery> bidDelivery;
    private transient Set<Pay> bidPay;
    //bid history
    private transient Map<User, Double> bidAll;

    public Lot() {
        bidDelivery = new HashSet<Delivery>();
        bidPay = new HashSet<Pay>();
        bidAll = new TreeMap<User, Double>();
    }

    public int getId() {
        return id;
    }

    public String getLotName() {
        return lotName;
    }

    public void setLotName(String lotName) {
        this.lotName = lotName;
    }

    public String getLotInfo() {
        return lotInfo;
    }

    public void setLotInfo(String lotInfo) {
        this.lotInfo = lotInfo;
    }

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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public double getBidInitial() {
        return bidInitial;
    }

    public void setBidInitial(double bidInitial) {
        this.bidInitial = bidInitial;
    }

    public double getBidCurrent() {
        return bidCurrent;
    }

    public void setBidCurrent(double bidCurrent) {
        this.bidCurrent = bidCurrent;
    }

    public double getBidBuyNow() {
        return bidBuyNow;
    }

    public void setBidBuyNow(double bidBuyNow) {
        this.bidBuyNow = bidBuyNow;
    }

    public Set<Delivery> getBidDelivery() {
        return bidDelivery;
    }

    public void setBidDelivery(Set<Delivery> bidDelivery) {
        this.bidDelivery = bidDelivery;
    }

    public Set<Pay> getBidPay() {
        return bidPay;
    }

    public void setBidPay(Set<Pay> bidPay) {
        this.bidPay = bidPay;
    }

    public Map<User, Double> getBidAll() {
        return bidAll;
    }

    public void setBidAll(Map<User, Double> bidAll) {
        this.bidAll = bidAll;
    }
}
