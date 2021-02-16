package com.allofus.admin.client;

import com.allofus.commons.ws.request.LoginRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "gateway-server-x")
public interface AuthClientProxy {

    @PostMapping(value = "auth-server/api/auth")
    String login(@RequestBody LoginRequest request);

}
