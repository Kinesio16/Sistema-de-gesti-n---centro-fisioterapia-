package com.kinesiovitality.cita.service;

import java.util.List;

import com.kinesiovitality.cita.dto.CitaRequest;
import com.kinesiovitality.cita.model.Cita;

public interface CitaService {

    Cita guardar(CitaRequest request);

    Cita buscarPorId(Long id);

    List<Cita> listar();

    Cita actualizar(Long id, CitaRequest request);

    void cancelar(Long id);

}
