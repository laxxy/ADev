package com.audev.common.Service;

import com.audev.common.Entity.Category;

import java.util.List;

/**
 * Created by cosxt on 20.12.2015.
 */
public interface CategoryService {
    public void addOne(Category category);
    public List<Category> getAll();
    public Category getOneById(long id);
}
