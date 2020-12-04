package com.example.user.util;

import com.example.user.dto.User;

import java.util.Arrays;
import java.util.List;

public class UserListUtil {

    public static List<User> buildUserList() {
        User firstUser = new User(1, 1, "Test First User", "Test First User");
        User secondUser = new User(1, 2, "Test Second User", "Test Second User");
        User thirdUser = new User(1, 3, "Test Third User", "Test Third User");
        User fourthUser = new User(2, 4, "Test Fourth User", "Test Fourth User");
        User fifthUser = new User(2, 5, "Test Fifth User", "Test Fifth User");
        User sixthUser = new User(2, 6, "Test Six User", "Test Sixth User");

        List<User> users = Arrays.asList(firstUser, secondUser, thirdUser, fourthUser, fifthUser, sixthUser);

        return users;
    }

    public static List<User> buildUserListWithThreeUser() {
        User firstUser = new User(1, 1, "Test First User", "Test First User");
        User secondUser = new User(1, 2, "Test Second User", "Test Second User");
        User thirdUser = new User(1, 3, "Test Third User", "Test Third User");

        List<User> users = Arrays.asList(firstUser, secondUser, thirdUser);

        return users;
    }
}
