package com.example.dao;

import com.example.pojo.User;

import java.util.List;

public interface IUserDao {

    List<User> findAll();

    User findByCondition(User user);
}
