package com.blog.UserBlogs.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private int user_id;
    private String user_name, user_password;
    private String user_email;
    private long user_contact;
    private List<Blog> user_blogs;
}
