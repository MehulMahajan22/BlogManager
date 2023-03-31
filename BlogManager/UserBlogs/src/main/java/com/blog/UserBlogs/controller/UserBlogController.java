package com.blog.UserBlogs.controller;

import com.blog.UserBlogs.domain.Blog;
import com.blog.UserBlogs.domain.User;
import com.blog.UserBlogs.exceptions.BlogTitleAlreadyExists;
import com.blog.UserBlogs.exceptions.NoBlogFound;
import com.blog.UserBlogs.exceptions.NoUserFound;
import com.blog.UserBlogs.services.BlogServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/blog")
public class UserBlogController {
    @Autowired
    BlogServiceImpl bsi;

    @PostMapping("/addUser")
    public ResponseEntity<?> addUser(@RequestBody User user){
        return new ResponseEntity<>(bsi.addUser(user), HttpStatus.CREATED);
    }

    @RequestMapping("/addBlog")
    public ResponseEntity<?> addBlog(@RequestBody Blog blog, HttpServletRequest httpServletRequest) throws BlogTitleAlreadyExists {
        String email = (String) httpServletRequest.getAttribute("Email");
        return new ResponseEntity<>(bsi.addBlog(blog, email),HttpStatus.CREATED);
    }

    @PutMapping("/updateBlog")
    public ResponseEntity<?> updateBlog(@RequestBody Blog blog, HttpServletRequest httpServletRequest) throws NoBlogFound {
        String email = (String) httpServletRequest.getAttribute("Email");
        return new ResponseEntity<>(bsi.updateBlog(blog,email),HttpStatus.OK);
    }

    @DeleteMapping("/deleteBlog")
    public ResponseEntity<?> deleteBlog(@RequestBody Blog blog, HttpServletRequest httpServletRequest) throws NoBlogFound {
        String email = (String) httpServletRequest.getAttribute("Email");
        bsi.deleteBlog(blog,email);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/myBlogs")
    public ResponseEntity<?> getUserBlogs(HttpServletRequest httpServletRequest) throws NoUserFound {
        String email = (String) httpServletRequest.getAttribute("Email");
        return new ResponseEntity<>(bsi.getUserBlogs(email),HttpStatus.OK);
    }

    @PostMapping("/comments/{comment}")
    public ResponseEntity<?> updateComments(@PathVariable String comment, @RequestBody Blog blog) throws NoBlogFound, NoUserFound {
        return new ResponseEntity<>(bsi.updateComments(comment,blog),HttpStatus.CREATED);
    }

    @GetMapping("/getAllBlogs")
    public ResponseEntity<?> getAllBlogs(){
        return new ResponseEntity<>(bsi.getAllBlogs(),HttpStatus.OK);
    }

    @GetMapping("/getBlog/{title}")
    public ResponseEntity<?> getBlog(@PathVariable String title, HttpServletRequest httpServletRequest) throws NoBlogFound {
        String email = (String) httpServletRequest.getAttribute("Email");
        return new ResponseEntity<>(bsi.getBlog(title,email),HttpStatus.OK);
    }

    @PostMapping("/getBlogComment/{title}")
    public ResponseEntity<?> getBlogForComments(@RequestBody String email, @PathVariable String title) throws NoBlogFound {
        return new ResponseEntity<>(bsi.getBlog(title,email),HttpStatus.OK);
    }


}
