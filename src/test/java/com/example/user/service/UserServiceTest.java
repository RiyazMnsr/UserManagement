package com.example.user.service;

import com.example.user.constant.ErrorMessage;
import com.example.user.dto.User;
import com.example.user.exception.UserNotFoundException;
import com.example.user.service.jsonprovider.UserGatewayImpl;
import com.example.user.service.user.UserServiceImpl;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
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
    public void getUpdatedUser_success() {

        User firstUser = new User(1, 1, "Test First User", "Test First User");
        User secondUser = new User(1, 2, "Test Second User", "Test Second User");
        User thirdUser = new User(1, 3, "Test Third User", "Test Third User");
        User fourthUser = new User(2, 4, "Test Fourth User", "Test Fourth User");
        User fifthUser = new User(2, 5, "Test Fifth User", "Test Fifth User");
        User sixthUser = new User(2, 6, "Test Six User", "Test Sixth User");

        List<User> users = Arrays.asList(firstUser, secondUser, thirdUser, fourthUser, fifthUser, sixthUser);

        given(userGateway.getUserList()).willReturn(users);

        List<User> updatedUser = userService.getUpdatedUser();

        assertAll(
                () -> Assertions.assertNotNull(updatedUser),
                () -> Assertions.assertTrue(updatedUser.size() == 6),
                () -> Assertions.assertEquals(fourthUser.getId(), updatedUser.get(3).getId()),
                () -> Assertions.assertEquals("1800Flowers",updatedUser.get(3).getTitle()),
                () -> Assertions.assertEquals("1800Flowers", updatedUser.get(3).getBody())
        );
    }

    @Test
    public void getUpdatedUser_only_three_user_failed() {

        User firstUser = new User(1, 1, "Test First User", "Test First User");
        User secondUser = new User(1, 2, "Test Second User", "Test Second User");
        User thirdUser = new User(1, 3, "Test Third User", "Test Third User");

        List<User> users = Arrays.asList(firstUser, secondUser, thirdUser);
        given(userGateway.getUserList()).willReturn(users);

        thrown.expect(UserNotFoundException.class);
        thrown.expectMessage(ErrorMessage.NO_USER_FOUND);

        userService.getUpdatedUser();

    }

    @Test
    public void getCount_success() {

        User firstUser = new User(1, 1, "Test First User", "Test First User");
        User secondUser = new User(1, 2, "Test Second User", "Test Second User");
        User thirdUser = new User(1, 3, "Test Third User", "Test Third User");
        User fourthUser = new User(2, 4, "Test Fourth User", "Test Fourth User");
        User fifthUser = new User(2, 5, "Test Fifth User", "Test Fifth User");
        User sixthUser = new User(2, 6, "Test Six User", "Test Sixth User");

        List<User> users = Arrays.asList(firstUser, secondUser, thirdUser, fourthUser, fifthUser, sixthUser);
        given(userGateway.getUserList()).willReturn(users);
        Map<String, Integer> uniqueNo = userService.countUniqueUsers();
        assertAll(
                () -> Assertions.assertNotNull(uniqueNo),
                () -> Assertions.assertEquals(uniqueNo.get("count"), 2)
        );
    }
}
