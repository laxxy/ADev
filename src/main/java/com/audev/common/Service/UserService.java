package com.audev.common.Service;

import com.audev.common.Entity.User;

import java.util.List;

/**
 * UserService
 */
public interface UserService {
    public User getUserById(long id);
    public User getUserByEmail(String s);
    public List<User> getAllUsers();
    public void deleteUserById(long id);
    public void addUser(User user);
}
