package com.kinesiovitality.evaluacion.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class EvaluacionRequest {

    @NotNull
    private Long pacienteId;

    @NotNull
    private Long fisioterapeutaId;

    private LocalDate fechaEvaluacion;

    private String motivoConsulta;

    private String antecedentes;

    @Min(0)
    @Max(10)
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
    
    

    public EvaluacionRequest() {
    }
    
 // Generar getters y setters

	public Long getPacienteId() {
		return pacienteId;
	}

	public void setPacienteId(Long pacienteId) {
		this.pacienteId = pacienteId;
	}

	public Long getFisioterapeutaId() {
		return fisioterapeutaId;
	}

	public void setFisioterapeutaId(Long fisioterapeutaId) {
		this.fisioterapeutaId = fisioterapeutaId;
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

    
    
}
