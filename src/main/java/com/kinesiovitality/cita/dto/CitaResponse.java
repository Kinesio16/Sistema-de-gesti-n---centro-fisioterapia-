package com.kinesiovitality.cita.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.kinesiovitality.common.enums.EstadoCita;

public class CitaResponse {

    private Long id;

    private Long pacienteId;
    private String pacienteNombre;

    private Long fisioterapeutaId;
    private String fisioterapeutaNombre;

    private LocalDate fecha;

    private LocalTime horaInicio;

    private LocalTime horaFin;

    private Integer duracionMinutos;

    private String tipoTerapia;

    private String motivoConsulta;

    private String observaciones;

    private EstadoCita estado;

    private LocalDateTime fechaCreacion;

    private LocalDateTime fechaActualizacion;
    
    

    public CitaResponse() {
    }

    
    
    
    // Getters y Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPacienteId() {
		return pacienteId;
	}

	public void setPacienteId(Long pacienteId) {
		this.pacienteId = pacienteId;
	}

	public String getPacienteNombre() {
		return pacienteNombre;
	}

	public void setPacienteNombre(String pacienteNombre) {
		this.pacienteNombre = pacienteNombre;
	}

	public Long getFisioterapeutaId() {
		return fisioterapeutaId;
	}

	public void setFisioterapeutaId(Long fisioterapeutaId) {
		this.fisioterapeutaId = fisioterapeutaId;
	}

	public String getFisioterapeutaNombre() {
		return fisioterapeutaNombre;
	}

	public void setFisioterapeutaNombre(String fisioterapeutaNombre) {
		this.fisioterapeutaNombre = fisioterapeutaNombre;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public LocalTime getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(LocalTime horaInicio) {
		this.horaInicio = horaInicio;
	}

	public LocalTime getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(LocalTime horaFin) {
		this.horaFin = horaFin;
	}

	public Integer getDuracionMinutos() {
		return duracionMinutos;
	}

	public void setDuracionMinutos(Integer duracionMinutos) {
		this.duracionMinutos = duracionMinutos;
	}

	public String getTipoTerapia() {
		return tipoTerapia;
	}

	public void setTipoTerapia(String tipoTerapia) {
		this.tipoTerapia = tipoTerapia;
	}

	public String getMotivoConsulta() {
		return motivoConsulta;
	}

	public void setMotivoConsulta(String motivoConsulta) {
		this.motivoConsulta = motivoConsulta;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public EstadoCita getEstado() {
		return estado;
	}

	public void setEstado(EstadoCita estado) {
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