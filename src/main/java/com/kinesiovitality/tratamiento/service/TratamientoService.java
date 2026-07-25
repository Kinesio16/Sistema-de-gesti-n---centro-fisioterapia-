package com.kinesiovitality.tratamiento.service;

import java.util.List;

import com.kinesiovitality.common.enums.EstadoTratamiento;
import com.kinesiovitality.tratamiento.model.Tratamiento;

public interface TratamientoService {

    Tratamiento guardar(Tratamiento tratamiento,
                        Long pacienteId,
                        Long fisioterapeutaId,
                        Long evaluacionId);

    Tratamiento buscarPorId(Long id);

    List<Tratamiento> listar();

    Tratamiento actualizar(Long id,
                           Tratamiento tratamiento,
                           Long pacienteId,
                           Long fisioterapeutaId,
                           Long evaluacionId);

    

    List<Tratamiento> listarPorPaciente(Long pacienteId);

    List<Tratamiento> listarPorFisioterapeuta(Long fisioterapeutaId);

    List<Tratamiento> listarPorEstado(EstadoTratamiento estado);

    Tratamiento finalizar(Long id);

    Tratamiento suspender(Long id);

    Tratamiento reanudar(Long id);

    Tratamiento cancelar(Long id);
    
    Tratamiento registrarSesion(Long id);

}
