package com.kinesiovitality.usuario.dto;

import com.kinesiovitality.common.enums.Rol;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ActualizarUsuarioRequest {

    @NotBlank(message = "El nombre de usuario es obligatorio.")
    @Size(min = 4, max = 50)
    @Schema(
            description = "Nombre de usuario",
            example = "fisioterapeuta1"
    )
    private String username;

    @NotNull(message = "El rol es obligatorio.")
    @Schema(
            description = "Rol del usuario",
            example = "FISIOTERAPEUTA"
    )
    private Rol rol;

    public ActualizarUsuarioRequest() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

}