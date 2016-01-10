package com.audev.common.Service;

import com.audev.common.Entity.SubCategory;

import java.util.List;

/**
 * Created by cosxt on 29.12.2015.
 */
public interface SubCategoryService {
    SubCategory getOne(long id);
    SubCategory getOneByName(String name);
    void saveOne(SubCategory subCategory);
    List<SubCategory> getAll();

}
