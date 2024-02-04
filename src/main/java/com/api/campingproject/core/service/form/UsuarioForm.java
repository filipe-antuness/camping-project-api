package com.api.campingproject.core.service.form;

import com.api.campingproject.api.model.UsuarioEntity;
import lombok.Data;

import javax.persistence.Column;
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
    private String cep;
    private String cidade;
    private String estado;
    private String rua;
    private String numero;
    private String bairro;
    private Integer nivelAcesso;

    public UsuarioEntity converter() {
        return new UsuarioEntity(nome, sobrenome, email, dataNascimento, cpf, senha, telefone, cep, cidade, estado, rua, numero, bairro, nivelAcesso);
    }

    public UsuarioForm(){}

    public UsuarioForm (UsuarioEntity usuarioEntity) {
        this.nome = usuarioEntity.getNome();
        this.sobrenome = usuarioEntity.getSobrenome();
        this.email = usuarioEntity.getEmail();
        this.dataNascimento = usuarioEntity.getDataNascimento();
        this.cpf = usuarioEntity.getCpf();
        this.senha = usuarioEntity.getSenha();
        this.telefone = usuarioEntity.getTelefone();
        this.cep = usuarioEntity.getCep();
        this.cidade = usuarioEntity.getCidade();
        this.estado = usuarioEntity.getEstado();
        this.rua = usuarioEntity.getRua();
        this.numero = usuarioEntity.getNumero();
        this.bairro = usuarioEntity.getBairro();
        this.nivelAcesso = usuarioEntity.getNivelAcesso();
    }
}
