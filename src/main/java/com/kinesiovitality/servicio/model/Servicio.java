package com.kinesiovitality.servicio.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "servicios")
public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 20)
    private String codigoServicio;

    @NotBlank(message = "El nombre es obligatorio.")
    @Size(max = 100)
    @Column(nullable = false)
    private String nombre;

    @Size(max = 500)
    @Column(length = 500)
    private String descripcion;

    @NotNull(message = "La cantidad de sesiones es obligatoria.")
    @Min(value = 1, message = "Debe ser mayor a cero.")
    @Column(nullable = false)
    private Integer cantidadSesiones;

    @DecimalMin(
    	    value = "0.00",
    	    message = "El precio de costo debe ser mayor o igual a cero."
    	)
    @Column(precision = 10, scale = 2)
    private BigDecimal precioCosto;

    @NotNull(message = "El precio de venta es obligatorio.")
    @DecimalMin(value = "0.00")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precioVenta;

    @Column(nullable = false)
    private Boolean activo;

    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    @Column(nullable = false)
    private LocalDateTime fechaActualizacion;

    public Servicio() {
    }
    
    

    public Servicio(Long id, String codigoServicio,
			@NotBlank(message = "El nombre es obligatorio.") @Size(max = 100) String nombre,
			@Size(max = 500) String descripcion,
			@NotNull(message = "La cantidad de sesiones es obligatoria.") @Min(value = 1, message = "Debe ser mayor a cero.") Integer cantidadSesiones,
			@NotNull(message = "El precio de costo es obligatorio.") @DecimalMin("0.00") BigDecimal precioCosto,
			@NotNull(message = "El precio de venta es obligatorio.") @DecimalMin("0.00") BigDecimal precioVenta,
			Boolean activo, LocalDateTime fechaCreacion, LocalDateTime fechaActualizacion) {
		super();
		this.id = id;
		this.codigoServicio = codigoServicio;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.cantidadSesiones = cantidadSesiones;
		this.precioCosto = precioCosto;
		this.precioVenta = precioVenta;
		this.activo = activo;
		this.fechaCreacion = fechaCreacion;
		this.fechaActualizacion = fechaActualizacion;
	}



	@PrePersist
    public void prePersist() {

        this.activo = true;
        this.fechaCreacion = LocalDateTime.now();
        this.fechaActualizacion = LocalDateTime.now();

    }

    @PreUpdate
    public void preUpdate() {

        this.fechaActualizacion = LocalDateTime.now();

    }

    // ===== GETTERS Y SETTERS =====

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoServicio() {
        return codigoServicio;
    }

    public void setCodigoServicio(String codigoServicio) {
        this.codigoServicio = codigoServicio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getCantidadSesiones() {
        return cantidadSesiones;
    }

    public void setCantidadSesiones(Integer cantidadSesiones) {
        this.cantidadSesiones = cantidadSesiones;
    }

    public BigDecimal getPrecioCosto() {
        return precioCosto;
    }

    public void setPrecioCosto(BigDecimal precioCosto) {
        this.precioCosto = precioCosto;
    }

    public BigDecimal getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(BigDecimal precioVenta) {
        this.precioVenta = precioVenta;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
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
