package com.blog.UserBlogs.repository;

import com.blog.UserBlogs.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface BlogRepository extends MongoRepository<User,Number> {
    @Query("{user_email : ?0}")
    public User getUserByEmail(String email);
}
