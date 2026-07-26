package com.kinesiovitality.servicio.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kinesiovitality.servicio.model.Servicio;

public interface ServicioRepository extends JpaRepository<Servicio, Long> {

    // Buscar por código
    Optional<Servicio> findByCodigoServicio(String codigoServicio);

    // Buscar por nombre exacto
    Optional<Servicio> findByNombre(String nombre);

    // Validaciones
    boolean existsByCodigoServicio(String codigoServicio);

    boolean existsByNombreIgnoreCase(String nombre);

    // Solo servicios activos
    List<Servicio> findByActivoTrue();

    // Solo servicios inactivos
    List<Servicio> findByActivoFalse();

    // Búsqueda por nombre (para el buscador del frontend)
    List<Servicio> findByNombreContainingIgnoreCase(String nombre);

    // Servicios por cantidad de sesiones
    List<Servicio> findByCantidadSesiones(Integer cantidadSesiones);

    // Servicios con precio mayor o igual
    List<Servicio> findByPrecioVentaGreaterThanEqual(BigDecimal precio);

    // Servicios con precio menor o igual
    List<Servicio> findByPrecioVentaLessThanEqual(BigDecimal precio);

    // Servicios entre dos precios
    List<Servicio> findByPrecioVentaBetween(
            BigDecimal minimo,
            BigDecimal maximo);
    
    Optional<Servicio> findByIdAndActivoTrue(Long id);

}
