package com.project208.demo.controller;

import com.project208.demo.model.JournalModel;
import com.project208.demo.model.UserModel;
import com.project208.demo.services.JournalServices;
import com.project208.demo.services.UserServices;
import org.apache.catalina.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServices userServices;

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        List<UserModel> allUsers = userServices.findAllUsers();
        if (allUsers != null && !allUsers.isEmpty()){
            return new ResponseEntity<>(allUsers, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getUserById(@PathVariable ObjectId id) {
        UserModel userById = userServices.findUserById(id).orElse(null);
        if (userById != null) {
            return new ResponseEntity<>(userById, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserModel userEntry){
        try {
            userServices.saveUserEntry(userEntry);
            return new ResponseEntity<>(userEntry, HttpStatus.OK);
        }catch (Exception e){
            System.out.println("####this is error#### "+ e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{userName}")
    public ResponseEntity<?> updateUser(@RequestBody UserModel user, @PathVariable String userName){
        UserModel oldUser = userServices.findByUserName(userName);
        if (oldUser != null){
            oldUser.setUserName(user.getUserName());
            oldUser.setPassword(user.getPassword());
            userServices.saveUserEntry(oldUser);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}