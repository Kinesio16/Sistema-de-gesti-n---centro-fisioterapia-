package com.kinesiovitality.servicio.mapper;

import com.kinesiovitality.servicio.dto.ServicioRequest;
import com.kinesiovitality.servicio.dto.ServicioResponse;
import com.kinesiovitality.servicio.model.Servicio;

public class ServicioMapper {

    private ServicioMapper() {
    }

    public static Servicio toEntity(ServicioRequest request) {

        Servicio servicio = new Servicio();

        servicio.setNombre(request.getNombre());
        servicio.setDescripcion(request.getDescripcion());
        servicio.setCantidadSesiones(request.getCantidadSesiones());
        servicio.setPrecioCosto(request.getPrecioCosto());
        servicio.setPrecioVenta(request.getPrecioVenta());

        return servicio;
    }

    public static ServicioResponse toResponse(Servicio servicio) {

        ServicioResponse response = new ServicioResponse();

        response.setId(servicio.getId());
        response.setCodigoServicio(servicio.getCodigoServicio());
        response.setNombre(servicio.getNombre());
        response.setDescripcion(servicio.getDescripcion());
        response.setCantidadSesiones(servicio.getCantidadSesiones());
        response.setPrecioCosto(servicio.getPrecioCosto());
        response.setPrecioVenta(servicio.getPrecioVenta());
        response.setActivo(servicio.getActivo());
        response.setFechaCreacion(servicio.getFechaCreacion());
        response.setFechaActualizacion(servicio.getFechaActualizacion());

        return response;
    }

}
