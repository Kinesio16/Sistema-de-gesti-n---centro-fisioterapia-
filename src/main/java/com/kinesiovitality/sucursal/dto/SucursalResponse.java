package com.kinesiovitality.sucursal.dto;

import java.time.LocalDateTime;

import com.kinesiovitality.common.enums.EstadoRegistro;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "SucursalResponse",
        description = "Información completa de la sucursal."
)
public class SucursalResponse {

    private Long id;

    private String nombre;

    private String direccion;

    private String telefono;

    private String correo;

    private EstadoRegistro estado;

    private LocalDateTime fechaCreacion;

    private LocalDateTime fechaActualizacion;

    public SucursalResponse() {
    }

    public SucursalResponse(Long id, String nombre, String direccion,
                            String telefono, String correo,
                            EstadoRegistro estado,
                            LocalDateTime fechaCreacion,
                            LocalDateTime fechaActualizacion) {

        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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