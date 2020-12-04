package com.example.user.controller;

import com.example.user.dto.User;
import com.example.user.dto.UserCountResponse;
import com.example.user.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Validated
@RestController
public class UserController {

    public static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    /**
     *
     * @return count of unique userId
     */
    @GetMapping
    @RequestMapping("/users/count")
    public ResponseEntity countUniqueUser() {
        UserCountResponse response = userService.countUniqueUsers();
        return new ResponseEntity(response, HttpStatus.OK);

    }

    /**
     *
     * @return updated user list
     */
    @GetMapping
    @RequestMapping("/users")
    public ResponseEntity getUpdatedUser() {
        List<User> user = userService.getUpdatedUser();
        return new ResponseEntity(user, HttpStatus.OK);

    }
}
