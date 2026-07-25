package com.kinesiovitality.evaluacion.service;

import java.util.List;

import com.kinesiovitality.evaluacion.dto.EvaluacionRequest;
import com.kinesiovitality.evaluacion.model.Evaluacion;

public interface EvaluacionService {

    Evaluacion guardar(EvaluacionRequest request);

    Evaluacion buscarPorId(Long id);

    List<Evaluacion> listar();

    Evaluacion actualizar(Long id, EvaluacionRequest request);

    void eliminar(Long id);

}
