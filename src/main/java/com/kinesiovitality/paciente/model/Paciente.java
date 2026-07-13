package com.kinesiovitality.paciente.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.kinesiovitality.common.enums.EstadoRegistro;
import com.kinesiovitality.common.enums.Sexo;

import jakarta.persistence.*;

@Entity
@Table(name = "pacientes")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombres;

    @Column(nullable = false)
    private String apellidos;

    @Column(unique = true, nullable = false, length = 10)
    private String cedula;

    private LocalDate fechaNacimiento;
    
    @Enumerated(EnumType.STRING)
    private Sexo sexo;
    
    @Column(length = 10)
    private String celular;

    private String correo;

    private String direccion;

    private String ciudad;

    private String tipoSangre;

    @Column(length = 1000)
    private String alergias;

    @Column(length = 1000)
    private String enfermedades;

    @Column(length = 1000)
    private String observaciones;

    private String contactoEmergencia;

    private String parentescoContacto;

    private String telefonoContacto;
    
    @Enumerated(EnumType.STRING)
    private EstadoRegistro estado;

    private LocalDateTime fechaCreacion;

    private LocalDateTime fechaActualizacion;

    
    @PrePersist
    public void prePersist() {
        this.estado = EstadoRegistro.ACTIVO;
        this.fechaCreacion = LocalDateTime.now();
        this.fechaActualizacion = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.fechaActualizacion = LocalDateTime.now();
    }
    
    //constructores
    
    public Paciente() {
    }

	public Paciente(Long id, String nombres, String apellidos, String cedula, LocalDate fechaNacimiento, Sexo sexo,
			String celular, String correo, String direccion, String ciudad, String tipoSangre, String alergias,
			String enfermedades, String observaciones, String contactoEmergencia, String parentescoContacto,
			String telefonoContacto, EstadoRegistro estado, LocalDateTime fechaCreacion,
			LocalDateTime fechaActualizacion) {
		super();
		this.id = id;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.cedula = cedula;
		this.fechaNacimiento = fechaNacimiento;
		this.sexo = sexo;
		this.celular = celular;
		this.correo = correo;
		this.direccion = direccion;
		this.ciudad = ciudad;
		this.tipoSangre = tipoSangre;
		this.alergias = alergias;
		this.enfermedades = enfermedades;
		this.observaciones = observaciones;
		this.contactoEmergencia = contactoEmergencia;
		this.parentescoContacto = parentescoContacto;
		this.telefonoContacto = telefonoContacto;
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

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getTipoSangre() {
		return tipoSangre;
	}

	public void setTipoSangre(String tipoSangre) {
		this.tipoSangre = tipoSangre;
	}

	public String getAlergias() {
		return alergias;
	}

	public void setAlergias(String alergias) {
		this.alergias = alergias;
	}

	public String getEnfermedades() {
		return enfermedades;
	}

	public void setEnfermedades(String enfermedades) {
		this.enfermedades = enfermedades;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getContactoEmergencia() {
		return contactoEmergencia;
	}

	public void setContactoEmergencia(String contactoEmergencia) {
		this.contactoEmergencia = contactoEmergencia;
	}

	public String getParentescoContacto() {
		return parentescoContacto;
	}

	public void setParentescoContacto(String parentescoContacto) {
		this.parentescoContacto = parentescoContacto;
	}

	public String getTelefonoContacto() {
		return telefonoContacto;
	}

	public void setTelefonoContacto(String telefonoContacto) {
		this.telefonoContacto = telefonoContacto;
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
