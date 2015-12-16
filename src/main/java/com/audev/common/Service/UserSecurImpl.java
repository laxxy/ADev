package com.audev.common.Service;

import com.audev.common.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by cosxt on 16.12.2015.
 */
@Service
public class UserSecurImpl implements UserDetailsService {

    @Autowired UserService userService;

    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        //User user = userService.

        return null;
    }
}
