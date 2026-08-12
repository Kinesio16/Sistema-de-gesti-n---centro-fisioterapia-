package com.kinesiovitality.sucursal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kinesiovitality.common.enums.EstadoRegistro;
import com.kinesiovitality.sucursal.model.Sucursal;

@Repository
public interface SucursalRepository extends JpaRepository<Sucursal, Long> {

	boolean existsByNombreIgnoreCase(String nombre);

	Optional<Sucursal> findByNombre(String nombre);

    boolean existsByCorreo(String correo);

    List<Sucursal> findByEstado(EstadoRegistro estado);
}