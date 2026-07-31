package com.kinesiovitality.usuario.dto;

import com.kinesiovitality.common.enums.Rol;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import io.swagger.v3.oas.annotations.media.Schema;

public class UsuarioRequest {

    @NotBlank(message = "El nombre de usuario es obligatorio.")
    @Size(min = 4, max = 50)
    @Schema(
    	    description = "Nombre de usuario",
    	    example = "fisioterapeuta1"
    	)
    private String username;

    @NotBlank(message = "La contraseña es obligatoria.")
    @Size(min = 8, max = 100)
    @Schema(
    	    description = "Contraseña del usuario",
    	    example = "Fisio@2026*"
    	)
    private String password;

    @NotNull(message = "El rol es obligatorio.")
    @Schema(
    	    description = "Rol del usuario",
    	    example = "FISIOTERAPEUTA"
    	)
    private Rol rol;
    
    

    public UsuarioRequest() {
		super();
	}
    
    // getters y setters

	public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

}