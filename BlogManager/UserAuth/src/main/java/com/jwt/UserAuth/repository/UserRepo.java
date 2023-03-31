package com.jwt.UserAuth.repository;

import com.jwt.UserAuth.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepo extends JpaRepository<User, Number> {

    @Query( value = "SELECT * FROM USER u WHERE user_email = ?1",
            nativeQuery = true)
    public User findUserByEmail(String email);

    @Query( value = "SELECT * FROM USER u WHERE user_contact = ?1",
            nativeQuery = true)
    public User findUserByContact(long contact);

}
