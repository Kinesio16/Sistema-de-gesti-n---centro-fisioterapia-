package com.kinesiovitality.fisioterapeuta.dto;

import java.time.LocalDateTime;

import com.kinesiovitality.common.enums.EstadoRegistro;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
	    name = "FisioterapeutaResponse",
	    description = "Información completa del fisioterapeuta."
	)
public class FisioterapeutaResponse {
	
	@Schema(example = "5")
	private Long id;

	@Schema(example = "Andrea")
	private String nombres;

	@Schema(example = "Gómez Pérez")
	private String apellidos;

	@Schema(example = "1724567890")
	private String cedula;

	@Schema(example = "0991234567")
	private String celular;

	@Schema(example = "andrea@kinesiovitality.com")
	private String correo;

	@Schema(example = "Traumatología Deportiva")
	private String especialidad;

	@Schema(example = "MSP-45879")
	private String numeroLicencia;

	private EstadoRegistro estado;

	@Schema(description = "Fecha de creación del registro")
	private LocalDateTime fechaCreacion;

	private LocalDateTime fechaActualizacion;

	
	
	
	public FisioterapeutaResponse() {
		super();
	}
	
	

	public FisioterapeutaResponse(Long id, String nombres, String apellidos, String cedula, String celular,
			String correo, String especialidad, String numeroLicencia, EstadoRegistro estado,
			LocalDateTime fechaCreacion, LocalDateTime fechaActualizacion) {
		super();
		this.id = id;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.cedula = cedula;
		this.celular = celular;
		this.correo = correo;
		this.especialidad = especialidad;
		this.numeroLicencia = numeroLicencia;
		this.estado = estado;
		this.fechaCreacion = fechaCreacion;
		this.fechaActualizacion = fechaActualizacion;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	public String getNumeroLicencia() {
		return numeroLicencia;
	}

	public void setNumeroLicencia(String numeroLicencia) {
		this.numeroLicencia = numeroLicencia;
	}

	public EstadoRegistro getEstado() {
		return estado;
	}

	public void setEstado(EstadoRegistro estado) {
		this.estado = estado;
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

	

}
