package com.example.user.service;

import com.example.user.constant.ErrorMessage;
import com.example.user.dto.User;
import com.example.user.service.jsonprovider.UserGatewayImpl;
import com.example.user.util.UserListUtil;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserGatewayTest {

    @Autowired
    private UserGatewayImpl userGateway;

    @MockBean
    private RestTemplate apiGatewayRestTemplate;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void getUserListFromGatewaySuccess() {

        List<User> users = UserListUtil.buildUserList();

        ResponseEntity<List<User>> responseEntity = new ResponseEntity<List<User>>(users, HttpStatus.OK);

        Mockito.when(apiGatewayRestTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<ParameterizedTypeReference<List<User>>>any())
        ).thenReturn(responseEntity);

        List<User> updatedUser = userGateway.getUserList();
        assertAll(
                () -> Assertions.assertNotNull(updatedUser),
                () -> Assertions.assertEquals(updatedUser.size(), users.size())
        );
    }

    @Test
    public void getUserListFromGatewayFail() {

        Mockito.when(apiGatewayRestTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<ParameterizedTypeReference<List<User>>>any())
        ).thenThrow(RestClientResponseException.class);

        thrown.expect(RuntimeException.class);
        thrown.expectMessage(ErrorMessage.ERROR_WHILE_CALLING_JSON_PROVIDER);

        List<User> updatedUser = userGateway.getUserList();
    }
}
