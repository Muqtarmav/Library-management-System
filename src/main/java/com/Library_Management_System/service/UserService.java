package com.Library_Management_System.service;

import com.Library_Management_System.datas.model.User;
import com.Library_Management_System.dtos.UserDto;
import com.github.fge.jsonpatch.JsonPatch;

import java.util.List;

public interface UserService {

    List<User> findAll();
    User addUser(UserDto userDto);
    User findById(Long id);
    User updateUserById(Long id, JsonPatch user);
    void delete(User user);


}
