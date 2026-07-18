package com.kinesiovitality.fisioterapeuta.model;

import java.time.LocalDateTime;

import com.kinesiovitality.common.enums.EstadoRegistro;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "fisioterapeutas")
public class Fisioterapeuta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String nombres;
	
	@Column(nullable = false)
	private String apellidos;
	
	@Column(unique = true, nullable = false, length = 10)
	private String cedula;
	
	@Column(length = 10)
	private String celular;
	
	@Column(unique = true)
	private String correo;
	
	private String especialidad;
	
	@Column(unique = true)
	private String numeroLicencia;
	
	@Enumerated(EnumType.STRING)
	private EstadoRegistro estado;
	
	private LocalDateTime fechaCreacion;
	
	private LocalDateTime fechaActualizacion;
	
	
	@PrePersist
	public void prePersist() {
		this.estado = EstadoRegistro.ACTIVO;
		this.fechaCreacion = LocalDateTime.now();
		this.fechaActualizacion = LocalDateTime.now();
		
	}
	
	@PreUpdate
	public void preUpdate() {
		this.fechaActualizacion = LocalDateTime.now();
		
	}
	
	//constructores
	

	public Fisioterapeuta(Long id, String nombres, String apellidos, String cedula, String celular, String correo,
			String especialidad, String numeroLicencia, EstadoRegistro estado, LocalDateTime fechaCreacion,
			LocalDateTime fechaActualizacion) {
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

	public Fisioterapeuta() {
		super();
	}

	
	//getters and setters
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
