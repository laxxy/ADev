package com.audev.common.Entity;

import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by cosxt on 16.12.2015.
 */
@Entity
@Table(name = "category")
public class Category {

    public interface CatView{}
    public interface CatViewWithSubCategories extends CatView{}

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "category_id")
    private long id;
    @Column(name = "name")
    private String name;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "category")
    private List<SubCategory> subCategories = new ArrayList<SubCategory>();

    public Category(){
    }

    public long getId() {
        return id;
    }

    @JsonView(CatView.class)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonView(CatViewWithSubCategories.class)
    public List<SubCategory> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<SubCategory> subCategories) {
        this.subCategories = subCategories;
    }
}
