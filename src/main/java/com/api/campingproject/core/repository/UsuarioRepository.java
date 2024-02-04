package com.api.campingproject.core.repository;

import com.api.campingproject.api.config.UserPrincipal;
import com.api.campingproject.api.model.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {
    Optional<UsuarioEntity> findByEmail(String string);


}
