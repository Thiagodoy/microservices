/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.allofus.gateway.resource;


import com.allofus.commons.exception.ProfileException;
import com.allofus.commons.exception.SignatureException;
import com.allofus.commons.ws.request.LoginRequest;
import com.allofus.commons.ws.response.AuthResponse;
import com.allofus.commons.ws.response.ProfileResponse;
import com.allofus.gateway.model.User;
import com.allofus.gateway.service.AuthService;
import com.allofus.gateway.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author thiag
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/auth")
public class AuthResource {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AuthService authService;


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity auth(@RequestBody LoginRequest request) throws Exception {
        final User user = authenticate(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(this.mount(user, true));
    }

    @RequestMapping(value = "getUserByToken", method = RequestMethod.GET)
    public ResponseEntity getUserByToken(HttpServletRequest request) {

        final String requestTokenHeader = request.getHeader("Authorization");
        String jwtToken = requestTokenHeader.substring(7);

        String usernameFromToken = jwtTokenUtil.getUsernameFromToken(jwtToken);
        User userDetails = (User) authService.loadUserByUsername(usernameFromToken);

        return ResponseEntity.ok(mount(userDetails, false));

    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity checkToken(HttpServletRequest request) {

        try {

            final String requestTokenHeader = request.getHeader("Authorization");
            String jwtToken = null;

            jwtToken = requestTokenHeader.substring(7);

            boolean isValid = jwtTokenUtil.isTokenExpired(jwtToken);

            if (!isValid) {
                return ResponseEntity.ok().build();
            } else {
                throw new Exception();
            }
        } catch (Exception ex) {
            //Logger.getLogger(AuthResource.class.getName()).log(Level.SEVERE, "[ checkToken ]", ex);
            return ResponseEntity.status(HttpStatus.resolve(500)).body("TOKEN_EXPIRED");
        }
    }

    private AuthResponse mount(User user, boolean generateToken) {

        AuthResponse.AuthResponseBuilder response = AuthResponse.builder()
                .email(user.getEmail())
                .name(user.getName())
                .profiles(user.getProfiles().stream().map(userProfile -> {
                    return ProfileResponse.builder()
                            .description(userProfile.getProfile().getDescription())
                            .id(userProfile.getProfile().getId())
                            .build();
                }).collect(Collectors.toList()));

        if (generateToken) {
            response.token(jwtTokenUtil.generateToken(user));
        }

        return response.build();

    }

    private User authenticate(String username, String password) throws Exception {
        try {
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            User user = (User) authenticate.getPrincipal();

            Optional.ofNullable(user.getSignature())
                    .stream()
                    .filter(signature -> signature.getIsEnable())
                    .findFirst()
                    .orElseThrow(SignatureException::new);

            Optional.ofNullable(user.getProfiles())
                    .stream()
                    .filter(userProfiles -> user.isEnabled())
                    .findFirst()
                    .orElseThrow(ProfileException::new);

            authService.resetAttempts(username);
            return (User) authenticate.getPrincipal();
        } catch (BadCredentialsException e) {
            authService.updateAttempts(username);
            throw e;
        }
    }

}
