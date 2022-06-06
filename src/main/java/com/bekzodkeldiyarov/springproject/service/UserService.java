package com.bekzodkeldiyarov.springproject.service;

import com.bekzodkeldiyarov.springproject.entity.User;
import com.bekzodkeldiyarov.springproject.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {
    private final UserSessionService activeUserService;
    private final UserRepository userRepository;

    public UserService(UserSessionService activeUserService, UserRepository userRepository) {
        this.activeUserService = activeUserService;
        this.userRepository = userRepository;
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void setLastLoginTime(String username, LocalDateTime time) {
        User user = userRepository.findByUsername(username);
        user.setLastLoginDate(time);
        userRepository.save(user);
    }

    public List<User> findByIds(Long[] ids) {
        return userRepository.findUsersByIdIn(ids);

    }

    public void saveUsers(List<User> users) {
        userRepository.saveAll(users);
    }


    public void deleteUsers(List<User> users) {
        userRepository.deleteAll(users);
    }

    public void refreshUserSession() {
        activeUserService.expireSessionForNonActiveUsers();
    }
}
