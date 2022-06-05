package com.bekzodkeldiyarov.springproject.repository;

import com.bekzodkeldiyarov.springproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    List<User> findAll();


    List<User> findUsersByIdIn(Long[] ids);

    User save(User user);



}
