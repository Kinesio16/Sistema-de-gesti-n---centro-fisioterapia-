package com.kinesiovitality.servicio.service;

import java.util.List;

import com.kinesiovitality.servicio.model.Servicio;

public interface ServicioService {

    Servicio guardar(Servicio servicio);

    Servicio buscarPorId(Long id);

    List<Servicio> listar();

    List<Servicio> listarActivos();

    List<Servicio> buscarPorNombre(String nombre);

    Servicio actualizar(Long id, Servicio servicio);

    void eliminar(Long id);
    
    void reactivar(Long id);

}
