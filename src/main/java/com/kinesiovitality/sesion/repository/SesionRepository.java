package com.kinesiovitality.sesion.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kinesiovitality.common.enums.EstadoSesion;
import com.kinesiovitality.sesion.model.Sesion;

@Repository
public interface SesionRepository extends JpaRepository<Sesion, Long> {

    Optional<Sesion> findByCodigoSesion(String codigoSesion);

    boolean existsByCodigoSesion(String codigoSesion);

    List<Sesion> findByTratamientoId(Long tratamientoId);

    List<Sesion> findByEstado(EstadoSesion estado);

    List<Sesion> findByFechaSesion(LocalDate fechaSesion);

    List<Sesion> findByTratamientoIdAndEstado(Long tratamientoId, EstadoSesion estado);

}
