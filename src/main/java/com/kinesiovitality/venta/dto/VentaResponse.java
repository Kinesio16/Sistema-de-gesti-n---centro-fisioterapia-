package com.kinesiovitality.venta.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.kinesiovitality.common.enums.EstadoFactura;
import com.kinesiovitality.common.enums.EstadoPago;
import com.kinesiovitality.common.enums.FormaPago;

public class VentaResponse {

    private Long id;

    private String codigoVenta;

    private Long pacienteId;

    private String paciente;

    private Long servicioId;

    private String nombreServicio;
    
    private Long fisioterapeutaId;

    private String fisioterapeuta;

    private Integer cantidadSesiones;

    private BigDecimal precioUnitario;

    private BigDecimal descuento;

    private BigDecimal total;

    private Boolean promocion;

    private FormaPago formaPago;

    private EstadoPago estadoPago;

    private EstadoFactura estadoFactura;

    private LocalDate fechaVenta;

    private String observaciones;

    private LocalDateTime fechaCreacion;

    private LocalDateTime fechaActualizacion;
    
    private Long sucursalId;
    
    private String sucursal;
    

    public VentaResponse() {
    }

    //  getters y setters
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getCodigoVenta() {
		return codigoVenta;
	}


	public void setCodigoVenta(String codigoVenta) {
		this.codigoVenta = codigoVenta;
	}


	public Long getPacienteId() {
		return pacienteId;
	}


	public void setPacienteId(Long pacienteId) {
		this.pacienteId = pacienteId;
	}


	public String getPaciente() {
		return paciente;
	}


	public void setPaciente(String paciente) {
		this.paciente = paciente;
	}


	public Long getServicioId() {
		return servicioId;
	}


	public void setServicioId(Long servicioId) {
		this.servicioId = servicioId;
	}


	public String getNombreServicio() {
		return nombreServicio;
	}


	public void setNombreServicio(String nombreServicio) {
		this.nombreServicio = nombreServicio;
	}


	public Long getFisioterapeutaId() {
		return fisioterapeutaId;
	}

	public void setFisioterapeutaId(Long fisioterapeutaId) {
		this.fisioterapeutaId = fisioterapeutaId;
	}

	public String getFisioterapeuta() {
		return fisioterapeuta;
	}

	public void setFisioterapeuta(String fisioterapeuta) {
		this.fisioterapeuta = fisioterapeuta;
	}

	public Integer getCantidadSesiones() {
		return cantidadSesiones;
	}


	public void setCantidadSesiones(Integer cantidadSesiones) {
		this.cantidadSesiones = cantidadSesiones;
	}


	public BigDecimal getPrecioUnitario() {
		return precioUnitario;
	}


	public void setPrecioUnitario(BigDecimal precioUnitario) {
		this.precioUnitario = precioUnitario;
	}


	public BigDecimal getDescuento() {
		return descuento;
	}


	public void setDescuento(BigDecimal descuento) {
		this.descuento = descuento;
	}


	public BigDecimal getTotal() {
		return total;
	}


	public void setTotal(BigDecimal total) {
		this.total = total;
	}


	public Boolean getPromocion() {
		return promocion;
	}


	public void setPromocion(Boolean promocion) {
		this.promocion = promocion;
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


	public LocalDate getFechaVenta() {
		return fechaVenta;
	}


	public void setFechaVenta(LocalDate fechaVenta) {
		this.fechaVenta = fechaVenta;
	}


	public String getObservaciones() {
		return observaciones;
	}


	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}


	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}


	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}


	public LocalDateTime getFechaActualizacion() {
		return fechaActualizacion;
	}


	public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}
	
	

	public Long getSucursalId() {
		return sucursalId;
	}

	public void setSucursalId(Long sucursalId) {
		this.sucursalId = sucursalId;
	}

	public String getSucursal() {
		return sucursal;
	}

	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	
    
    
}
