package com.jwt.UserAuth.services;

import com.jwt.UserAuth.domain.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class TokenGenerationServiceImpl implements TokenGenerationService{
    @Override
    public Map<String, String> generateToken(User user) {

            Map<String, String> result = new HashMap<>();
            Map<String, Object> userData = new HashMap<>();
            user.setUser_password("");
            userData.put("Email",user.getUser_email());
            userData.put("Name",user.getUser_name());
            String token = Jwts.builder()
                    .setIssuedAt(new Date())
                    .setClaims(userData)
                    .signWith(SignatureAlgorithm.HS512,"SecretToken")
                    .compact();
            result.put("Token",token);
            result.put("Message","Logged In");
            result.put("Email",user.getUser_email());
            return result;
        }
    }

