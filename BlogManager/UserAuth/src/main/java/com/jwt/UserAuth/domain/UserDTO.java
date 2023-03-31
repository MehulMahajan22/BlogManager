package com.jwt.UserAuth.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private int user_id;
    private String user_name,  user_email;
    private long user_contact;
}
