package com.kinesiovitality.cita.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.kinesiovitality.common.enums.EstadoCita;
import com.kinesiovitality.fisioterapeuta.model.Fisioterapeuta;
import com.kinesiovitality.paciente.model.Paciente;

import jakarta.persistence.*;

@Entity
@Table(name = "citas")
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relación con Paciente
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    // Relación con Fisioterapeuta
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fisioterapeuta_id", nullable = false)
    private Fisioterapeuta fisioterapeuta;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false)
    private LocalTime horaInicio;

    @Column(nullable = false)
    private LocalTime horaFin;

    @Column(nullable = false, length = 100)
    private String tipoTerapia;

    @Column(nullable = false)
    private Integer duracionMinutos;

    @Column(nullable = false, length = 500)
    private String motivoConsulta;

    @Column(length = 1000)
    private String observaciones;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoCita estado;

    private LocalDateTime fechaCreacion;

    private LocalDateTime fechaActualizacion;


    @PrePersist
    public void prePersist() {

        this.estado = EstadoCita.PENDIENTE;
        this.fechaCreacion = LocalDateTime.now();
        this.fechaActualizacion = LocalDateTime.now();

    }

    @PreUpdate
    public void preUpdate() {

        this.fechaActualizacion = LocalDateTime.now();

    }

    
 // Constructor vacío
	public Cita() {
		super();
	}

	// Constructor con todos los campos
	public Cita(Long id, Paciente paciente, Fisioterapeuta fisioterapeuta, LocalDate fecha, LocalTime horaInicio,
			LocalTime horaFin, String tipoTerapia, Integer duracionMinutos, String motivoConsulta, String observaciones,
			EstadoCita estado, LocalDateTime fechaCreacion, LocalDateTime fechaActualizacion) {
		super();
		this.id = id;
		this.paciente = paciente;
		this.fisioterapeuta = fisioterapeuta;
		this.fecha = fecha;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.tipoTerapia = tipoTerapia;
		this.duracionMinutos = duracionMinutos;
		this.motivoConsulta = motivoConsulta;
		this.observaciones = observaciones;
		this.estado = estado;
		this.fechaCreacion = fechaCreacion;
		this.fechaActualizacion = fechaActualizacion;
	}

	
	 // Getters y Setters
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Fisioterapeuta getFisioterapeuta() {
		return fisioterapeuta;
	}

	public void setFisioterapeuta(Fisioterapeuta fisioterapeuta) {
		this.fisioterapeuta = fisioterapeuta;
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

	public Integer getDuracionMinutos() {
		return duracionMinutos;
	}

	public void setDuracionMinutos(Integer duracionMinutos) {
		this.duracionMinutos = duracionMinutos;
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