package com.allofus.commons.ws.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthResponse {

    private String email;
    private String name;
    private List<ProfileResponse> profiles;
    private Long signature;
    private String token;

}
