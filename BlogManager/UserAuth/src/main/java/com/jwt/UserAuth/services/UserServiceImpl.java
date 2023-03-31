package com.jwt.UserAuth.services;

import com.jwt.UserAuth.domain.SignUpData;
import com.jwt.UserAuth.domain.User;
import com.jwt.UserAuth.domain.UserDTO;
import com.jwt.UserAuth.expceptions.ContactAlreadyRegistered;
import com.jwt.UserAuth.expceptions.EmailAlreadyRegistered;
import com.jwt.UserAuth.expceptions.InvalidCredentials;
import com.jwt.UserAuth.proxy.UserProxy;
import com.jwt.UserAuth.rabbitMq.EmailDTO;
import com.jwt.UserAuth.rabbitMq.MailProducer;
import com.jwt.UserAuth.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepo ur;
    @Autowired
    UserProxy userProxy;
    @Autowired
    MailProducer mailProducer;

    @Autowired
    TokenGenerationServiceImpl tgs;

    @Override
    public User addUser(SignUpData signUpData) throws ContactAlreadyRegistered, EmailAlreadyRegistered {
        System.out.println("email "+signUpData.getUser_email());
        System.out.println("contact "+signUpData.getUser_contact());
        if(ur.findUserByEmail(signUpData.getUser_email())!=null) {
            throw new EmailAlreadyRegistered();
        }
        else if (ur.findUserByContact(signUpData.getUser_contact())!=null) {
            throw new ContactAlreadyRegistered();
        }
        else {
            mailProducer.sendMailToQueue(new EmailDTO(signUpData.getUser_email(), "Welcome To ABC Blogs", "Registration Successful"));
            User u = new User();
            u.setUser_password(signUpData.getUser_password());
            u.setUser_email(signUpData.getUser_email());
            u.setUser_contact(signUpData.getUser_contact());
            u.setUser_name(signUpData.getUser_name());
            User u1 = ur.save(u);
            UserDTO userDTO=new UserDTO(u1.getUser_id(), u1.getUser_name(),u1.getUser_email(),u1.getUser_contact());
            userProxy.sendDataToBlogApp(userDTO);
            return u;
        }
    }

    @Override
    public Map<String, String> login(String email, String password) throws InvalidCredentials {
        User user1 = new User();
        if ((user1 = ur.findUserByEmail(email)) != null) {
            if (user1.getUser_password().equals(password)) {
                return tgs.generateToken(user1);
            }
        }
        throw new InvalidCredentials();
    }
}
