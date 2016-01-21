package com.audev.common.Repository;

import com.audev.common.Entity.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by cosxt on 29.12.2015.
 */
public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {
    SubCategory findByName(String s);
    SubCategory findById(long id);
}
