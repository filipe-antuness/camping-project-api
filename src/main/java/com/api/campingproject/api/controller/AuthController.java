package com.api.campingproject.api.controller;

import com.api.campingproject.api.config.TokenService;
import com.api.campingproject.api.model.UsuarioEntity;
import com.api.campingproject.api.vo.TokenVO;
import com.api.campingproject.api.vo.UsuarioLogadoVO;
import com.api.campingproject.core.repository.UsuarioRepository;
import com.api.campingproject.core.service.form.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UsuarioRepository repository;

    @PostMapping
    public ResponseEntity<TokenVO> autenticar(@RequestBody @Valid LoginForm form){
        UsernamePasswordAuthenticationToken dadosLogin = form.converter();

        Optional<UsuarioEntity> usuarioEntity = repository.findByEmail(form.getEmail());

        if(usuarioEntity.isPresent()){
            try {
                Authentication authentication = authManager.authenticate(dadosLogin);
                String token = tokenService.gerarToken(authentication);

                return ResponseEntity.ok(new TokenVO(token, new UsuarioLogadoVO(usuarioEntity.get())));
            }catch (AuthenticationException e){
                return ResponseEntity.badRequest().build();
            }
        }else{
            return ResponseEntity.badRequest().build();
        }

    }
}
