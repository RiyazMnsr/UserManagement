package com.example.user.service.user;

import com.example.user.dto.User;
import com.example.user.dto.UserCountResponse;

import java.util.List;
import java.util.Map;

public interface UserService {

    UserCountResponse countUniqueUsers();

    List<User> getUpdatedUser();
}
