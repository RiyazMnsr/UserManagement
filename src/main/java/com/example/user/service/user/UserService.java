package com.example.user.service.user;

import com.example.user.dto.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    Map<String, Integer> countUniqueUsers();

    List<User> getUpdatedUser();
}
