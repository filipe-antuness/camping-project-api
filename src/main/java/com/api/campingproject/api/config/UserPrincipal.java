package com.api.campingproject.api.config;

import com.api.campingproject.api.model.UsuarioEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class UserPrincipal implements UserDetails {

    private String email;
    private String senha;
    private SimpleGrantedAuthority authorities;

    public UserPrincipal(UsuarioEntity usuario){
        this.email = usuario.getEmail();
        this.senha = usuario.getSenha();
        this.authorities = new SimpleGrantedAuthority("ROLE_".concat(usuario.getNivelAcesso().toString()));
    }

    public static UserPrincipal create(UsuarioEntity usuario){
        return new UserPrincipal(usuario);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(authorities);
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
