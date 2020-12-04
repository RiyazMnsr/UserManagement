package com.example.user.service.user;

import com.example.user.constant.ApplicationConstant;
import com.example.user.constant.ErrorMessage;
import com.example.user.dto.User;
import com.example.user.dto.UserCountResponse;
import com.example.user.exception.UserNotFoundException;
import com.example.user.service.jsonprovider.UserGateway;
import com.example.user.util.UtilMethods;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    public static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Value("${update_value}")
    private String updateValue;

    @Autowired
    private UserGateway userGateway;

    /**
     *
     * @return count of unique userId
     * Create stream of list and filter the user by unique userId
     */
    @Override
    public UserCountResponse countUniqueUsers() {

        logger.info("countUniqueUsers start");
        List<User> users = userGateway.getUserList();

        List<User> distinctElements = users.stream().filter(UtilMethods.distinctByKey(User::getUserId))
                .collect(Collectors.toList());
        UserCountResponse countResponse = new UserCountResponse();
        countResponse.setCount(distinctElements.size());
        logger.debug("Response : {}", countResponse);
        return countResponse;
    }

    /**
     *
     * @return updated user list
     * Create stream of list and filter the requested user by id for update properties value
     */
    @Override
    public List<User> getUpdatedUser() {

        List<User> users = userGateway.getUserList();

        if (users.size() < ApplicationConstant.REQUEST_UPDATE_USER) {
            throw new UserNotFoundException(ErrorMessage.NO_USER_FOUND);
        }

        users.stream()
                .filter(obj -> obj.getId() == ApplicationConstant.REQUEST_UPDATE_USER)
                .findFirst()
                .ifPresent(o -> {
                    o.setBody(updateValue);
                    o.setTitle(updateValue);
                });

        return users;
    }
}
