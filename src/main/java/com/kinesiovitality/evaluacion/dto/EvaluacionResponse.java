package com.kinesiovitality.evaluacion.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.kinesiovitality.common.enums.EstadoRegistro;

public class EvaluacionResponse {

    private Long id;
    
    private String codigoEvaluacion;

    private Long pacienteId;

    private String pacienteNombre;

    private Long fisioterapeutaId;

    private String fisioterapeutaNombre;

    private LocalDate fechaEvaluacion;

    private String motivoConsulta;

    private String antecedentes;

    private Integer escalaDolorEva;

    private String diagnosticoFisioterapeutico;

    private String objetivosTratamiento;

    private String inspeccion;

    private String palpacion;

    private String rangoMovimiento;

    private String fuerzaMuscular;

    private String pruebasFuncionales;

    private Integer sesionesRecomendadas;

    private Integer frecuenciaSemanal;

    private String tratamientoSugerido;

    private String observaciones;

    private EstadoRegistro estado;

    private LocalDateTime fechaCreacion;

    private LocalDateTime fechaActualizacion;
    
    

    public EvaluacionResponse() {
    }


 // Generar getters y setters
    
	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getCodigoEvaluacion() {
		return codigoEvaluacion;
	}


	public void setCodigoEvaluacion(String codigoEvaluacion) {
		this.codigoEvaluacion = codigoEvaluacion;
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



	public LocalDate getFechaEvaluacion() {
		return fechaEvaluacion;
	}



	public void setFechaEvaluacion(LocalDate fechaEvaluacion) {
		this.fechaEvaluacion = fechaEvaluacion;
	}



	public String getMotivoConsulta() {
		return motivoConsulta;
	}



	public void setMotivoConsulta(String motivoConsulta) {
		this.motivoConsulta = motivoConsulta;
	}



	public String getAntecedentes() {
		return antecedentes;
	}



	public void setAntecedentes(String antecedentes) {
		this.antecedentes = antecedentes;
	}



	public Integer getEscalaDolorEva() {
		return escalaDolorEva;
	}



	public void setEscalaDolorEva(Integer escalaDolorEva) {
		this.escalaDolorEva = escalaDolorEva;
	}



	public String getDiagnosticoFisioterapeutico() {
		return diagnosticoFisioterapeutico;
	}



	public void setDiagnosticoFisioterapeutico(String diagnosticoFisioterapeutico) {
		this.diagnosticoFisioterapeutico = diagnosticoFisioterapeutico;
	}



	public String getObjetivosTratamiento() {
		return objetivosTratamiento;
	}



	public void setObjetivosTratamiento(String objetivosTratamiento) {
		this.objetivosTratamiento = objetivosTratamiento;
	}



	public String getInspeccion() {
		return inspeccion;
	}



	public void setInspeccion(String inspeccion) {
		this.inspeccion = inspeccion;
	}



	public String getPalpacion() {
		return palpacion;
	}



	public void setPalpacion(String palpacion) {
		this.palpacion = palpacion;
	}



	public String getRangoMovimiento() {
		return rangoMovimiento;
	}



	public void setRangoMovimiento(String rangoMovimiento) {
		this.rangoMovimiento = rangoMovimiento;
	}



	public String getFuerzaMuscular() {
		return fuerzaMuscular;
	}



	public void setFuerzaMuscular(String fuerzaMuscular) {
		this.fuerzaMuscular = fuerzaMuscular;
	}



	public String getPruebasFuncionales() {
		return pruebasFuncionales;
	}



	public void setPruebasFuncionales(String pruebasFuncionales) {
		this.pruebasFuncionales = pruebasFuncionales;
	}



	public Integer getSesionesRecomendadas() {
		return sesionesRecomendadas;
	}



	public void setSesionesRecomendadas(Integer sesionesRecomendadas) {
		this.sesionesRecomendadas = sesionesRecomendadas;
	}



	public Integer getFrecuenciaSemanal() {
		return frecuenciaSemanal;
	}



	public void setFrecuenciaSemanal(Integer frecuenciaSemanal) {
		this.frecuenciaSemanal = frecuenciaSemanal;
	}



	public String getTratamientoSugerido() {
		return tratamientoSugerido;
	}



	public void setTratamientoSugerido(String tratamientoSugerido) {
		this.tratamientoSugerido = tratamientoSugerido;
	}



	public String getObservaciones() {
		return observaciones;
	}



	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
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
