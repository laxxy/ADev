package com.audev.common.Repository;

import com.audev.common.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by cosxt on 20.12.2015.
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
