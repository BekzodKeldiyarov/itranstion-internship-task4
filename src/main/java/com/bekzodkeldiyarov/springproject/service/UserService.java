package com.bekzodkeldiyarov.springproject.service;

import com.bekzodkeldiyarov.springproject.entity.User;
import com.bekzodkeldiyarov.springproject.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void saveUser(User user) {
        System.out.println("Saved User: " + userRepository.save(user));
    }

    public void setLastLoginTime(String username, LocalDateTime time) {
        User user = userRepository.findByUsername(username);
        user.setLastLoginDate(time);
        userRepository.save(user);
    }

    public List<User> findByIds(Long[] ids) {
        List<User> users = userRepository.findUsersByIdIn(ids);
        return users;
    }

    public void saveUsers(List<User> users) {
        System.out.println(users);
        List<User> usersSaved = userRepository.saveAll(users);

    }

    public void deleteUsers(List<User> users) {
        userRepository.deleteAll(users);
    }
}
