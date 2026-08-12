package com.kinesiovitality.sucursal.mapper;

import com.kinesiovitality.sucursal.dto.SucursalRequest;
import com.kinesiovitality.sucursal.dto.SucursalResponse;
import com.kinesiovitality.sucursal.model.Sucursal;

public class SucursalMapper {

    private SucursalMapper() {
    }

    public static Sucursal toEntity(SucursalRequest request) {

        Sucursal sucursal = new Sucursal();

        sucursal.setNombre(request.getNombre());
        sucursal.setDireccion(request.getDireccion());
        sucursal.setTelefono(request.getTelefono());
        sucursal.setCorreo(request.getCorreo());

        return sucursal;

    }

    public static SucursalResponse toResponse(Sucursal sucursal) {

        return new SucursalResponse(

                sucursal.getId(),
                sucursal.getNombre(),
                sucursal.getDireccion(),
                sucursal.getTelefono(),
                sucursal.getCorreo(),
                sucursal.getEstado(),
                sucursal.getFechaCreacion(),
                sucursal.getFechaActualizacion()

        );

    }

}