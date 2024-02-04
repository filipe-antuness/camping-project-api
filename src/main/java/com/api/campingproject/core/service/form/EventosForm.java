package com.api.campingproject.core.service.form;

import com.api.campingproject.api.model.EventosEntity;
import com.api.campingproject.api.model.UsuarioEntity;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class EventosForm {

    private Integer id;
    private String titulo;
    private Date dataAbertura;
    private Date dataEncerramento;
    private String local;
    private String descricao;
    private Double taxaInscricao;
    private Integer idadeMinima;
    private String caminhoImagem;
    private List<UsuarioEntity> inscritos;

    public EventosForm(){}

    public EventosForm(EventosEntity eventosEntity, UsuarioEntity usuario){
        this.inscritos.add(usuario);
    }

    public EventosForm(EventosEntity eventosEntity){
        this.id = eventosEntity.getId();
        this.titulo = eventosEntity.getTitulo();
        this.dataAbertura = eventosEntity.getDataAbertura();
        this.dataEncerramento = eventosEntity.getDataEncerramento();
        this.local = eventosEntity.getLocal();
        this.descricao = eventosEntity.getDescricao();
        this.taxaInscricao = eventosEntity.getTaxaInscricao();
        this.idadeMinima = eventosEntity.getIdadeMinima();
        this.inscritos = eventosEntity.getInscritos();
    }
    public EventosEntity converter() {
        return new EventosEntity(id,titulo,dataAbertura,dataEncerramento,local,descricao,taxaInscricao,idadeMinima,caminhoImagem,inscritos);
    }
}
