package com.audev.common.Entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "category_id")
    private long id;
    @Column(name = "name")
    private String name;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "category")
    private List<SubCategory> subCategories;

    public Category(){
        subCategories = new ArrayList<>();
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

    public List<SubCategory> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<SubCategory> subCategories) {
        this.subCategories = subCategories;
    }
}
