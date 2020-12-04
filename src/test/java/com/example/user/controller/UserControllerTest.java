package com.example.user.controller;

import com.example.user.dto.User;
import com.example.user.dto.UserCountResponse;
import com.example.user.service.user.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

    private static final String countUrl = "/users/count";
    private static final String listUrl = "/users";

    private MockMvc mockMvc;

    @InjectMocks
    private UserController userController;

    @MockBean
    private UserService userService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .setViewResolvers((viewName, locale) -> new MappingJackson2JsonView())
                .build();
    }

    @Test
    public void testCount() throws Exception {

        UserCountResponse response = new UserCountResponse();

        // Pass
        given(userService.countUniqueUsers())
                .willReturn(response);
        mockMvc.perform(get(countUrl)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testupdate() throws Exception {

        List<User> responseMap = Collections.emptyList();
        // Pass
        given(userService.getUpdatedUser())
                .willReturn(responseMap);
        mockMvc.perform(get(listUrl)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
