package com.Library_Management_System.datas.repository;

import com.Library_Management_System.datas.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@Slf4j
@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;


    @BeforeEach
    void setUp() {
    }


    @Test
    @DisplayName("user can be saved in database")

    void testThatUserCanBeSavedInDatabase(){
        User user = new User();

        user.setFirstName("ade");
        user.setLastName("tolani");
        user.setEmail("ade@gmail.com");
        user.setMobile("081973823");
        user.setAge("20");

        log.info("user can be saved::{}", user);
        userRepository.save(user);
        assertThat(user.getId()).isNull();
        assertThat(user.getFirstName()).isEqualTo("ade");
        assertThat(user.getLastName()).isEqualTo("tolani");
        assertThat(user.getId()).isNotNull();
        assertThat(user.getEmail()).isEqualTo("ade@gmail.com");

    }


    @Test
    @DisplayName("all users can be find")

    void allUserCanBeFindInDatabaseTest(){

        List<User> user = userRepository.findAll();
        assertThat(user).isNotNull();
        assertThat(user.size()).isEqualTo(1);
    }





    @Test
    @DisplayName("users can be find by Id")

    void userCanBeFindByIdTest(){

        User user = userRepository.findById(1L).orElse(null);
        assertThat(user).isNotNull();
        assertThat(user.getFirstName());
        assertThat(user.getLastName());
        assertThat(user.getEmail());
        assertThat(user.getMobile());

        log.info("user id retrieved::{}", user);

    }

//
//    @Test
//    @DisplayName("update user records")
//    void userRecordsCanBeUpdatedTest(){
//        User user = new User();
//        assertThat(user).isNotNull();
//        assertThat(user.getFirstName()).isEqualTo();
//        assertThat(user.getLastName()).isEqualTo();
//        assertThat(user.getEmail()).isEqualTo();
//
//        user.setEmail("");
//
//        log.info("user record has been updated");
//        userRepository.save(user);
//
//        assertThat(user.getFirstName()).isEqualTo();
//        assertThat(user.getEmail()).isEqualTo();
//    }

    @Test
    @DisplayName("user can be delete")

    void userCanBeDeleteInDatabaseTest(){

        User user = new User();
        userRepository.delete(user);

    }

}