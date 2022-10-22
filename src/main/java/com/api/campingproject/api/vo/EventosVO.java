package com.api.campingproject.api.vo;

import com.api.campingproject.api.model.EventosEntity;
import com.api.campingproject.api.model.UsuarioEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class EventosVO {

    private Integer id;
    private String titulo;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataAbertura;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataEncerramento;
    private String local;
    private String descricao;
    private Double taxaInscricao;
    private Integer idadeMinima;
    private String caminhoImagem;
    private List<UsuarioEntity> inscritos;

    public EventosVO(){}

    public EventosVO(EventosEntity eventosEntity){
        this.id = eventosEntity.getId();
        this.titulo = eventosEntity.getTitulo();
        this.dataAbertura = eventosEntity.getDataAbertura();
        this.dataEncerramento = eventosEntity.getDataEncerramento();
        this.local = eventosEntity.getLocal();
        this.descricao = eventosEntity.getDescricao();
        this.taxaInscricao = eventosEntity.getTaxaInscricao();
        this.idadeMinima = eventosEntity.getIdadeMinima();
        this.caminhoImagem = eventosEntity.getCaminhoImagem();
        this.inscritos = eventosEntity.getInscritos();
    }

    public static List<EventosVO> converterListadeEventos (List<EventosEntity> eventos){
        return eventos.stream().map( eventosEntity -> {
            EventosVO eventosVO = new EventosVO(eventosEntity);
            return eventosVO;
        }).collect(Collectors.toList());
    }
}
