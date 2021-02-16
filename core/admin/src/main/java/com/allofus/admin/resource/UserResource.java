package com.allofus.admin.resource;


import com.allofus.admin.client.AuthClientProxy;
import com.allofus.commons.ws.request.LoginRequest;
import com.allofus.commons.ws.response.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping(value = "user")
@Slf4j
public class UserResource {


    @Autowired
    private AuthClientProxy authClientProxy;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<UserResponse>> get(Principal principal) {



        List<UserResponse> collect = Stream.iterate(0, (i) -> i + 1)
                .limit(2)
                .map((i) -> UserResponse.builder().email("teste").name("teste").build())
                .collect(Collectors.toList());

        return ResponseEntity.ok(collect);
    }
}
