package com.api.campingproject.core.service;

import com.api.campingproject.api.model.EventosEntity;
import com.api.campingproject.api.vo.EventosVO;
import com.api.campingproject.core.repository.EventosRepository;
import com.api.campingproject.core.service.form.EventosForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class EventosService {

    @Autowired
    EventosRepository eventosRepository;

    public ResponseEntity cadastrar (EventosForm eventosForm, UriComponentsBuilder uriComponentsBuilder) {
        EventosEntity eventosEntity =  eventosForm.converter();
        eventosRepository.save(eventosEntity);
        URI uri = uriComponentsBuilder.path("/eventos/{id}").buildAndExpand(eventosEntity.getId()).toUri();
        return ResponseEntity.created(uri).body(new EventosForm(eventosEntity));
    }

    public ResponseEntity buscaEventoPorId (Integer id){
        Optional<EventosEntity> eventosEntity = eventosRepository.findById(id);
        if(eventosEntity.isPresent()){
            EventosVO eventosVO = new EventosVO(eventosEntity.get());
            return ResponseEntity.ok(eventosVO);
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<List<EventosVO>> Listar (){
        List<EventosEntity> eventosEntities = eventosRepository.findAll();
        List<EventosVO> eventosVOS = EventosVO.converterListadeEventos(eventosEntities);
        return ResponseEntity.ok(eventosVOS);
    }

    public ResponseEntity<?> deletarEvento (Integer id){
        Optional<EventosEntity> optional = eventosRepository.findById(id);
        if(optional.isPresent()){
            eventosRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity atualizarEvento(EventosForm eventosForm, Integer id) {
        Optional<EventosEntity> eventosEntityOptional = eventosRepository.findById(id);

        if(eventosEntityOptional.isPresent()){

            eventosEntityOptional.get().setTitulo(eventosForm.getTitulo());
            eventosEntityOptional.get().setDataAbertura(eventosForm.getDataAbertura());
            eventosEntityOptional.get().setDataEncerramento(eventosForm.getDataEncerramento());
            eventosEntityOptional.get().setLocal(eventosForm.getLocal());
            eventosEntityOptional.get().setDescricao(eventosForm.getDescricao());
            eventosEntityOptional.get().setTaxaInscricao(eventosForm.getTaxaInscricao());
            eventosEntityOptional.get().setIdadeMinima(eventosForm.getIdadeMinima());
            eventosEntityOptional.get().setCaminhoImagem(eventosForm.getCaminhoImagem());
            eventosEntityOptional.get().setInscritos(eventosForm.getInscritos());

            eventosRepository.save(eventosEntityOptional.get());

            return ResponseEntity.ok(new EventosForm(eventosEntityOptional.get()));
        }
        return ResponseEntity.notFound().build();
    }
}