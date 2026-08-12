package com.kinesiovitality.fisioterapeuta.service;

import java.util.List;

import com.kinesiovitality.fisioterapeuta.model.Fisioterapeuta;

public interface FisioterapeutaService {

    Fisioterapeuta guardar(Fisioterapeuta fisioterapeuta);

    Fisioterapeuta buscarPorId(Long id);

    List<Fisioterapeuta> listar();
    
    List<Fisioterapeuta> listarActivos();

    Fisioterapeuta actualizar(Long id, Fisioterapeuta fisioterapeuta);

    void eliminar(Long id);
    
    void reactivar(Long id);

}
