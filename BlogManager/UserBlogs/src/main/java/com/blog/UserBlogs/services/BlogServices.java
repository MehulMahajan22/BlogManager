package com.blog.UserBlogs.services;

import com.blog.UserBlogs.domain.Blog;
import com.blog.UserBlogs.domain.User;
import com.blog.UserBlogs.exceptions.BlogTitleAlreadyExists;
import com.blog.UserBlogs.exceptions.NoBlogFound;
import com.blog.UserBlogs.exceptions.NoUserFound;

import java.util.List;

public interface BlogServices {
    public User addUser(User user);
    public Blog addBlog(Blog blog, String email) throws BlogTitleAlreadyExists;
    public Blog updateBlog(Blog blog, String email) throws NoBlogFound;
    public void deleteBlog(Blog blog, String email) throws NoBlogFound;
    public List<Blog> getUserBlogs(String email) throws NoUserFound;
    public String updateComments(String comment, Blog blog) throws NoBlogFound, NoUserFound;
    public List<Blog> getAllBlogs();
    public Blog getBlog(String title, String email) throws NoBlogFound;
}
