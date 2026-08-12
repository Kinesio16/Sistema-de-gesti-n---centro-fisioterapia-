package com.kinesiovitality.usuario.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kinesiovitality.common.enums.Rol;
import com.kinesiovitality.usuario.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByUsername(String username);

    boolean existsByUsername(String username);
    
    long countByRolAndActivo(
            Rol rol,
            Boolean activo);
}