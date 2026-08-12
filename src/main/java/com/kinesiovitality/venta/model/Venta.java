package com.kinesiovitality.venta.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.kinesiovitality.common.enums.EstadoFactura;
import com.kinesiovitality.common.enums.EstadoPago;
import com.kinesiovitality.common.enums.FormaPago;
import com.kinesiovitality.fisioterapeuta.model.Fisioterapeuta;
import com.kinesiovitality.paciente.model.Paciente;
import com.kinesiovitality.servicio.model.Servicio;
import com.kinesiovitality.sucursal.model.Sucursal;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "ventas")
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 20)
    private String codigoVenta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "servicio_id", nullable = false)
    private Servicio servicio;
    
    @ManyToOne(fetch = FetchType.LAZY)

    @JoinColumn(name="fisioterapeuta_id")

    private Fisioterapeuta fisioterapeuta;

    @Column(nullable = false, length = 100)
    private String nombreServicio;

    @Column(nullable = false)
    private Integer cantidadSesiones;

    @NotNull
    @DecimalMin("0.00")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precioUnitario;

    @NotNull
    @DecimalMin("0.00")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal descuento;

    @NotNull
    @DecimalMin("0.00")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal total;

    @Column(nullable = false)
    private Boolean promocion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FormaPago formaPago;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoPago estadoPago;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoFactura estadoFactura;

    @Column(nullable = false)
    private LocalDate fechaVenta;

    @Size(max = 500)
    @Column(length = 500)
    private String observaciones;

    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    @Column(nullable = false)
    private LocalDateTime fechaActualizacion;
    
    @ManyToOne

    private Sucursal sucursal;

    
    
    public Venta() {
    }
    

	public Venta(Long id, String codigoVenta, Paciente paciente, Servicio servicio, Fisioterapeuta fisioterapeuta,
			String nombreServicio, Integer cantidadSesiones, @NotNull @DecimalMin("0.00") BigDecimal precioUnitario,
			@NotNull @DecimalMin("0.00") BigDecimal descuento, @NotNull @DecimalMin("0.00") BigDecimal total,
			Boolean promocion, FormaPago formaPago, EstadoPago estadoPago, EstadoFactura estadoFactura,
			LocalDate fechaVenta, @Size(max = 500) String observaciones, LocalDateTime fechaCreacion,
			LocalDateTime fechaActualizacion, Sucursal sucursal) {
		super();
		this.id = id;
		this.codigoVenta = codigoVenta;
		this.paciente = paciente;
		this.servicio = servicio;
		this.fisioterapeuta = fisioterapeuta;
		this.nombreServicio = nombreServicio;
		this.cantidadSesiones = cantidadSesiones;
		this.precioUnitario = precioUnitario;
		this.descuento = descuento;
		this.total = total;
		this.promocion = promocion;
		this.formaPago = formaPago;
		this.estadoPago = estadoPago;
		this.estadoFactura = estadoFactura;
		this.fechaVenta = fechaVenta;
		this.observaciones = observaciones;
		this.fechaCreacion = fechaCreacion;
		this.fechaActualizacion = fechaActualizacion;
		this.sucursal = sucursal;
	}











	@PrePersist
    public void prePersist() {

        this.fechaVenta = LocalDate.now();

        this.fechaCreacion = LocalDateTime.now();

        this.fechaActualizacion = LocalDateTime.now();

        if (this.descuento == null) {
            this.descuento = BigDecimal.ZERO;
        }

        if (this.promocion == null) {
            this.promocion = false;
        }

    }

    @PreUpdate
    public void preUpdate() {

        this.fechaActualizacion = LocalDateTime.now();

    }


    // ==========================
    // GETTERS Y SETTERS
    // ==========================
    
	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getCodigoVenta() {
		return codigoVenta;
	}



	public void setCodigoVenta(String codigoVenta) {
		this.codigoVenta = codigoVenta;
	}



	public Paciente getPaciente() {
		return paciente;
	}



	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}



	public Servicio getServicio() {
		return servicio;
	}



	public Fisioterapeuta getFisioterapeuta() {
		return fisioterapeuta;
	}




	public void setFisioterapeuta(Fisioterapeuta fisioterapeuta) {
		this.fisioterapeuta = fisioterapeuta;
	}




	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}



	public String getNombreServicio() {
		return nombreServicio;
	}



	public void setNombreServicio(String nombreServicio) {
		this.nombreServicio = nombreServicio;
	}



	public Integer getCantidadSesiones() {
		return cantidadSesiones;
	}



	public void setCantidadSesiones(Integer cantidadSesiones) {
		this.cantidadSesiones = cantidadSesiones;
	}



	public BigDecimal getPrecioUnitario() {
		return precioUnitario;
	}



	public void setPrecioUnitario(BigDecimal precioUnitario) {
		this.precioUnitario = precioUnitario;
	}



	public BigDecimal getDescuento() {
		return descuento;
	}



	public void setDescuento(BigDecimal descuento) {
		this.descuento = descuento;
	}



	public BigDecimal getTotal() {
		return total;
	}



	public void setTotal(BigDecimal total) {
		this.total = total;
	}



	public Boolean getPromocion() {
		return promocion;
	}



	public void setPromocion(Boolean promocion) {
		this.promocion = promocion;
	}



	public FormaPago getFormaPago() {
		return formaPago;
	}



	public void setFormaPago(FormaPago formaPago) {
		this.formaPago = formaPago;
	}



	public EstadoPago getEstadoPago() {
		return estadoPago;
	}



	public void setEstadoPago(EstadoPago estadoPago) {
		this.estadoPago = estadoPago;
	}



	public EstadoFactura getEstadoFactura() {
		return estadoFactura;
	}



	public void setEstadoFactura(EstadoFactura estadoFactura) {
		this.estadoFactura = estadoFactura;
	}



	public LocalDate getFechaVenta() {
		return fechaVenta;
	}



	public void setFechaVenta(LocalDate fechaVenta) {
		this.fechaVenta = fechaVenta;
	}



	public String getObservaciones() {
		return observaciones;
	}



	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
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


	public Sucursal getSucursal() {
		return sucursal;
	}


	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}

    
    
}
