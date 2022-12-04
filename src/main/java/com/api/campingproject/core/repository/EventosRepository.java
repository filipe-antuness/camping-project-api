package com.api.campingproject.core.repository;

import com.api.campingproject.api.model.EventosEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventosRepository extends JpaRepository<EventosEntity, Integer> {

    Optional<EventosEntity> findAllByCaminhoImagem(String string);
}
