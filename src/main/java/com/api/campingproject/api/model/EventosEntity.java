package com.api.campingproject.api.model;


import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "eventos")
public class EventosEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "data_abertura")
    private Date dataAbertura;

    @Column(name = "data_encerramento")
    private Date dataEncerramento;

    @Column(name = "local")
    private String local;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "taxa_inscricao")
    private Double taxaInscricao;

    @Column(name = "idade_minima")
    private Integer idadeMinima;

    @Column(name = "caminho_imagem")
    private String caminhoImagem;

    @ManyToMany
    @JoinColumn(name = "fk_inscritos")
    private List<UsuarioEntity> inscritos;
}
