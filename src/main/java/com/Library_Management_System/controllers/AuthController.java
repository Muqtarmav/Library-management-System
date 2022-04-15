package com.Library_Management_System.controllers;

import com.Library_Management_System.datas.model.Role;
import com.Library_Management_System.datas.model.User;
import com.Library_Management_System.datas.repository.RoleRepository;
import com.Library_Management_System.datas.repository.UserRepository;
import com.Library_Management_System.dtos.LoginDto;
import com.Library_Management_System.dtos.SignUpDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    @PostMapping("/login")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUserNameOrEmail(),
                passwordEncoder.encode(loginDto.getPassWord())));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("User signed-in successfully!.", HttpStatus.OK);

    }


    @PostMapping("/signUp")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto){

        if ( userRepository.existsByUserName(signUpDto.getUserName())){
            return new ResponseEntity<>("user name is already taken", HttpStatus.BAD_REQUEST);
        }

        if (userRepository.existsByEmail(signUpDto.getEmail())){
            return new ResponseEntity<>("email is already taken", HttpStatus.BAD_REQUEST);
        }

        User user = new User();

       // user.setFirstName(signUpDto.getName());
        user.setUserName(signUpDto.getUserName());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassWord()));

        Role role = roleRepository.findByName("user_role").get();
        user.setRoleList(Collections.singleton(role));
        userRepository.save(user);

        return new ResponseEntity<>("user registered succesfully", HttpStatus.OK);
    }

}
