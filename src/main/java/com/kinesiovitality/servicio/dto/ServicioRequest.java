package com.kinesiovitality.servicio.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ServicioRequest {

    @NotBlank(message = "El nombre es obligatorio.")
    @Size(max = 100)
    private String nombre;

    @Size(max = 500)
    private String descripcion;

    @NotNull(message = "La cantidad de sesiones es obligatoria.")
    @Min(value = 1, message = "La cantidad de sesiones debe ser mayor a cero.")
    private Integer cantidadSesiones;

    @NotNull(message = "El precio de costo es obligatorio.")
    @DecimalMin(value = "0.00", message = "El precio de costo debe ser mayor o igual a cero.")
    private BigDecimal precioCosto;

    @NotNull(message = "El precio de venta es obligatorio.")
    @DecimalMin(value = "0.00", message = "El precio de venta debe ser mayor o igual a cero.")
    private BigDecimal precioVenta;

    public ServicioRequest() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getCantidadSesiones() {
        return cantidadSesiones;
    }

    public void setCantidadSesiones(Integer cantidadSesiones) {
        this.cantidadSesiones = cantidadSesiones;
    }

    public BigDecimal getPrecioCosto() {
        return precioCosto;
    }

    public void setPrecioCosto(BigDecimal precioCosto) {
        this.precioCosto = precioCosto;
    }

    public BigDecimal getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(BigDecimal precioVenta) {
        this.precioVenta = precioVenta;
    }
}
