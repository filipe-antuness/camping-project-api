package com.api.campingproject.api.vo;

import com.api.campingproject.api.model.EnderecoEntity;
import com.api.campingproject.api.model.EventosEntity;
import com.api.campingproject.api.model.UsuarioEntity;
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
    private Date dataNascimento;
    private String cpf;
    private String senha;
    private String telefone;
    private Integer nivelAcesso;
    private List<EnderecoEntity> enderecos;
    private List<EventosEntity> eventos;

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
        this.enderecos = usuarioEntity.getEndereco();
        this.eventos = usuarioEntity.getEventos();
    }

    public static List<UsuarioVO> converterListadeUsuarios (List<UsuarioEntity> usuarios){
       return usuarios.stream().map( usuarioEntity -> {
           UsuarioVO usuarioVO = new UsuarioVO(usuarioEntity);
           return usuarioVO;
       }).collect(Collectors.toList());
    }

}
