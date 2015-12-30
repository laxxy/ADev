package com.audev.common.Service;

import com.audev.common.Entity.SubCategory;

/**
 * Created by cosxt on 29.12.2015.
 */
public interface SubCategoryService {
public SubCategory getOne(long id);
public void saveOne(SubCategory subCategory);
}
