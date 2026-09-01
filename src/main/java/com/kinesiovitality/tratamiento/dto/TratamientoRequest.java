package com.kinesiovitality.tratamiento.dto;

import java.time.LocalDate;

import java.util.List;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class TratamientoRequest {

    @NotNull(message = "El paciente es obligatorio.")
    private Long pacienteId;

    @NotNull(message = "El fisioterapeuta es obligatorio.")
    private Long fisioterapeutaId;

    @NotNull(message = "La evaluación es obligatoria.")
    private Long evaluacionId;

    @NotNull(message = "La fecha de inicio es obligatoria.")
    private LocalDate fechaInicio;

    private LocalDate fechaEstimadaAlta;

    @NotBlank(message = "El objetivo general es obligatorio.")
    @Size(max = 1000)
    private String objetivoGeneral;

    @Size(max = 2000)
    private String objetivosEspecificos;

    @NotBlank(message = "El diagnóstico es obligatorio.")
    @Size(max = 1000)
    private String diagnostico;

    @NotBlank(message = "El tratamiento propuesto es obligatorio.")
    @Size(max = 2000)
    private String tratamientoPropuesto;

    @NotEmpty(message = "Debe registrar al menos una técnica.")
    private List<String> tecnicas;

    @NotNull(message = "Debe indicar las sesiones planificadas.")
    @Min(value = 1, message = "Debe existir al menos una sesión.")
    private Integer sesionesPlanificadas;

    @NotNull(message = "Debe indicar la frecuencia semanal.")
    @Min(value = 1, message = "La frecuencia debe ser mayor a cero.")
    private Integer frecuenciaSemanal;

    @Size(max = 2000)
    private String observacionesIniciales;

    public TratamientoRequest() {
    }

    // Getters y Setters

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

    public Integer getFrecuenciaSemanal() {
        return frecuenciaSemanal;
    }

    public void setFrecuenciaSemanal(Integer frecuenciaSemanal) {
        this.frecuenciaSemanal = frecuenciaSemanal;
    }

    public String getObservacionesIniciales() {
        return observacionesIniciales;
    }

    public void setObservacionesIniciales(String observacionesIniciales) {
        this.observacionesIniciales = observacionesIniciales;
    }
}
