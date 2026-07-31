package com.kinesiovitality.usuario.dto;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;

public class UsuarioResponse {
	
	@Schema(
		    description = "Identificador del usuario",
		    example = "3"
		)
    private Long id;

	@Schema(
		    description = "Nombre de usuario",
		    example = "fisioterapeuta1"
		)
		private String username;
	
	@Schema(
		    description = "Rol del usuario",
		    example = "FISIOTERAPEUTA"
		)
    private String rol;
	
	@Schema(
		    description = "Indica si el usuario está activo",
		    example = "true"
		)
    private Boolean activo;
	
	@Schema(
		    description = "Obliga al usuario a cambiar la contraseña en el primer inicio de sesión",
		    example = "true"
		)
    private Boolean debeCambiarPassword;
	
	
    private LocalDateTime fechaCreacion;
    
    


    public UsuarioResponse() {
		super();
	}
    
    // getters y setters

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Boolean getDebeCambiarPassword() {
        return debeCambiarPassword;
    }

    public void setDebeCambiarPassword(Boolean debeCambiarPassword) {
        this.debeCambiarPassword = debeCambiarPassword;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

}
