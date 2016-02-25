package com.audev.common.Service;

import com.audev.common.Entity.User;
import com.audev.common.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by cosxt on 03.12.2015.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Cacheable(value = "User")
    public User getUserById(long id) {
        return userRepository.findOne(id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUserById(long id) {
        userRepository.delete(id);
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    @Cacheable(value = "User")
    public User getUserByName(String s) {
        return userRepository.findByLogin(s);
    }

    @Cacheable(value = "User")
    public User getUserByEmail(String s) {
        return userRepository.findByEmail(s);
    }
}
