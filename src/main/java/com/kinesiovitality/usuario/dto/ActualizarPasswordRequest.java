package com.kinesiovitality.usuario.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ActualizarPasswordRequest {

    @NotBlank(message = "La nueva contraseña es obligatoria.")
    @Size(min = 8, max = 100)
    @Schema(
    	    description = "Nueva contraseña del usuario",
    	    example = "NuevaClave@2026"
    	)
    private String nuevaPassword;

    public String getNuevaPassword() {
        return nuevaPassword;
    }

    public void setNuevaPassword(String nuevaPassword) {
        this.nuevaPassword = nuevaPassword;
    }

}