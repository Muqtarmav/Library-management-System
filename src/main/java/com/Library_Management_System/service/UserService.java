package com.Library_Management_System.service;

import com.Library_Management_System.datas.model.User;
import com.Library_Management_System.dtos.UserDto;
import com.Library_Management_System.exceptions.UserDoesNotExistException;
import com.Library_Management_System.exceptions.UserLogicException;
import com.github.fge.jsonpatch.JsonPatch;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {

    List<User> findAllUsers();
   // User createUser(User user) throws UserDoesNotExistException;
    User addUser(UserDto userDto) throws UserLogicException;
    User findUserById(Long id) throws UserDoesNotExistException;
    User updateUserRecords(Long id, JsonPatch user) throws UserLogicException;
    void delete(User user);
    void deleteById(Long id) throws UserDoesNotExistException;


}

