package com.audev.common.Service;

import com.audev.common.Entity.Category;
import com.audev.common.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by cosxt on 20.12.2015.
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public void addOne(Category category) {
        categoryRepository.save(category);
    }
}
