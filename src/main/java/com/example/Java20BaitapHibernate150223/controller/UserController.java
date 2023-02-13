package com.example.Java20BaitapHibernate150223.controller;

import com.example.Java20BaitapHibernate150223.dto.UserDTO;
import com.example.Java20BaitapHibernate150223.service.imp.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("")
    public ResponseEntity<?> getUserByEmailNPAss(@RequestParam String email, @RequestParam String password){

        return new ResponseEntity<>(userService.getUserByEmailAndPassword(email,password), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> saveUser(@RequestBody UserDTO userDto){

        return new ResponseEntity<>(userService.saveUser(userDto),HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<?> getAllUser(){

        return new ResponseEntity<>(userService.getAllUser(),HttpStatus.OK);
    }

}
