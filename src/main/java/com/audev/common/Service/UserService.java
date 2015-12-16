package com.audev.common.Service;

import com.audev.common.Entity.User;

import java.util.List;

/**
 * Created by cosxt on 03.12.2015.
 */
public interface UserService {
    public User getUserById(long id);
    public User getUserByName(String s);
    public List<User> getAllUsers();
    public void deleteUserById(long id);
    public void updateUser(User user);
    public void addUser();
}
