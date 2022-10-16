package com.api.campingproject.api.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Value("${forum.jwt.expiration}")
    private String expiration;

    @Value("${forum.jwt.secret}")
    private String secret;
    public String gerarToken(Authentication authentication) {
        UserPrincipal usuario = (UserPrincipal) authentication.getPrincipal();
        Date data = new Date();
        Date dataExpiracao = new Date(data.getTime() + Long.parseLong(expiration));

        return Jwts.builder()
                .setIssuer("API Acampamentos")
                .setSubject(usuario.getUsername())
                .setIssuedAt(data)
                .setExpiration(dataExpiracao)
                .signWith(SignatureAlgorithm.HS256 ,secret)
                .compact();
    }

    public boolean isTokenValido(String token) {
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public String getIdUsuario(String token) {
       Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
       return claims.getSubject();

    }
}
