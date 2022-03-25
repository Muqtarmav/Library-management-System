package com.Library_Management_System.controllers;

import com.Library_Management_System.datas.model.User;
import com.Library_Management_System.dtos.UserDto;
import com.Library_Management_System.exceptions.UserDoesNotExistException;
import com.Library_Management_System.exceptions.UserLogicException;
import com.Library_Management_System.service.UserService;
import com.github.fge.jsonpatch.JsonPatch;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/api/user")
@RestController
public class UserController {

    @Autowired
    UserService userService;


    @GetMapping()
    public ResponseEntity<?> findAll(){
        List<User> userList = userService.findAllUsers();
        return ResponseEntity.ok().body(userList);

    }


    @GetMapping("get/user/id")
        public ResponseEntity<?>findById(Long id){

        try{
            User savedUser = userService.findUserById(id);
            return ResponseEntity.ok().body(savedUser);
        }

        catch(UserDoesNotExistException message){
            return ResponseEntity.badRequest().body(message);
        }
    }


    @PostMapping("add/")
    public ResponseEntity<?> addUser(@RequestBody UserDto userDto){

        log.info("user added::{}", userDto);

        try{
            User user = userService.addUser(userDto);
            return ResponseEntity.ok().body(user);
        }

        catch (UserLogicException message){
            return ResponseEntity.badRequest().body(message);
        }
    }


    @PatchMapping(path = "{id}", consumes = "application/json-patch+json")
    public ResponseEntity<?> updateBookRecords(@PathVariable Long id, @RequestBody JsonPatch userPatch){

        try {
            User updatedEmployee = userService.updateUserRecords(id, userPatch);
            log.info("updated employee {}", updatedEmployee);
            return ResponseEntity.status(HttpStatus.OK).body(updatedEmployee);
        }

        catch (UserLogicException e){
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
        return  null;
    }

}
