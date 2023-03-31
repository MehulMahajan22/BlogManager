package com.jwt.UserAuth.services;

import com.jwt.UserAuth.domain.User;

import java.util.Map;

public interface TokenGenerationService {
    public Map<String,String> generateToken(User user);
}
