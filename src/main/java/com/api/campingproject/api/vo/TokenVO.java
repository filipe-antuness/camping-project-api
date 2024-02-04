package com.api.campingproject.api.vo;

import lombok.Getter;

@Getter
public class TokenVO {

    private String token;
    private UsuarioLogadoVO usuario;

    public TokenVO(String token, UsuarioLogadoVO usuario) {
        this.token = token;
        this.usuario = usuario;
    }
}
