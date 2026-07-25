package com.kinesiovitality.cita.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kinesiovitality.common.enums.EstadoCita;
import com.kinesiovitality.cita.model.Cita;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {

    // Listar por estado
    List<Cita> findByEstado(EstadoCita estado);

    // Agenda del día
    List<Cita> findByFecha(LocalDate fecha);

    // Agenda de un fisioterapeuta
    List<Cita> findByFisioterapeutaId(Long fisioterapeutaId);

    // Historial de un paciente
    List<Cita> findByPacienteId(Long pacienteId);
    
    List<Cita> findByPacienteIdAndFecha(Long pacienteId, LocalDate fecha);

    // Validar que un paciente no tenga otra cita exactamente al mismo inicio
    boolean existsByPacienteIdAndFechaAndHoraInicio(
            Long pacienteId,
            LocalDate fecha,
            LocalTime horaInicio
    );

}