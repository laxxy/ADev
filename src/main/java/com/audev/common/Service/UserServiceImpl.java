package com.audev.common.Service;

import com.audev.common.Entity.User;
import com.audev.common.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * UserService impl
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

    //no cache here cz -> throw LazyInitializationException
    //use @Transactional to method
    public User getUserByEmail(String s) {
        return userRepository.findByEmail(s);
    }
}
