package com.allofus.admin.client;

import com.allofus.commons.ws.response.AuthResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "gateway-server")
public interface GatewayClientProxy {

    @GetMapping(value = "api/auth/getUserByToken")
    AuthResponse getUser(@RequestHeader("Authorization")String token);

}
