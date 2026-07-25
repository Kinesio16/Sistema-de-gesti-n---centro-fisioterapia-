package com.kinesiovitality.evaluacion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kinesiovitality.evaluacion.model.Evaluacion;

@Repository
public interface EvaluacionRepository extends JpaRepository<Evaluacion, Long>{

    List<Evaluacion> findByPacienteId(Long pacienteId);

    List<Evaluacion> findByFisioterapeutaId(Long fisioterapeutaId);

}
