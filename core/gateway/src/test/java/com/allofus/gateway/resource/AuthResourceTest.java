package com.allofus.gateway.resource;


import com.allofus.commons.ws.request.LoginRequest;
import com.allofus.gateway.model.Profile;
import com.allofus.gateway.model.Signature;
import com.allofus.gateway.model.User;
import com.allofus.gateway.model.UserProfile;
import com.allofus.gateway.service.AuthService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@SpringBootTest
//@WebMvcTest(controllers = AuthResource.class)
public class AuthResourceTest {


    private static User user;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AuthService authService;

    @Before
    public void mountUser() {


        user = User.builder()
                .email("thiagodoy@hotmail.com")
                .name("Thiago Henrique de Godoy")
                .attempts(0L)
                .signature(Signature
                        .builder()
                        .isEnable(true)
                        .build())
                .profiles(List.of(UserProfile
                        .builder()
                        .user(user)
                        .profile(Profile
                                .builder()
                                .description("Admin")
                                .build())
                        .isEnable(true)
                        .build()))
                .build();

    }


    @Test
    public void auth() throws Exception {


        LoginRequest request = LoginRequest.builder()
                .email("thiagodoy@hotmail.com")
                .password("12345")
                .build();


        given(authService.loadUserByUsername(request.getEmail())).willReturn(user);

        mockMvc.perform(post("/api/auth")
                .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", CoreMatchers.is("thiagodoy@hotmail.com")));



    }


}
