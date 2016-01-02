package com.audev.common.Entity;

import com.audev.common.Entity.Enums.Delivery;
import com.audev.common.Entity.Enums.Pay;
import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.annotations.GenericGenerator;

import java.util.*;

import javax.persistence.*;

/**
 * Created by cosxt on 28.11.2015.
 */
@Entity
@Table(name = "Lot")
public class Lot {

    public interface Public{}

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name= "increment", strategy= "increment")
    @Column(name = "id")
    private long id;
    @Column(name = "lot_name")
    private String lotName;
    @Column(name = "lot_info")
    private String lotInfo;
    @Column(name = "date_of_start")
    private Date dateOfStart;
    @Column(name = "date_of_end")
    private Date dateOfEnd;
    //@Column(name = "bid_initial")
    //private double bidInitial;
    @Column(name = "bid_current")
    private double bidCurrent;
    //@Column(name = "bid_buy_now")
    //private double bidBuyNow;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_id", nullable = false)
    private SubCategory subCategory;
    private transient Set<Delivery> bidDelivery;
    private transient Set<Pay> bidPay;
    private transient Map<User, Double> bidAll;

    public Lot() {
        bidDelivery = new HashSet<Delivery>();
        bidPay = new HashSet<Pay>();
        bidAll = new TreeMap<User, Double>();
    }

    public Lot(SubCategory subCategory) {
        this.subCategory = subCategory;
    }

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

    @JsonView(Public.class)
    public Date getDateOfEnd() {
        return dateOfEnd;
    }

    public void setDateOfEnd(Date dateOfEnd) {
        this.dateOfEnd = dateOfEnd;
    }

    /*@JsonView(Public.class)
    public double getBidInitial() {
        return bidInitial;
    }*/

    /*public void setBidInitial(double bidInitial) {
        this.bidInitial = bidInitial;
    }*/

    @JsonView(Public.class)
    public double getBidCurrent() {
        return bidCurrent;
    }

    public void setBidCurrent(double bidCurrent) {
        this.bidCurrent = bidCurrent;
    }

    /*@JsonView(Public.class)
    public double getBidBuyNow() {
        return bidBuyNow;
    }*/

    /*public void setBidBuyNow(double bidBuyNow) {
        this.bidBuyNow = bidBuyNow;
    }*/

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

    public SubCategory getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(SubCategory subCategory) {
        this.subCategory = subCategory;
    }

    /*@Override
    public String toString() {
        return "Lot[name:" + lotName + ", info:" + lotInfo + ", dastart:" + dateOfStart +
                ", daend:" + dateOfEnd + ", bidinit:" + bidInitial + ", bidcurrent:" +
                bidCurrent + ", bidbuynow" + bidBuyNow + "]";
    }*/
}
