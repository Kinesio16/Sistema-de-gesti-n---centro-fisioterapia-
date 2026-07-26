package com.kinesiovitality.sesion.model;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.kinesiovitality.common.enums.EstadoSesion;
import com.kinesiovitality.tratamiento.model.Tratamiento;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "sesiones")
public class Sesion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 30)
    private String codigoSesion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tratamiento_id", nullable = false)
    private Tratamiento tratamiento;

    @Column(nullable = false)
    private LocalDate fechaSesion;

    @Column(nullable = false)
    private LocalTime horaInicio;

    @Column(nullable = false)
    private LocalTime horaFin;

    @Column(nullable = false)
    private Integer duracionMinutos;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoSesion estado;

    @Column(length = 1000)
    private String evolucionClinica;

    @Column(length = 1000)
    private String observaciones;

    @Column(length = 1000)
    private String proximaSesionObservacion;

    private Integer evaAntes;

    private Integer evaDespues;

    @ElementCollection
    @CollectionTable(
            name = "sesion_tecnicas_aplicadas",
            joinColumns = @JoinColumn(name = "sesion_id")
    )
    @Column(name = "tecnica")
    private List<String> tecnicasAplicadas = new ArrayList<>();

    private LocalDate proximaSesion;

    private LocalDateTime fechaCreacion;

    private LocalDateTime fechaActualizacion;

    public Sesion() {
    }
    
    

    public Sesion(Long id, String codigoSesion, Tratamiento tratamiento, LocalDate fechaSesion, LocalTime horaInicio,
			LocalTime horaFin, Integer duracionMinutos, EstadoSesion estado, String evolucionClinica,
			String observaciones, String proximaSesionObservacion, Integer evaAntes, Integer evaDespues,
			List<String> tecnicasAplicadas, LocalDate proximaSesion, LocalDateTime fechaCreacion,
			LocalDateTime fechaActualizacion) {
		super();
		this.id = id;
		this.codigoSesion = codigoSesion;
		this.tratamiento = tratamiento;
		this.fechaSesion = fechaSesion;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.duracionMinutos = duracionMinutos;
		this.estado = estado;
		this.evolucionClinica = evolucionClinica;
		this.observaciones = observaciones;
		this.proximaSesionObservacion = proximaSesionObservacion;
		this.evaAntes = evaAntes;
		this.evaDespues = evaDespues;
		this.tecnicasAplicadas = tecnicasAplicadas;
		this.proximaSesion = proximaSesion;
		this.fechaCreacion = fechaCreacion;
		this.fechaActualizacion = fechaActualizacion;
	}



	@PrePersist
    public void prePersist() {
        this.estado = this.estado == null ? EstadoSesion.PROGRAMADA : this.estado;
        this.fechaCreacion = LocalDateTime.now();
        this.fechaActualizacion = LocalDateTime.now();
        calcularDuracion();
    }

    @PreUpdate
    public void preUpdate() {
        this.fechaActualizacion = LocalDateTime.now();
        calcularDuracion();
    }

    private void calcularDuracion() {
        if (this.horaInicio != null && this.horaFin != null) {
            this.duracionMinutos = (int) Duration.between(this.horaInicio, this.horaFin).toMinutes();
        }
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

    public Tratamiento getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(Tratamiento tratamiento) {
        this.tratamiento = tratamiento;
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
