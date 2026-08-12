package com.kinesiovitality.sucursal.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(
        name = "SucursalRequest",
        description = "Información necesaria para registrar o actualizar una sucursal."
)
public class SucursalRequest {

    @NotBlank(message = "El nombre es obligatorio.")
    @Size(max = 100)
    @Schema(example = "Sucursal Norte")
    private String nombre;

    @NotBlank(message = "La dirección es obligatoria.")
    @Size(max = 200)
    @Schema(example = "Av. Amazonas y Naciones Unidas")
    private String direccion;

    @NotBlank(message = "El teléfono es obligatorio.")
    @Pattern(
            regexp = "09\\d{8}",
            message = "El teléfono debe tener el formato ecuatoriano (09XXXXXXXX)."
    )
    @Schema(example = "0991234567")
    private String telefono;

    @Email(message = "El correo no tiene un formato válido.")
    @Schema(example = "norte@kinesiovitality.com")
    private String correo;

    public SucursalRequest() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

}