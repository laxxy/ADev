package com.audev.common.Entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Set;

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
    @Column(name = "name")
    private String name;
    private ArrayList<SubCategory> subCategories = new ArrayList<SubCategory>();

    public Category(){
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

    public ArrayList<SubCategory> getSubCategories() {
        return subCategories;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    public void setSubCategories(ArrayList<SubCategory> subCategories) {
        this.subCategories = subCategories;
    }
}
