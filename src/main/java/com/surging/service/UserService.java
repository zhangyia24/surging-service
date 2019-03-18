package com.surging.service;

import com.surging.entity.User1;
import com.surging.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Song on 2017/2/15.
 * User业务逻辑
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void dropTable(){
        userRepository.dropTable();
    }
    public void createTable(){
        userRepository.createTable();
    }
    public void insert(User1 user1){
        userRepository.insert(user1);
    }
    public List<User1> findAll(){
        return userRepository.findAll();
    }
    public List<User1> findByNameLike(String name){
        return userRepository.findByNameLike(name);
    }
}
