package com.api.campingproject.api.vo.form;

import com.api.campingproject.api.model.UsuarioEntity;
import lombok.Data;

import java.util.Date;

@Data
public class UsuarioForm {

    private String nome;
    private String sobrenome;
    private String email;
    private Date dataNascimento;
    private String cpf;
    private String senha;
    private String telefone;

    public UsuarioEntity converter() {
        return new UsuarioEntity(nome, sobrenome, email, dataNascimento, cpf, senha, telefone);
    }

    public UsuarioForm(){

    }


    public UsuarioForm (UsuarioEntity usuarioEntity) {
        this.nome = usuarioEntity.getNome();
        this.sobrenome = usuarioEntity.getSobrenome();
        this.email = usuarioEntity.getEmail();
        this.dataNascimento = usuarioEntity.getDataNascimento();
        this.cpf = usuarioEntity.getCpf();
        this.senha = usuarioEntity.getSenha();
        this.telefone = usuarioEntity.getTelefone();
    }
}
