package com.audev.common.Service;

import com.audev.common.Entity.Enums.UserRole;
import com.audev.common.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by cosxt on 16.12.2015.
 */
@Service
public class UserSecurImpl implements UserDetailsService {

    @Autowired UserService userService;

    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        User user = userService.getUserByEmail(s);
        Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
        roles.add(new SimpleGrantedAuthority(UserRole.USER.name()));

        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(), roles);
    }
}
