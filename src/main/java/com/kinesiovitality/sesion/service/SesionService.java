package com.kinesiovitality.sesion.service;

import java.util.List;

import com.kinesiovitality.sesion.model.Sesion;

public interface SesionService {

    Sesion guardar(Sesion sesion, Long tratamientoId);

    Sesion buscarPorId(Long id);

    List<Sesion> listar();

    Sesion actualizar(Long id, Sesion sesion, Long tratamientoId);

    Sesion registrarRealizada(Long id);

    Sesion reprogramar(Long id, Sesion sesion);

    Sesion cancelar(Long id);

    Sesion noAsistio(Long id);
}
