package com.api.campingproject.api.vo;

import lombok.Getter;

@Getter
public class TokenVO {

    private String token;
    private String bearer;

    public TokenVO(String token, String bearer) {
        this.token = token;
        this.bearer = bearer;
    }
}
