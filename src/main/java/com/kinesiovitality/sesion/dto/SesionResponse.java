package com.kinesiovitality.sesion.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import com.kinesiovitality.common.enums.EstadoSesion;

public class SesionResponse {

    private Long id;

    private String codigoSesion;

    private Long tratamientoId;
    private String codigoTratamiento;

    private Long pacienteId;
    private String nombrePaciente;

    private Long fisioterapeutaId;
    private String nombreFisioterapeuta;

    private LocalDate fechaSesion;

    private LocalTime horaInicio;

    private LocalTime horaFin;

    private Integer duracionMinutos;

    private EstadoSesion estado;

    private String evolucionClinica;

    private String observaciones;

    private String proximaSesionObservacion;

    private Integer evaAntes;

    private Integer evaDespues;

    private List<String> tecnicasAplicadas;

    private LocalDate proximaSesion;

    private LocalDateTime fechaCreacion;

    private LocalDateTime fechaActualizacion;

    public SesionResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoSesion() {
        return codigoSesion;
    }

    public void setCodigoSesion(String codigoSesion) {
        this.codigoSesion = codigoSesion;
    }

    public Long getTratamientoId() {
        return tratamientoId;
    }

    public void setTratamientoId(Long tratamientoId) {
        this.tratamientoId = tratamientoId;
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

    public Integer getDuracionMinutos() {
        return duracionMinutos;
    }

    public void setDuracionMinutos(Integer duracionMinutos) {
        this.duracionMinutos = duracionMinutos;
    }

    public EstadoSesion getEstado() {
        return estado;
    }

    public void setEstado(EstadoSesion estado) {
        this.estado = estado;
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
