package com.api.campingproject.api.config;

import com.api.campingproject.api.model.UsuarioEntity;
import com.api.campingproject.core.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UsuarioEntity> usuarioExistente = usuarioRepository.findByEmail(username);

        if(usuarioExistente.isPresent()){
            return UserPrincipal.create(usuarioExistente.get());
        }

        throw new UsernameNotFoundException("Esse usuário não existe!");
    }
}
