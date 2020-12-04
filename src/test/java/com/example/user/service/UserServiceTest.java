package com.example.user.service;

import com.example.user.constant.ErrorMessage;
import com.example.user.dto.User;
import com.example.user.dto.UserCountResponse;
import com.example.user.exception.UserNotFoundException;
import com.example.user.service.jsonprovider.UserGatewayImpl;
import com.example.user.service.user.UserServiceImpl;
import com.example.user.util.UserListUtil;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.BDDMockito.given;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserServiceImpl userService;

    @MockBean
    private UserGatewayImpl userGateway;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void getUpdatedUserSuccess() {

        List<User> users = UserListUtil.buildUserList();

        given(userGateway.getUserList()).willReturn(users);

        List<User> updatedUser = userService.getUpdatedUser();

        assertAll(
                () -> Assertions.assertNotNull(updatedUser),
                () -> Assertions.assertEquals(updatedUser.size(), users.size()),
                () -> Assertions.assertEquals(users.get(3).getId(), updatedUser.get(3).getId()),
                () -> Assertions.assertEquals("1800Flowers", updatedUser.get(3).getTitle()),
                () -> Assertions.assertEquals("1800Flowers", updatedUser.get(3).getBody())
        );
    }

    @Test
    public void getUpdatedUserOnlyThreeUserFailed() {

        List<User> users = UserListUtil.buildUserListWithThreeUser();
        given(userGateway.getUserList()).willReturn(users);

        thrown.expect(UserNotFoundException.class);
        thrown.expectMessage(ErrorMessage.NO_USER_FOUND);

        userService.getUpdatedUser();
    }

    @Test
    public void getCountSuccess() {

        List<User> users = UserListUtil.buildUserList();
        given(userGateway.getUserList()).willReturn(users);
        UserCountResponse response = userService.countUniqueUsers();
        assertAll(
                () -> Assertions.assertNotNull(response),
                () -> Assertions.assertEquals(response.getCount(), 2)
        );
    }
}
