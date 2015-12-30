package com.audev.common.Service;

import com.audev.common.Entity.SubCategory;
import com.audev.common.Repository.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by cosxt on 29.12.2015.
 */
@Service
public class SubCategoryServiceImpl implements SubCategoryService {

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    public SubCategory getOne(long id) {
        return subCategoryRepository.getOne(id);
    }

    public void saveOne(SubCategory subCategory) {
        subCategoryRepository.save(subCategory);
    }
}
