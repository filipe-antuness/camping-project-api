package com.api.campingproject.api.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuarios")
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotNull
    @Column(name = "nome")
    private String nome;

    @Column(name = "sobrenome")
    private String sobrenome;

    @Column(name = "email")
    private String email;

    @Column(name = "data_nascimento")
    private Date dataNascimento;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "senha")
    private String senha;

    @Column(name = "telefone")
    private String telefone;

    @ManyToMany
    @JoinColumn(name = "fk_endereco")
    private List<EnderecoEntity> endereco;

    @Column(name = "nivel_acesso")
    private Integer nivelAcesso;

    @ManyToMany(mappedBy = "inscritos")
    @Column(name = "eventos")
    private List<EventosEntity> eventos;
}
