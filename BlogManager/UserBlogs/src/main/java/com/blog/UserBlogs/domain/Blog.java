package com.blog.UserBlogs.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Blog {
    private String blog_title, blog_description, image_url, blogger_email;
    private List<String> blog_comments;
}
