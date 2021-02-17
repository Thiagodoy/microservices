/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.allofus.gateway.resource;


import com.allofus.commons.ws.request.LoginRequest;
import com.allofus.commons.ws.response.AuthResponse;
import com.allofus.gateway.service.AuthService;
import com.allofus.gateway.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
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
    public ResponseEntity auth(@RequestBody LoginRequest request) {

        try {
            authenticate(request.getEmail(), request.getPassword());
            final UserDetails userDetails = authService
                    .loadUserByUsername(request.getEmail());
            final String token = jwtTokenUtil.generateToken(userDetails);

            AuthResponse response = AuthResponse.builder().token(token).email(request.getEmail()).profile("teste").name("Thiago").email("thiagogody@hotmai.com").build();


            return ResponseEntity.ok(response);

        } catch (Exception ex) {
            Logger.getLogger(AuthResource.class.getName()).log(Level.SEVERE, null, ex);
            return ResponseEntity.status(HttpStatus.resolve(500)).body(ex.getMessage());
        }

    }

    @RequestMapping(value = "getUserByToken",method = RequestMethod.GET)
    public ResponseEntity getUserByToken(HttpServletRequest request){


        //FIXME Create a service with cache method tha load a user
        final String requestTokenHeader = request.getHeader("Authorization");
        String jwtToken = requestTokenHeader.substring(7);

        String usernameFromToken = jwtTokenUtil.getUsernameFromToken(jwtToken);
        UserDetails userDetails = authService.loadUserByUsername(usernameFromToken);

        AuthResponse response = AuthResponse.builder().token(jwtToken).email("thiagodoy@hotmail.com").profile("teste").name("Thiago").email("thiagogody@hotmai.com").build();

        return ResponseEntity.ok(response);

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

    private void authenticate(String username, String password) throws Exception {
        try {
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            Object principal =  authenticate.getPrincipal();
        } catch (DisabledException e) {
            log.error("Exception : {}", e);
        } catch (BadCredentialsException e) {
            //TODO: Atualiza as tentativas quando chegar a 5 tentativas desabilitar o usuario
            log.error("Exception : {}", e);
        }catch (LockedException e){
            log.error("Exception : {}", e);
        }catch (AccountExpiredException e){
            log.error("Exception : {}", e);
        }
    }

}
