package com.api.campingproject.core.repository;

import com.api.campingproject.api.model.EventosEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventosRepository extends JpaRepository<EventosEntity, Integer> {
}
