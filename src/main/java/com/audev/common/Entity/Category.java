package com.audev.common.Entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;

/**
 * Created by cosxt on 16.12.2015.
 */
@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "cat_id")
    private long id;
    @OneToOne(optional = false)
    private Lot lot;
    @Column(name = "name")
    private String name;
    //@OneToMany(cascade = {CascadeType.ALL})
    //@JoinColumn(name = "cat_id")
    private transient ArrayList<Category> subCategory;

    public Category() {
        subCategory = new ArrayList<Category>();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Category> getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(ArrayList<Category> subCategory) {
        this.subCategory = subCategory;
    }

    public Lot getLot() {
        return lot;
    }

    public void setLot(Lot lot) {
        this.lot = lot;
    }
}
