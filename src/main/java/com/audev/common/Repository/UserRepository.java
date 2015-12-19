package com.audev.common.Repository;

import com.audev.common.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by cosxt on 03.12.2015.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findByLogin(String s);
    User findByEmail(String s);
}
