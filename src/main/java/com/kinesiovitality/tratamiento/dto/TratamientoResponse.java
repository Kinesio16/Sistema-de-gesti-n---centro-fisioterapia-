package com.kinesiovitality.tratamiento.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.kinesiovitality.common.enums.EstadoTratamiento;

public class TratamientoResponse {

    private Long id;

    private String codigoTratamiento;

    private Long pacienteId;
    private String nombrePaciente;

    private Long fisioterapeutaId;
    private String nombreFisioterapeuta;

    private Long evaluacionId;

    private LocalDate fechaInicio;

    private LocalDate fechaEstimadaAlta;

    private LocalDate fechaAlta;

    private String objetivoGeneral;

    private String objetivosEspecificos;

    private String diagnostico;

    private String tratamientoPropuesto;

    private List<String> tecnicas;

    private Integer sesionesPlanificadas;

    private Integer sesionesRealizadas;

    private Integer sesionesPendientes;

    private Integer frecuenciaSemanal;

    private Double porcentajeAvance;

    private EstadoTratamiento estado;

    private String observacionesIniciales;

    private String observacionesFinales;

    private LocalDateTime fechaCreacion;

    private LocalDateTime fechaActualizacion;
    
    

    public TratamientoResponse() {
    }

    
    
 // Getters y Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigoTratamiento() {
		return codigoTratamiento;
	}

	public void setCodigoTratamiento(String codigoTratamiento) {
		this.codigoTratamiento = codigoTratamiento;
	}

	public Long getPacienteId() {
		return pacienteId;
	}

	public void setPacienteId(Long pacienteId) {
		this.pacienteId = pacienteId;
	}

	public String getNombrePaciente() {
		return nombrePaciente;
	}

	public void setNombrePaciente(String nombrePaciente) {
		this.nombrePaciente = nombrePaciente;
	}

	public Long getFisioterapeutaId() {
		return fisioterapeutaId;
	}

	public void setFisioterapeutaId(Long fisioterapeutaId) {
		this.fisioterapeutaId = fisioterapeutaId;
	}

	public String getNombreFisioterapeuta() {
		return nombreFisioterapeuta;
	}

	public void setNombreFisioterapeuta(String nombreFisioterapeuta) {
		this.nombreFisioterapeuta = nombreFisioterapeuta;
	}

	public Long getEvaluacionId() {
		return evaluacionId;
	}

	public void setEvaluacionId(Long evaluacionId) {
		this.evaluacionId = evaluacionId;
	}

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public LocalDate getFechaEstimadaAlta() {
		return fechaEstimadaAlta;
	}

	public void setFechaEstimadaAlta(LocalDate fechaEstimadaAlta) {
		this.fechaEstimadaAlta = fechaEstimadaAlta;
	}

	public LocalDate getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(LocalDate fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public String getObjetivoGeneral() {
		return objetivoGeneral;
	}

	public void setObjetivoGeneral(String objetivoGeneral) {
		this.objetivoGeneral = objetivoGeneral;
	}

	public String getObjetivosEspecificos() {
		return objetivosEspecificos;
	}

	public void setObjetivosEspecificos(String objetivosEspecificos) {
		this.objetivosEspecificos = objetivosEspecificos;
	}

	public String getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}

	public String getTratamientoPropuesto() {
		return tratamientoPropuesto;
	}

	public void setTratamientoPropuesto(String tratamientoPropuesto) {
		this.tratamientoPropuesto = tratamientoPropuesto;
	}

	public List<String> getTecnicas() {
		return tecnicas;
	}

	public void setTecnicas(List<String> tecnicas) {
		this.tecnicas = tecnicas;
	}

	public Integer getSesionesPlanificadas() {
		return sesionesPlanificadas;
	}

	public void setSesionesPlanificadas(Integer sesionesPlanificadas) {
		this.sesionesPlanificadas = sesionesPlanificadas;
	}

	public Integer getSesionesRealizadas() {
		return sesionesRealizadas;
	}

	public void setSesionesRealizadas(Integer sesionesRealizadas) {
		this.sesionesRealizadas = sesionesRealizadas;
	}

	public Integer getSesionesPendientes() {
		return sesionesPendientes;
	}

	public void setSesionesPendientes(Integer sesionesPendientes) {
		this.sesionesPendientes = sesionesPendientes;
	}

	public Integer getFrecuenciaSemanal() {
		return frecuenciaSemanal;
	}

	public void setFrecuenciaSemanal(Integer frecuenciaSemanal) {
		this.frecuenciaSemanal = frecuenciaSemanal;
	}

	public Double getPorcentajeAvance() {
		return porcentajeAvance;
	}

	public void setPorcentajeAvance(Double porcentajeAvance) {
		this.porcentajeAvance = porcentajeAvance;
	}

	public EstadoTratamiento getEstado() {
		return estado;
	}

	public void setEstado(EstadoTratamiento estado) {
		this.estado = estado;
	}

	public String getObservacionesIniciales() {
		return observacionesIniciales;
	}

	public void setObservacionesIniciales(String observacionesIniciales) {
		this.observacionesIniciales = observacionesIniciales;
	}

	public String getObservacionesFinales() {
		return observacionesFinales;
	}

	public void setObservacionesFinales(String observacionesFinales) {
		this.observacionesFinales = observacionesFinales;
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