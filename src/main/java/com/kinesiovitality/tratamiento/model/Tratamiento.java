package com.kinesiovitality.tratamiento.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.kinesiovitality.common.enums.EstadoTratamiento;
import com.kinesiovitality.evaluacion.model.Evaluacion;
import com.kinesiovitality.fisioterapeuta.model.Fisioterapeuta;
import com.kinesiovitality.paciente.model.Paciente;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "tratamientos")
public class Tratamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String codigoTratamiento;

    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "fisioterapeuta_id", nullable = false)
    private Fisioterapeuta fisioterapeuta;

    @ManyToOne
    @JoinColumn(name = "evaluacion_id", nullable = false)
    private Evaluacion evaluacion;

    @Column(nullable = false)
    private LocalDate fechaInicio;

    private LocalDate fechaEstimadaAlta;

    private LocalDate fechaAlta;

    @Column(columnDefinition = "TEXT")
    private String objetivoGeneral;

    @Column(columnDefinition = "TEXT")
    private String objetivosEspecificos;

    @Column(columnDefinition = "TEXT")
    private String diagnostico;

    @Column(columnDefinition = "TEXT")
    private String tratamientoPropuesto;

    @ElementCollection
    @CollectionTable(
            name = "tratamiento_tecnicas",
            joinColumns = @JoinColumn(name = "tratamiento_id")
    )
    @Column(name = "tecnica")
    private List<String> tecnicas = new ArrayList<>();

    private Integer sesionesPlanificadas;

    private Integer sesionesRealizadas;


    private Integer frecuenciaSemanal;


    @Enumerated(EnumType.STRING)
    private EstadoTratamiento estado;

    @Column(columnDefinition = "TEXT")
    private String observacionesIniciales;

    @Column(columnDefinition = "TEXT")
    private String observacionesFinales;

    private LocalDateTime fechaCreacion;

    private LocalDateTime fechaActualizacion;

    @PrePersist
    public void prePersist() {

        this.estado = EstadoTratamiento.ACTIVO;

        this.fechaCreacion = LocalDateTime.now();
        this.fechaActualizacion = LocalDateTime.now();

        if (this.sesionesRealizadas == null) {
            this.sesionesRealizadas = 0;
        }

    }

    @PreUpdate
    public void preUpdate() {
        this.fechaActualizacion = LocalDateTime.now();
    }
    
    // metodos
    
    public Integer getSesionesPendientes() {

        if (sesionesPlanificadas == null || sesionesRealizadas == null) {
            return 0;
        }

        return sesionesPlanificadas - sesionesRealizadas;
    }
    
    public Double getPorcentajeAvance() {

        if (sesionesPlanificadas == null || sesionesPlanificadas == 0) {
            return 0.0;
        }

        return (sesionesRealizadas * 100.0) / sesionesPlanificadas;
    }
    
    
 // Constructores
    public Tratamiento() {
    }

	

	
	public Tratamiento(Long id, String codigoTratamiento, Paciente paciente, Fisioterapeuta fisioterapeuta,
			Evaluacion evaluacion, LocalDate fechaInicio, LocalDate fechaEstimadaAlta, LocalDate fechaAlta,
			String objetivoGeneral, String objetivosEspecificos, String diagnostico, String tratamientoPropuesto,
			List<String> tecnicas, Integer sesionesPlanificadas, Integer sesionesRealizadas, Integer frecuenciaSemanal,
			EstadoTratamiento estado, String observacionesIniciales, String observacionesFinales,
			LocalDateTime fechaCreacion, LocalDateTime fechaActualizacion) {
		super();
		this.id = id;
		this.codigoTratamiento = codigoTratamiento;
		this.paciente = paciente;
		this.fisioterapeuta = fisioterapeuta;
		this.evaluacion = evaluacion;
		this.fechaInicio = fechaInicio;
		this.fechaEstimadaAlta = fechaEstimadaAlta;
		this.fechaAlta = fechaAlta;
		this.objetivoGeneral = objetivoGeneral;
		this.objetivosEspecificos = objetivosEspecificos;
		this.diagnostico = diagnostico;
		this.tratamientoPropuesto = tratamientoPropuesto;
		this.tecnicas = tecnicas;
		this.sesionesPlanificadas = sesionesPlanificadas;
		this.sesionesRealizadas = sesionesRealizadas;
		this.frecuenciaSemanal = frecuenciaSemanal;
		this.estado = estado;
		this.observacionesIniciales = observacionesIniciales;
		this.observacionesFinales = observacionesFinales;
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

	public String getCodigoTratamiento() {
		return codigoTratamiento;
	}

	public void setCodigoTratamiento(String codigoTratamiento) {
		this.codigoTratamiento = codigoTratamiento;
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

	public Evaluacion getEvaluacion() {
		return evaluacion;
	}

	public void setEvaluacion(Evaluacion evaluacion) {
		this.evaluacion = evaluacion;
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


	public Integer getFrecuenciaSemanal() {
		return frecuenciaSemanal;
	}

	public void setFrecuenciaSemanal(Integer frecuenciaSemanal) {
		this.frecuenciaSemanal = frecuenciaSemanal;
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
