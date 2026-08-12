package com.kinesiovitality.sucursal.service;

import java.util.List;

import com.kinesiovitality.sucursal.model.Sucursal;

public interface SucursalService {

    Sucursal guardar(Sucursal sucursal);

    Sucursal buscarPorId(Long id);

    List<Sucursal> listar();
    
    List<Sucursal> listarActivas();

    Sucursal actualizar(Long id, Sucursal sucursal);

    void eliminar(Long id);
    
    void reactivar(Long id);

}