package com.jwt.UserAuth.proxy;
import com.jwt.UserAuth.domain.User;

import com.jwt.UserAuth.domain.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "blog-app",url = "localhost:4500")
public interface UserProxy {
    @PostMapping("/blog/addUser")
    public ResponseEntity<?> sendDataToBlogApp(@RequestBody UserDTO userDTO);
}