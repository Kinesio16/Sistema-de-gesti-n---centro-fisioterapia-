package com.kinesiovitality.tratamiento.business;

import com.kinesiovitality.sesion.model.Sesion;
import com.kinesiovitality.venta.model.Venta;

public interface TratamientoBusinessService {

    void procesarVenta(Venta venta);
    void revertirVenta(Venta venta);
    void registrarSesion(Sesion sesion);

    void eliminarSesion(Sesion sesion);

}