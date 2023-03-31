package com.jwt.UserAuth.controller;

import com.jwt.UserAuth.domain.SignUpData;
import com.jwt.UserAuth.domain.User;
import com.jwt.UserAuth.expceptions.ContactAlreadyRegistered;
import com.jwt.UserAuth.expceptions.EmailAlreadyRegistered;
import com.jwt.UserAuth.expceptions.InvalidCredentials;
import com.jwt.UserAuth.repository.UserRepo;
import com.jwt.UserAuth.services.TokenGenerationServiceImpl;
import com.jwt.UserAuth.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserAuthController {

    @Autowired
    TokenGenerationServiceImpl tgs;

    @Autowired
    UserServiceImpl usi;

    @Autowired
    UserRepo ur;

    @PostMapping("/registerUser")
    public ResponseEntity<?> addUser(@RequestBody SignUpData signUpData) throws EmailAlreadyRegistered, ContactAlreadyRegistered {
        System.out.println(signUpData.getUser_email());
        return new ResponseEntity<>(usi.addUser(signUpData), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) throws InvalidCredentials {

            return new ResponseEntity<>(usi.login(user.getUser_email(),user.getUser_password()),HttpStatus.OK);

    }
}
