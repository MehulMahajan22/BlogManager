package com.jwt.UserAuth.expceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Email Already Registered")
public class EmailAlreadyRegistered extends Exception{
}
