//package com.Library_Management_System.service;
//
//import com.Library_Management_System.datas.model.User;
//import com.Library_Management_System.datas.repository.UserRepository;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.validateMockitoUsage;
//import static org.mockito.Mockito.when;
//
//
//@SpringBootTest
//class UserServiceImplTest {
//
//    @Autowired
//    UserService userService;
//
//    @MockBean
//        UserRepository userRepository;
//
//
//    @Test
////    void findAllUsers() {
////        when(userRepository.findAll()).thenReturn(Stream.of(new User("hwje", "mdm", "elel", "emle", "elle")).collect(Collectors.toList()));
////        assertEquals(1, userService.findAllUsers().size());
////}
//
//    @Test
//    void addUser() {
//    }
//
//    @Test
//    void findUserById() {
//    }
//
//    @Test
//    void updateUserRecords() {
//    }
//
//    @Test
//    void delete() {
//    }
//}