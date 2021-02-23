package com.allofus.commons.ws.request;

import lombok.Data;

@Data
public class UserRequest implements Request {

    private Long id;
    private String name;
    private String email;
    private String password;
    private Boolean isEnable;
    private Long profile;


}
