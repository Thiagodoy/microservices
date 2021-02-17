package com.allofus.commons.exception;


public class SignatureException extends Exception{
    public SignatureException(){
        super("Usuário não possui um perfil ativo!");
    }
}
