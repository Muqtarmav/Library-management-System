package com.Library_Management_System.service;

import com.Library_Management_System.datas.model.User;
import com.Library_Management_System.dtos.UserDto;
import com.Library_Management_System.exceptions.UserDoesNotExistException;
import com.Library_Management_System.exceptions.UserLogicException;
import com.github.fge.jsonpatch.JsonPatch;

import java.util.List;

public interface UserService {

    List<User> findAllUsers();
    User addUser(UserDto userDto) throws UserLogicException;
    User findUserById(Long id) throws UserDoesNotExistException;
    User updateUserRecords(Long id, JsonPatch user) throws UserLogicException;
    void delete(User user);


}

