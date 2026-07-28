package com.kinesiovitality.dashboard.dto;

import java.math.BigDecimal;

public class DashboardResponse {

    // ===========================
    // PACIENTES
    // ===========================

    private Long totalPacientes;

    private Long pacientesActivos;

    private Long pacientesNuevosMes;

    // ===========================
    // FISIOTERAPEUTAS
    // ===========================

    private Long totalFisioterapeutas;

    private Long fisioterapeutasActivos;

    // ===========================
    // TRATAMIENTOS
    // ===========================

    private Long tratamientosActivos;

    private Long tratamientosSuspendidos;

    private Long tratamientosFinalizados;

    private Long tratamientosCancelados;

    // ===========================
    // SESIONES
    // ===========================

    private Long sesionesHoy;

    private Long sesionesSemana;

    private Long sesionesMes;

    // ===========================
    // VENTAS
    // ===========================

    private Long ventasHoy;

    private Long ventasSemana;

    private Long ventasMes;

    private Long ventasAnio;

    // ===========================
    // INGRESOS
    // ===========================

    private BigDecimal ingresosHoy;

    private BigDecimal ingresosSemana;

    private BigDecimal ingresosMes;

    private BigDecimal ingresosAnio;
    
    
    //extras
    private BigDecimal ticketPromedio;

    private Long paquetesVendidosMes;

    private Long sesionesPendientes;

    private Long citasHoy;

    private Long citasPendientes;

    public DashboardResponse() {
    }
    
    // getters y setters	

	public Long getTotalPacientes() {
		return totalPacientes;
	}

	public void setTotalPacientes(Long totalPacientes) {
		this.totalPacientes = totalPacientes;
	}

	public Long getPacientesActivos() {
		return pacientesActivos;
	}

	public void setPacientesActivos(Long pacientesActivos) {
		this.pacientesActivos = pacientesActivos;
	}

	public Long getPacientesNuevosMes() {
		return pacientesNuevosMes;
	}

	public void setPacientesNuevosMes(Long pacientesNuevosMes) {
		this.pacientesNuevosMes = pacientesNuevosMes;
	}

	public Long getTotalFisioterapeutas() {
		return totalFisioterapeutas;
	}

	public void setTotalFisioterapeutas(Long totalFisioterapeutas) {
		this.totalFisioterapeutas = totalFisioterapeutas;
	}

	public Long getFisioterapeutasActivos() {
		return fisioterapeutasActivos;
	}

	public void setFisioterapeutasActivos(Long fisioterapeutasActivos) {
		this.fisioterapeutasActivos = fisioterapeutasActivos;
	}

	public Long getTratamientosActivos() {
		return tratamientosActivos;
	}

	public void setTratamientosActivos(Long tratamientosActivos) {
		this.tratamientosActivos = tratamientosActivos;
	}

	public Long getTratamientosSuspendidos() {
		return tratamientosSuspendidos;
	}

	public void setTratamientosSuspendidos(Long tratamientosSuspendidos) {
		this.tratamientosSuspendidos = tratamientosSuspendidos;
	}

	public Long getTratamientosFinalizados() {
		return tratamientosFinalizados;
	}

	public void setTratamientosFinalizados(Long tratamientosFinalizados) {
		this.tratamientosFinalizados = tratamientosFinalizados;
	}

	public Long getTratamientosCancelados() {
		return tratamientosCancelados;
	}

	public void setTratamientosCancelados(Long tratamientosCancelados) {
		this.tratamientosCancelados = tratamientosCancelados;
	}

	public Long getSesionesHoy() {
		return sesionesHoy;
	}

	public void setSesionesHoy(Long sesionesHoy) {
		this.sesionesHoy = sesionesHoy;
	}

	public Long getSesionesSemana() {
		return sesionesSemana;
	}

	public void setSesionesSemana(Long sesionesSemana) {
		this.sesionesSemana = sesionesSemana;
	}

	public Long getSesionesMes() {
		return sesionesMes;
	}

	public void setSesionesMes(Long sesionesMes) {
		this.sesionesMes = sesionesMes;
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

	public Long getPaquetesVendidosMes() {
		return paquetesVendidosMes;
	}

	public void setPaquetesVendidosMes(Long paquetesVendidosMes) {
		this.paquetesVendidosMes = paquetesVendidosMes;
	}

	public Long getSesionesPendientes() {
		return sesionesPendientes;
	}

	public void setSesionesPendientes(Long sesionesPendientes) {
		this.sesionesPendientes = sesionesPendientes;
	}

	public Long getCitasHoy() {
		return citasHoy;
	}

	public void setCitasHoy(Long citasHoy) {
		this.citasHoy = citasHoy;
	}

	public Long getCitasPendientes() {
		return citasPendientes;
	}

	public void setCitasPendientes(Long citasPendientes) {
		this.citasPendientes = citasPendientes;
	}

	
    
}