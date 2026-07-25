package com.kinesiovitality.evaluacion.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.kinesiovitality.common.enums.EstadoRegistro;
import com.kinesiovitality.fisioterapeuta.model.Fisioterapeuta;
import com.kinesiovitality.paciente.model.Paciente;

import jakarta.persistence.*;

@Entity
@Table(name = "evaluaciones")
public class Evaluacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false, length = 20)
    private String codigoEvaluacion;

    // Relaciones
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fisioterapeuta_id", nullable = false)
    private Fisioterapeuta fisioterapeuta;

    // Información general
    private LocalDate fechaEvaluacion;

    @Column(length = 1000)
    private String motivoConsulta;

    @Column(length = 1000)
    private String antecedentes;

    // Evaluación clínica
    private Integer escalaDolorEva;

    @Column(length = 1000)
    private String diagnosticoFisioterapeutico;

    @Column(length = 1000)
    private String objetivosTratamiento;

    // Exploración física
    @Column(length = 1000)
    private String inspeccion;

    @Column(length = 1000)
    private String palpacion;

    @Column(length = 1000)
    private String rangoMovimiento;

    @Column(length = 1000)
    private String fuerzaMuscular;

    @Column(length = 1000)
    private String pruebasFuncionales;

    // Plan terapéutico
    private Integer sesionesRecomendadas;

    private Integer frecuenciaSemanal;

    @Column(length = 1000)
    private String tratamientoSugerido;

    @Column(length = 1000)
    private String observaciones;

    @Enumerated(EnumType.STRING)
    private EstadoRegistro estado;

    private LocalDateTime fechaCreacion;

    private LocalDateTime fechaActualizacion;
    
    
    

    public Evaluacion() {
    }



	public Evaluacion(Long id, String codigoEvaluacion, Paciente paciente, Fisioterapeuta fisioterapeuta,
			LocalDate fechaEvaluacion, String motivoConsulta, String antecedentes, Integer escalaDolorEva,
			String diagnosticoFisioterapeutico, String objetivosTratamiento, String inspeccion, String palpacion,
			String rangoMovimiento, String fuerzaMuscular, String pruebasFuncionales, Integer sesionesRecomendadas,
			Integer frecuenciaSemanal, String tratamientoSugerido, String observaciones, EstadoRegistro estado,
			LocalDateTime fechaCreacion, LocalDateTime fechaActualizacion) {
		super();
		this.id = id;
		this.codigoEvaluacion = codigoEvaluacion;
		this.paciente = paciente;
		this.fisioterapeuta = fisioterapeuta;
		this.fechaEvaluacion = fechaEvaluacion;
		this.motivoConsulta = motivoConsulta;
		this.antecedentes = antecedentes;
		this.escalaDolorEva = escalaDolorEva;
		this.diagnosticoFisioterapeutico = diagnosticoFisioterapeutico;
		this.objetivosTratamiento = objetivosTratamiento;
		this.inspeccion = inspeccion;
		this.palpacion = palpacion;
		this.rangoMovimiento = rangoMovimiento;
		this.fuerzaMuscular = fuerzaMuscular;
		this.pruebasFuncionales = pruebasFuncionales;
		this.sesionesRecomendadas = sesionesRecomendadas;
		this.frecuenciaSemanal = frecuenciaSemanal;
		this.tratamientoSugerido = tratamientoSugerido;
		this.observaciones = observaciones;
		this.estado = estado;
		this.fechaCreacion = fechaCreacion;
		this.fechaActualizacion = fechaActualizacion;
	}







	@PrePersist
    public void prePersist() {

        estado = EstadoRegistro.ACTIVO;

        fechaCreacion = LocalDateTime.now();
        fechaActualizacion = LocalDateTime.now();

        if (fechaEvaluacion == null) {
            fechaEvaluacion = LocalDate.now();
        }
    }

    @PreUpdate
    public void preUpdate() {

        fechaActualizacion = LocalDateTime.now();

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
