package com.jwt.UserAuth.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class SignUpData {
    private String user_name, user_password, user_email;
    private long user_contact;
}
