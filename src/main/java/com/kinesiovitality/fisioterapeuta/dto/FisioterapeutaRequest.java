package com.kinesiovitality.fisioterapeuta.dto;

import jakarta.validation.constraints.Email;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
	    name = "FisioterapeutaRequest",
	    description = "Información necesaria para registrar o actualizar un fisioterapeuta."
	)
public class FisioterapeutaRequest {
	
	@NotBlank(message = "Los nombres son obligatorios")
	@Size(max = 100, message = "Los nombres no pueden superar los 100 caracteres")
	@Schema(
		    description = "Nombres del fisioterapeuta",
		    example = "Andrea"
		)
	private String nombres;
	
	@NotBlank(message = "Los apellidos son obligatorios")
	@Size(max = 100, message = "Los apellidos no pueden superar los 100 caracteres")
	@Schema(
		    description = "Apellidos del fisioterapeuta",
		    example = "Gómez Pérez"
		)
	private String apellidos;
	
	@NotBlank(message = "La cédula es obligatoria")
	@Size(min = 10, max = 10, message = "La cédula debe tener 10 dígitos")
	@Pattern(regexp = "\\d{10}", message = "La cédula solo debe contener números")
	@Schema(
		    description = "Número de identificación",
		    example = "1724567890"
		)
	private String cedula;
	
	@NotBlank(message = "El celular es obligatorio")
    @Pattern(regexp = "09\\d{8}", message = "El celular debe tener el formato ecuatoriano (09XXXXXXXX)")
	@Schema(
		    description = "Número telefónico",
		    example = "0991234567"
		)
    private String celular;
	
	@NotBlank(message="El correo es obligatorio")
	@Email(message = "El correo no tiene un formato válido")
	@Schema(
		    description = "Correo institucional",
		    example = "andrea@kinesiovitality.com"
		)
	private String correo;
	
	@Schema(
		    description = "Especialidad del fisioterapeuta",
		    example = "Traumatología Deportiva"
		)
	private String especialidad;
	
	@NotBlank(message="La especialidad es obligatoria")
	@Schema(
		    description = "Número de registro profesional",
		    example = "MSP-45879"
		)
	private String numeroLicencia;
	
	
	
	public FisioterapeutaRequest() {
		super();
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


	public String getEspecialidad() {
		return especialidad;
	}


	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}


	public String getNumeroLicencia() {
		return numeroLicencia;
	}


	public void setNumeroLicencia(String numeroLicencia) {
		this.numeroLicencia = numeroLicencia;
	}
	
	
	
	
	
}
