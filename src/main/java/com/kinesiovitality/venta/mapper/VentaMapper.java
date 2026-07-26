package com.kinesiovitality.venta.mapper;

import com.kinesiovitality.venta.dto.VentaRequest;
import com.kinesiovitality.venta.dto.VentaResponse;
import com.kinesiovitality.venta.model.Venta;

public class VentaMapper {

    private VentaMapper() {
    }

    public static Venta toEntity(VentaRequest request) {

        Venta venta = new Venta();

        venta.setDescuento(request.getDescuento());
        venta.setFormaPago(request.getFormaPago());
        venta.setEstadoPago(request.getEstadoPago());
        venta.setEstadoFactura(request.getEstadoFactura());
        venta.setObservaciones(request.getObservaciones());

        return venta;
    }

    public static VentaResponse toResponse(Venta venta) {

        VentaResponse response = new VentaResponse();

        response.setId(venta.getId());

        response.setCodigoVenta(venta.getCodigoVenta());

        response.setPacienteId(venta.getPaciente().getId());

        response.setPaciente(
                venta.getPaciente().getNombres()
                + " "
                + venta.getPaciente().getApellidos());

        response.setServicioId(
                venta.getServicio().getId());

        response.setNombreServicio(
                venta.getNombreServicio());

        response.setCantidadSesiones(
                venta.getCantidadSesiones());

        response.setPrecioUnitario(
                venta.getPrecioUnitario());

        response.setDescuento(
                venta.getDescuento());

        response.setTotal(
                venta.getTotal());

        response.setPromocion(
                venta.getPromocion());

        response.setFormaPago(
                venta.getFormaPago());

        response.setEstadoPago(
                venta.getEstadoPago());

        response.setEstadoFactura(
                venta.getEstadoFactura());

        response.setFechaVenta(
                venta.getFechaVenta());

        response.setObservaciones(
                venta.getObservaciones());

        response.setFechaCreacion(
                venta.getFechaCreacion());

        response.setFechaActualizacion(
                venta.getFechaActualizacion());

        return response;

    }

}
