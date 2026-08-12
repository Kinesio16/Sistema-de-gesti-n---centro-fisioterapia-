package com.kinesiovitality.paciente.service;

import java.util.List;

import com.kinesiovitality.paciente.model.Paciente;

public interface PacienteService {

    Paciente guardar(Paciente paciente);

    Paciente buscarPorId(Long id);

    List<Paciente> listar();
    
    List<Paciente> listarActivos();

    Paciente actualizar(Long id, Paciente paciente);

    void eliminar(Long id);
    
    void reactivar(Long id);
    

}
