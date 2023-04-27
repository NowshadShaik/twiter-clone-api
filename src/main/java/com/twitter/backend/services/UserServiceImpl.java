package com.twitter.backend.services;

import com.twitter.backend.modals.User;
import com.twitter.backend.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

//    @Autowired
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl (UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) throws Exception {
        logger.info("===Inside CreatUser===");
        if(userRepository.findByUsername(user.getUsername()) != null)
            throw new Exception("Username already exists");
        logger.info("Registration successfully...");
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getUserByUsername(String username) throws Exception {
        return userRepository.findByUsernameContaining(username);
    }
}
