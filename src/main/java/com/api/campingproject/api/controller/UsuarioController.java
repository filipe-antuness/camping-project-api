package com.api.campingproject.api.controller;

import com.api.campingproject.core.service.form.UsuarioForm;
import com.api.campingproject.api.vo.UsuarioVO;
import com.api.campingproject.core.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;


@RestController()
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioForm> cadastrar(@RequestBody UsuarioForm usuarioForm, UriComponentsBuilder uriComponentsBuilder){
        return usuarioService.cadastrar(usuarioForm, uriComponentsBuilder);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioVO> buscarUsuarioPordId(@PathVariable Integer id){
        return usuarioService.buscaUsuarioPorId(id);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioVO>> Lista(){
        return usuarioService.Listar();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar (@PathVariable Integer id){
        return usuarioService.deletarUsuario(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioForm> atualizar (@RequestBody UsuarioForm usuarioForm, @PathVariable Integer id){
        return usuarioService.atualizarUsuario(usuarioForm, id);
    }

}
