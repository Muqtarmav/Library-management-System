package com.Library_Management_System.service;

import com.Library_Management_System.datas.model.User;
import com.Library_Management_System.datas.repository.UserRepository;
import com.Library_Management_System.dtos.UserDto;
import com.Library_Management_System.exceptions.UserDoesNotExistException;
import com.Library_Management_System.exceptions.UserLogicException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> findAllUsers() {

        return userRepository.findAll();
    }

    @Override
    public User addUser(UserDto userDto) throws UserLogicException {

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
        user.setAge(userDto.getAge());
        user.setUserName(userDto.getUserName());
        user.setPassword(userDto.getPassWord());

      return userRepository.save(user);
    }

    private User saveOrUpdate(User user) throws UserLogicException {
        if ( user == null){
        throw new UserLogicException("user cannot be null");
        }

        return userRepository.save(user);
    }



    @Override
    public User findUserById(Long id) throws UserDoesNotExistException {
        if (id == null){
            throw new IllegalArgumentException("argument cannot be null");
        }

        Optional<User> result = userRepository.findById(id);
        if ( result.isPresent()){
            return result.get();
        }

        throw new UserDoesNotExistException("user with id"+ id + "does not exist");
    }

    @Override
    public User updateUserRecords(Long id, JsonPatch userPatch) throws UserLogicException {

        Optional<User> userQuery = userRepository.findById(id);

        if ( userQuery.isEmpty()){
            throw new IllegalArgumentException("user with id" + id + "does not exist");
        }

        User user1 = userQuery.get();

        try{
            user1 = applyPatchToUser(userPatch, user1);
            return saveOrUpdate(user1);
        }

        catch(JsonPatchException | JsonProcessingException | UserLogicException je){
            throw new UserLogicException("update failed");
        }
    }

    private User applyPatchToUser(JsonPatch productPatch, User user1) throws JsonProcessingException, JsonPatchException{

        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode patched = productPatch.apply(objectMapper.convertValue(user1, JsonNode.class));

        return objectMapper.treeToValue(patched, User.class);
    }

    @Override
    public void delete(User user) {

        userRepository.delete(user);

    }
}
