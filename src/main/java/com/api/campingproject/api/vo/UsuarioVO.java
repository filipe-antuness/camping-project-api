package com.api.campingproject.api.vo;

import com.api.campingproject.api.model.EventosEntity;
import com.api.campingproject.api.model.UsuarioEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class UsuarioVO {

    private Integer id;
    private String nome;
    private String sobrenome;
    private String email;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataNascimento;
    private String cpf;
    private String senha;
    private String telefone;
    private Integer nivelAcesso;
    private List<EventosEntity> eventos;
    private String cep;
    private String cidade;
    private String estado;
    private String rua;
    private String numero;
    private String bairro;

    public UsuarioVO () {}

    public UsuarioVO (UsuarioEntity usuarioEntity){
        this.id = usuarioEntity.getId();
        this.nome = usuarioEntity.getNome();
        this.sobrenome = usuarioEntity.getSobrenome();
        this.email = usuarioEntity.getEmail();
        this.dataNascimento = usuarioEntity.getDataNascimento();
        this.cpf = usuarioEntity.getCpf();
        this.senha = usuarioEntity.getSenha();
        this.telefone = usuarioEntity.getTelefone();
        this.nivelAcesso = usuarioEntity.getNivelAcesso();
        this.eventos = usuarioEntity.getEventos();
        this.cep = usuarioEntity.getCep();
        this.cidade = usuarioEntity.getCidade();
        this.estado = usuarioEntity.getEstado();
        this.rua = usuarioEntity.getRua();
        this.numero = usuarioEntity.getNumero();
        this.bairro = usuarioEntity.getBairro();
    }

    public static List<UsuarioVO> converterListadeUsuarios (List<UsuarioEntity> usuarios){
       return usuarios.stream().map( usuarioEntity -> {
           UsuarioVO usuarioVO = new UsuarioVO(usuarioEntity);
           return usuarioVO;
       }).collect(Collectors.toList());
    }

}
