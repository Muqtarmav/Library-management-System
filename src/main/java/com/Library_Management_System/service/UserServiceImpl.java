package com.Library_Management_System.service;

import com.Library_Management_System.datas.model.User;
import com.Library_Management_System.dtos.UserDto;
import com.github.fge.jsonpatch.JsonPatch;

import java.util.List;

public class UserServiceImpl implements UserService{

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User addUser(UserDto userDto) {
        return null;
    }

    @Override
    public User findById(Long id) {
        return null;
    }

    @Override
    public User updateUserById(Long id, JsonPatch user) {
        return null;
    }

    @Override
    public void delete(User user) {

    }
}
