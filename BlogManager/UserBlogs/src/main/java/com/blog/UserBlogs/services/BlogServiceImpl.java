package com.blog.UserBlogs.services;

import com.blog.UserBlogs.domain.Blog;
import com.blog.UserBlogs.domain.User;
import com.blog.UserBlogs.exceptions.BlogTitleAlreadyExists;
import com.blog.UserBlogs.exceptions.NoBlogFound;
import com.blog.UserBlogs.exceptions.NoUserFound;
import com.blog.UserBlogs.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogServices{

    @Autowired
    BlogRepository br;

    @Override
    public User addUser(User user) {
        return br.save(user);
    }

    @Override
    public Blog addBlog(Blog blog, String email) throws BlogTitleAlreadyExists {
        System.out.println(email);
        User user = br.getUserByEmail(email);
        System.out.println(user.getUser_blogs());
        if(user.getUser_blogs()==null){
            List<Blog> userBlogs=new ArrayList<>();
            userBlogs.add(blog);
            user.setUser_blogs(userBlogs);
        }
        else {
            System.out.println(user.getUser_blogs());
            for (Blog b : user.getUser_blogs()) {
                if (b.getBlog_title().equalsIgnoreCase(blog.getBlog_title())) {
                    throw new BlogTitleAlreadyExists();
                }
            }
            List<Blog> existingBlogs = new ArrayList<>();
            existingBlogs = user.getUser_blogs();
            existingBlogs.add(blog);
        }
        br.delete(user);
        br.save(user);
        return blog;
    }

    @Override
    public Blog updateBlog(Blog blog, String email) throws NoBlogFound {
        User user = br.getUserByEmail(email);
        for (Blog b: user.getUser_blogs()) {
            if(b.getBlog_title().equalsIgnoreCase(blog.getBlog_title())) {
                b.setBlog_description(blog.getBlog_description());
                b.setImage_url(blog.getImage_url());
                br.save(user);
                return blog;
            }
        }
        throw new NoBlogFound();
    }

    @Override
    public void deleteBlog(Blog blog, String email) throws NoBlogFound {
        User user = br.getUserByEmail(email);
        int count=0;
        for (Blog b: user.getUser_blogs()){
            if(b.getBlog_title().equalsIgnoreCase((b.getBlog_title()))){
                count++;
            }
        }
        if (count==0)
            throw new NoBlogFound();
        List<Blog> existingBlogs = user.getUser_blogs();
        existingBlogs.remove(blog);
        br.save(user);
    }

    @Override
    public List<Blog> getUserBlogs(String email) throws NoUserFound {
        User user = br.getUserByEmail(email);
        if(user==null)
            throw new NoUserFound();
        List<Blog> blogs = new ArrayList<>();
        blogs = user.getUser_blogs();
        return blogs;
    }

    @Override
    public String updateComments(String comment,Blog blog) throws NoBlogFound, NoUserFound {
        User user = br.getUserByEmail(blog.getBlogger_email());
        if(user.getUser_name()==null)
            throw new NoUserFound();
        for (Blog b: user.getUser_blogs()){
            if(b.getBlog_title().equalsIgnoreCase(blog.getBlog_title())){
                List<String> comments;
                if(b.getBlog_comments()==null){
                    comments = new ArrayList<>();
                }
                else
                {
                    comments = b.getBlog_comments();
                }
                comments.add(comment);
                b.setBlog_comments(comments);
                br.save(user);
                return comment;
            }
        }
        throw new NoBlogFound();
    }

    @Override
    public List<Blog> getAllBlogs() {
        List<Blog> allBlogs = new ArrayList<>();
        for (User user : br.findAll()){
            if(user.getUser_blogs()!=null)
                for (Blog b : user.getUser_blogs()){
                if(b!=null)
                    allBlogs.add(b);
            }
        }
        return allBlogs;
    }

    @Override
    public Blog getBlog(String title, String email) throws NoBlogFound {
        System.out.println(email);
        System.out.println(title);
        User user = br.getUserByEmail(email);
        for (Blog b : user.getUser_blogs()){
            if(b.getBlog_title().equalsIgnoreCase(title))
                return b;
        }
        throw new NoBlogFound();
    }


}
