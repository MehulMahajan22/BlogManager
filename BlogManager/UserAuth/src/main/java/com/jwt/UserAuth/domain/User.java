package com.jwt.UserAuth.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private int user_id;
    private String user_name, user_password;
    @Column(unique = true)
    private String user_email;
    @Column(unique = true)
    private long user_contact;
}
