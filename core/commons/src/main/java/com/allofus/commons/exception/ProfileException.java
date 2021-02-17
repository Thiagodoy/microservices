package com.allofus.commons.exception;

public class ProfileException extends Exception {

    public ProfileException(){
        super("Usuário sem um perfil ativo!");
    }

}
