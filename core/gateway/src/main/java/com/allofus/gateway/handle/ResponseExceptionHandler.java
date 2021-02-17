package com.allofus.gateway.handle;


import com.allofus.commons.ws.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(value = {AuthenticationException.class})
    public ResponseEntity handleAuthenticationException(AuthenticationException authenticationException) {
        ErrorResponse.ErrorResponseBuilder builder = ErrorResponse.builder();
        ErrorResponse response = null;
        if (authenticationException instanceof DisabledException) {
            response = builder.code(403)
                    .message("Usuário esta desabilitado. Contate o administrador!")
                    .stackError(authenticationException.getMessage())
                    .build();
        } else if (authenticationException instanceof BadCredentialsException) {
            response = builder.code(401)
                    .message("Usuário / Senha inválidos")
                    .stackError(authenticationException.getMessage())
                    .build();
        }else{
            response = builder.code(500)
                    .message("Erro ao processar sua solicitação. Contate o administrador!")
                    .stackError(authenticationException.getMessage())
                    .build();
        }
        log.error("Exception: {}",authenticationException);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity handleException(Exception exception){
        ErrorResponse response = ErrorResponse.builder().code(500)
                .message("Erro ao processar sua solicitação. Contate o administrador!")
                .stackError(exception.getMessage())
                .build();
        log.error("Exception: {}",exception);
        return ResponseEntity.status(response.getCode()).body(response);
    }


}
