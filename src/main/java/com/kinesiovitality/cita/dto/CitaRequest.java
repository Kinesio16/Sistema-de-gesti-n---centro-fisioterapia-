package com.kinesiovitality.cita.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CitaRequest {

    @NotNull(message = "El paciente es obligatorio.")
    private Long pacienteId;

    @NotNull(message = "El fisioterapeuta es obligatorio.")
    private Long fisioterapeutaId;

    @NotNull(message = "La fecha es obligatoria.")
    @FutureOrPresent(message = "La fecha no puede ser anterior al día actual.")
    private LocalDate fecha;

    @NotNull(message = "La hora de inicio es obligatoria.")
    private LocalTime horaInicio;

    @NotNull(message = "La hora de fin es obligatoria.")
    private LocalTime horaFin;

    @NotBlank(message = "El tipo de terapia es obligatorio.")
    @Size(max = 100)
    private String tipoTerapia;

    @NotBlank(message = "El motivo de consulta es obligatorio.")
    @Size(max = 500)
    private String motivoConsulta;

    @Size(max = 1000)
    private String observaciones;

    public CitaRequest() {
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
}
