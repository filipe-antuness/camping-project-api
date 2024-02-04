package com.api.campingproject.api.vo;

import com.api.campingproject.api.model.EventosEntity;
import com.api.campingproject.api.model.UsuarioEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class UsuarioLogadoVO {
    private Integer id;
    private String nome;
    private String sobrenome;
    private String email;
    private Integer nivelAcesso;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataNascimento;

    public UsuarioLogadoVO () {}

    public UsuarioLogadoVO (UsuarioEntity usuarioEntity){
        this.id = usuarioEntity.getId();
        this.nome = usuarioEntity.getNome();
        this.sobrenome = usuarioEntity.getSobrenome();
        this.email = usuarioEntity.getEmail();
        this.nivelAcesso = usuarioEntity.getNivelAcesso();
        this.dataNascimento = usuarioEntity.getDataNascimento();
    }

}
