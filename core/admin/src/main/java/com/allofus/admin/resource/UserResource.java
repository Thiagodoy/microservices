package com.allofus.admin.resource;


import com.allofus.admin.adapters.UserMapper;
import com.allofus.admin.client.AuthClientProxy;
import com.allofus.admin.service.UserService;
import com.allofus.commons.ws.request.LoginRequest;
import com.allofus.commons.ws.request.UserRequest;
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
@RequestMapping(value = "/api/user")
@Slf4j
public class UserResource extends Resource<UserRequest,UserResponse, UserService, UserMapper> {

    public UserResource(UserMapper mapper, UserService service) {
        super(mapper, service);
    }
}
