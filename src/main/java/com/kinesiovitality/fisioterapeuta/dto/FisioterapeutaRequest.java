package com.kinesiovitality.fisioterapeuta.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class FisioterapeutaRequest {
	
	@NotBlank(message = "Los nombres son obligatorios")
	@Size(max = 100, message = "Los nombres no pueden superar los 100 caracteres")
	private String nombres;
	
	@NotBlank(message = "Los apellidos son obligatorios")
	@Size(max = 100, message = "Los apellidos no pueden superar los 100 caracteres")
	private String apellidos;
	
	@NotBlank(message = "La cédula es obligatoria")
	@Size(min = 10, max = 10, message = "La cédula debe tener 10 dígitos")
	@Pattern(regexp = "\\d{10}", message = "La cédula solo debe contener números")
	private String cedula;
	
	@NotBlank(message = "El celular es obligatorio")
    @Pattern(regexp = "09\\d{8}", message = "El celular debe tener el formato ecuatoriano (09XXXXXXXX)")
    private String celular;
	
	@NotBlank(message="El correo es obligatorio")
	@Email(message = "El correo no tiene un formato válido")
	private String correo;
	
	private String especialidad;
	
	@NotBlank(message="La especialidad es obligatoria")
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
