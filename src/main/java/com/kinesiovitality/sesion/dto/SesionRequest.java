package com.kinesiovitality.sesion.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class SesionRequest {

    @NotNull(message = "El tratamiento es obligatorio.")
    private Long tratamientoId;

    @NotNull(message = "La fecha de la sesión es obligatoria.")
    private LocalDate fechaSesion;

    @NotNull(message = "La hora de inicio es obligatoria.")
    private LocalTime horaInicio;

    @NotNull(message = "La hora de fin es obligatoria.")
    private LocalTime horaFin;

    @NotBlank(message = "La evolución clínica es obligatoria.")
    @Size(max = 1000, message = "La evolución clínica no puede superar los 1000 caracteres.")
    private String evolucionClinica;

    @Size(max = 1000, message = "Las observaciones no pueden superar los 1000 caracteres.")
    private String observaciones;

    @Size(max = 1000, message = "La observación de la próxima sesión no puede superar los 1000 caracteres.")
    private String proximaSesionObservacion;

    private Integer evaAntes;

    private Integer evaDespues;

    @NotEmpty(message = "Debe registrar al menos una técnica aplicada.")
    private List<String> tecnicasAplicadas;

    private LocalDate proximaSesion;

    public SesionRequest() {
    }

    public Long getTratamientoId() {
        return tratamientoId;
    }

    public void setTratamientoId(Long tratamientoId) {
        this.tratamientoId = tratamientoId;
    }

    public LocalDate getFechaSesion() {
        return fechaSesion;
    }

    public void setFechaSesion(LocalDate fechaSesion) {
        this.fechaSesion = fechaSesion;
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

    public String getEvolucionClinica() {
        return evolucionClinica;
    }

    public void setEvolucionClinica(String evolucionClinica) {
        this.evolucionClinica = evolucionClinica;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getProximaSesionObservacion() {
        return proximaSesionObservacion;
    }

    public void setProximaSesionObservacion(String proximaSesionObservacion) {
        this.proximaSesionObservacion = proximaSesionObservacion;
    }

    public Integer getEvaAntes() {
        return evaAntes;
    }

    public void setEvaAntes(Integer evaAntes) {
        this.evaAntes = evaAntes;
    }

    public Integer getEvaDespues() {
        return evaDespues;
    }

    public void setEvaDespues(Integer evaDespues) {
        this.evaDespues = evaDespues;
    }

    public List<String> getTecnicasAplicadas() {
        return tecnicasAplicadas;
    }

    public void setTecnicasAplicadas(List<String> tecnicasAplicadas) {
        this.tecnicasAplicadas = tecnicasAplicadas;
    }

    public LocalDate getProximaSesion() {
        return proximaSesion;
    }

    public void setProximaSesion(LocalDate proximaSesion) {
        this.proximaSesion = proximaSesion;
    }
}
