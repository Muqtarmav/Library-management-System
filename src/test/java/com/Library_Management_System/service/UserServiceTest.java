package com.Library_Management_System.service;

import com.Library_Management_System.datas.model.User;
import com.Library_Management_System.datas.repository.UserRepository;
import com.Library_Management_System.dtos.UserDto;
import com.Library_Management_System.exceptions.UserDoesNotExistException;
import com.Library_Management_System.exceptions.UserLogicException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

//@RunWith(SpringRunner.class)
@RunWith(SpringRunner.class)
@MockBean
@Slf4j
@Component
//@SpringBootTest(classes ={ UserService.class, UserRepository.class})
class UserServiceTest {


    @MockBean
     private UserRepository userRepository;

    @Autowired
    private UserService userService;

    private UserDto userDto;

    private User user;

    @BeforeEach
    void setUp() {

    }


    @Test
    public void createAUserTest() throws UserDoesNotExistException, UserLogicException {
        User user = new User();
        user.setId(1L);
        user.setFirstName("adetune");
        user.setLastName("tunji");
        user.setEmail("ade@gmail.com");
        user.setUserName("ade");
       user.setPassword("password");

        when(userRepository.save(user)).thenReturn(user);
       assertThat(userService.addUser(userDto)).isEqualTo(user);
    }



    @Test
    public void getUserById() throws UserDoesNotExistException {


        User user = new User();
        user.setId(12L);
        user.setUserName("muqtyjay");
        user.setLastName("tunji");
        user.setEmail("muqt@gmail.com");
        user.setAge("17");

        userRepository.save(user);
        when(userRepository.findById(2L)).thenReturn(Optional.of(user));
        assertThat(userService.findUserById(2L)).isEqualTo(user);
    }



    @Test
    public void getAllUsers() {

        log.info("getting all user :: {}", user);

        when(userRepository.findAll()).thenReturn(Stream.of(new User(100L, "muqtar", "ade@gmail.com", "eke", "23")).collect(Collectors.toList()));
        assertThat(userService.findAllUsers().size());



    }
}
