package com.kinesiovitality.venta.dto;

import java.math.BigDecimal;

import com.kinesiovitality.common.enums.EstadoFactura;
import com.kinesiovitality.common.enums.EstadoPago;
import com.kinesiovitality.common.enums.FormaPago;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class VentaRequest {

    @NotNull(message = "El paciente es obligatorio.")
    private Long pacienteId;

    @NotNull(message = "El servicio es obligatorio.")
    private Long servicioId;

    @DecimalMin(value = "0.00", message = "El descuento no puede ser negativo.")
    private BigDecimal descuento;

    @NotNull(message = "La forma de pago es obligatoria.")
    private FormaPago formaPago;

    @NotNull(message = "El estado del pago es obligatorio.")
    private EstadoPago estadoPago;

    @NotNull(message = "El estado de la factura es obligatorio.")
    private EstadoFactura estadoFactura;

    @Size(max = 500)
    private String observaciones;

    public VentaRequest() {
    }

    public Long getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }

    public Long getServicioId() {
        return servicioId;
    }

    public void setServicioId(Long servicioId) {
        this.servicioId = servicioId;
    }

    public BigDecimal getDescuento() {
        return descuento;
    }

    public void setDescuento(BigDecimal descuento) {
        this.descuento = descuento;
    }

    public FormaPago getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(FormaPago formaPago) {
        this.formaPago = formaPago;
    }

    public EstadoPago getEstadoPago() {
        return estadoPago;
    }

    public void setEstadoPago(EstadoPago estadoPago) {
        this.estadoPago = estadoPago;
    }

    public EstadoFactura getEstadoFactura() {
        return estadoFactura;
    }

    public void setEstadoFactura(EstadoFactura estadoFactura) {
        this.estadoFactura = estadoFactura;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

}
