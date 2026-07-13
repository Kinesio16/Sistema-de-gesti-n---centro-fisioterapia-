package com.kinesiovitality.paciente.dto;

import java.time.LocalDate;

import com.kinesiovitality.common.enums.Sexo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class PacienteRequest {

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
	
	@Past(message = "La fecha de nacimiento debe ser anterior a la fecha actual")
	private LocalDate fechaNacimiento;
	
    private Sexo sexo;
    
    @NotBlank(message = "El celular es obligatorio")
    @Pattern(regexp = "09\\d{8}", message = "El celular debe tener el formato ecuatoriano (09XXXXXXXX)")
    private String celular;
    
    @Email(message = "El correo no tiene un formato válido")
    private String correo;
    
    private String direccion;
    private String ciudad;
    private String tipoSangre;
    private String alergias;
    private String enfermedades;
    private String observaciones;
    private String contactoEmergencia;
    private String parentescoContacto;
    private String telefonoContacto;

    public PacienteRequest() {
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
    

}
