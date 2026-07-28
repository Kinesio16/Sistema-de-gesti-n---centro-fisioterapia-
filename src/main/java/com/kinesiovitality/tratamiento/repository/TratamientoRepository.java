package com.kinesiovitality.tratamiento.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kinesiovitality.common.enums.EstadoTratamiento;
import com.kinesiovitality.tratamiento.model.Tratamiento;

@Repository
public interface TratamientoRepository extends JpaRepository<Tratamiento, Long> {

    Optional<Tratamiento> findByCodigoTratamiento(String codigoTratamiento);

    boolean existsByCodigoTratamiento(String codigoTratamiento);

    List<Tratamiento> findByEstado(EstadoTratamiento estado);

    List<Tratamiento> findByPacienteId(Long pacienteId);

    List<Tratamiento> findByFisioterapeutaId(Long fisioterapeutaId);
    
    Optional<Tratamiento> findByPacienteIdAndEstado(
            Long pacienteId,
            EstadoTratamiento estado);
    
    long countByEstado(EstadoTratamiento estado);


}
