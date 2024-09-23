package com.project208.demo.services;

import com.project208.demo.model.JournalModel;
import com.project208.demo.model.UserModel;
import com.project208.demo.repository.JournalRepository;
import com.project208.demo.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserServices {

    @Autowired
    private UserRepository userRepository;

    public void saveUserEntry(UserModel userEntry){
        userRepository.save(userEntry);
    }

    public List<UserModel> findAllUsers(){
        return userRepository.findAll();
    }

    public Optional<UserModel> findUserById(ObjectId id){
        return userRepository.findById(id);
    }

    public void deleteUserById(ObjectId id){
        userRepository.deleteById(id);
    }

    public UserModel findByUserName(String userName){
        return userRepository.findByUserName(userName);
    }


}
