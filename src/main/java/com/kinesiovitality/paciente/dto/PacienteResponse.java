package com.kinesiovitality.paciente.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.kinesiovitality.common.enums.EstadoRegistro;
import com.kinesiovitality.common.enums.Sexo;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
	    name = "PacienteResponse",
	    description = "Información completa de un paciente."
	)
public class PacienteResponse {
	
	@Schema(example = "15")
    private Long id;
	@Schema(example = "Carlos Andrés")
    private String nombres;
	@Schema(example = "Pérez López")
    private String apellidos;
	@Schema(example = "1723456789")
    private String cedula;
    private LocalDate fechaNacimiento;
    private Sexo sexo;
    @Schema(example = "0998765432")
    private String celular;
    @Schema(example = "carlos@gmail.com")
    private String correo;
    @Schema(example = "true")
    private EstadoRegistro estado;
    @Schema(
    	    description = "Fecha de registro del paciente"
    	)
    private LocalDateTime fechaCreacion;

    public PacienteResponse() {
    }
    
    

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

    
    
}
