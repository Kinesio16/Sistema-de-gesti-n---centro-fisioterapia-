package com.kinesiovitality.usuario.model;

import java.time.LocalDateTime;

import com.kinesiovitality.common.enums.Rol;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 50)
    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private Rol rol;

    @Column(nullable = false)
    private Boolean activo;

    @Column(nullable = false)
    private Boolean debeCambiarPassword;

    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    @Column(nullable = false)
    private LocalDateTime fechaActualizacion;
    
    

    public Usuario() {
    }

    
    public Usuario(Long id, String username, String password, Rol rol, Boolean activo, Boolean debeCambiarPassword,
			LocalDateTime fechaCreacion, LocalDateTime fechaActualizacion) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.rol = rol;
		this.activo = activo;
		this.debeCambiarPassword = debeCambiarPassword;
		this.fechaCreacion = fechaCreacion;
		this.fechaActualizacion = fechaActualizacion;
	}


	@PrePersist
    public void prePersist() {
        this.activo = true;
        this.debeCambiarPassword = true;
        this.fechaCreacion = LocalDateTime.now();
        this.fechaActualizacion = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.fechaActualizacion = LocalDateTime.now();
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


	public LocalDateTime getFechaActualizacion() {
		return fechaActualizacion;
	}


	public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

    
}