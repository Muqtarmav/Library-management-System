package com.Library_Management_System.service;

import com.Library_Management_System.datas.model.User;
import com.Library_Management_System.datas.repository.UserRepository;
import com.Library_Management_System.dtos.UserDto;
import com.Library_Management_System.exceptions.UserDoesNotExistException;
import com.Library_Management_System.exceptions.UserLogicException;
import com.github.fge.jsonpatch.JsonPatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> findAll() {

        return userRepository.findAll();
    }

    @Override
    public User addUser(UserDto userDto) {

        if (userDto == null) {
            throw new IllegalArgumentException("argument cannot be null");
        }

        Optional<User> query = userRepository.findByEmail(userDto.getEmail_address());

        if (query.isPresent()) {
            return query.get();
        }

        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail_address());
        user.setMobile(userDto.getMobile());

      return userRepository.save(user);
    }

    private User saveOrUpdate(User user) throws UserLogicException {
        if ( user == null){
        throw new UserLogicException("user cannot be null");
        }

        return userRepository.save(user);
    }



    @Override
    public User findById(Long id) {
        if (id == null){
            throw new IllegalArgumentException("argument cannot be null");
        }

        Optional<User> result = userRepository.findById(id);
        if ( result.isPresent()){
            return result.get();
        }

        throw new IllegalArgumentException("user with id"+ id + "does not exist");
    }

    @Override
    public User updateUserById(Long id, JsonPatch user) {
        return null;
    }

    @Override
    public void delete(User user) {

        userRepository.delete(user);

    }
}
