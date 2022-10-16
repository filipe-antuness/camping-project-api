package com.api.campingproject.api.controller;

import com.api.campingproject.api.vo.EventosVO;
import com.api.campingproject.core.service.EventosService;
import com.api.campingproject.core.service.form.EventosForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController()
@RequestMapping("/eventos")
public class EventosController {

    @Autowired
    EventosService eventosService;

    @PreAuthorize("hasRole('0')")
    @PostMapping
    public ResponseEntity<EventosForm> cadastrar(@RequestBody EventosForm eventosForm, UriComponentsBuilder uriComponentsBuilder){
        return eventosService.cadastrar(eventosForm, uriComponentsBuilder);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventosVO> buscarEventoPordId(@PathVariable Integer id){
        return eventosService.buscaEventoPorId(id);
    }

    @GetMapping
    public ResponseEntity<List<EventosVO>> Lista(){
        return eventosService.Listar();
    }

    @PreAuthorize("hasRole('0')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar (@PathVariable Integer id){
        return eventosService.deletarEvento(id);
    }

    @PreAuthorize("hasRole('0')")
    @PutMapping("/{id}")
    public ResponseEntity<EventosForm> atualizar (@RequestBody EventosForm eventosForm, @PathVariable Integer id){
        return eventosService.atualizarEvento(eventosForm, id);
    }

}
