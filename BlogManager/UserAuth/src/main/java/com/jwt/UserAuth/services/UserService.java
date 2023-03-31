package com.jwt.UserAuth.services;

import com.jwt.UserAuth.domain.SignUpData;
import com.jwt.UserAuth.domain.User;
import com.jwt.UserAuth.expceptions.ContactAlreadyRegistered;
import com.jwt.UserAuth.expceptions.EmailAlreadyRegistered;
import com.jwt.UserAuth.expceptions.InvalidCredentials;

import java.util.Map;

public interface UserService {
    public User addUser(SignUpData signUpData) throws ContactAlreadyRegistered, EmailAlreadyRegistered;
    public Map<String, String> login(String email, String password) throws InvalidCredentials;
}
