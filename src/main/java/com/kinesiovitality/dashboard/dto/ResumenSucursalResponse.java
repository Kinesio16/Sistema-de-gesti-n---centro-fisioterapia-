package com.kinesiovitality.dashboard.dto;

import java.math.BigDecimal;

public class ResumenSucursalResponse {

    private Long sucursalId;

    private String nombreSucursal;

    // Ventas
    private Long ventasHoy;
    private Long ventasSemana;
    private Long ventasMes;
    private Long ventasAnio;

    // Ingresos
    private BigDecimal ingresosHoy;
    private BigDecimal ingresosSemana;
    private BigDecimal ingresosMes;
    private BigDecimal ingresosAnio;

    // Extra
    private BigDecimal ticketPromedio;

    public ResumenSucursalResponse() {
    }

    public Long getSucursalId() {
        return sucursalId;
    }

    public void setSucursalId(Long sucursalId) {
        this.sucursalId = sucursalId;
    }

    public String getNombreSucursal() {
        return nombreSucursal;
    }

    public void setNombreSucursal(String nombreSucursal) {
        this.nombreSucursal = nombreSucursal;
    }

    public Long getVentasHoy() {
        return ventasHoy;
    }

    public void setVentasHoy(Long ventasHoy) {
        this.ventasHoy = ventasHoy;
    }

    public Long getVentasSemana() {
        return ventasSemana;
    }

    public void setVentasSemana(Long ventasSemana) {
        this.ventasSemana = ventasSemana;
    }

    public Long getVentasMes() {
        return ventasMes;
    }

    public void setVentasMes(Long ventasMes) {
        this.ventasMes = ventasMes;
    }

    public Long getVentasAnio() {
        return ventasAnio;
    }

    public void setVentasAnio(Long ventasAnio) {
        this.ventasAnio = ventasAnio;
    }

    public BigDecimal getIngresosHoy() {
        return ingresosHoy;
    }

    public void setIngresosHoy(BigDecimal ingresosHoy) {
        this.ingresosHoy = ingresosHoy;
    }

    public BigDecimal getIngresosSemana() {
        return ingresosSemana;
    }

    public void setIngresosSemana(BigDecimal ingresosSemana) {
        this.ingresosSemana = ingresosSemana;
    }

    public BigDecimal getIngresosMes() {
        return ingresosMes;
    }

    public void setIngresosMes(BigDecimal ingresosMes) {
        this.ingresosMes = ingresosMes;
    }

    public BigDecimal getIngresosAnio() {
        return ingresosAnio;
    }

    public void setIngresosAnio(BigDecimal ingresosAnio) {
        this.ingresosAnio = ingresosAnio;
    }

    public BigDecimal getTicketPromedio() {
        return ticketPromedio;
    }

    public void setTicketPromedio(BigDecimal ticketPromedio) {
        this.ticketPromedio = ticketPromedio;
    }

}