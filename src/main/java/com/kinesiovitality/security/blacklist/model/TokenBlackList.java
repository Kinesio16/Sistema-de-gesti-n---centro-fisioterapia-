package com.kinesiovitality.security.blacklist.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "tokens_blacklist")
public class TokenBlackList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 1500, unique = true)
    private String token;

    @Column(nullable = false)
    private LocalDateTime fechaExpiracion;

    @Column(nullable = false)
    private LocalDateTime fechaRevocacion;

    @Column(length = 255)
    private String motivo;

    @Column(nullable = false)
    private Boolean activo;

    public TokenBlackList() {
    }

    
    public TokenBlackList(Long id, String token, LocalDateTime fechaExpiracion, LocalDateTime fechaRevocacion,
			String motivo, Boolean activo) {
		super();
		this.id = id;
		this.token = token;
		this.fechaExpiracion = fechaExpiracion;
		this.fechaRevocacion = fechaRevocacion;
		this.motivo = motivo;
		this.activo = activo;
	}


	@PrePersist
    public void prePersist() {
        if (this.activo == null) {
            this.activo = true;
        }
        if (this.fechaRevocacion == null) {
            this.fechaRevocacion = LocalDateTime.now();
        }
    }

	// getters y setters
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}


	public LocalDateTime getFechaExpiracion() {
		return fechaExpiracion;
	}


	public void setFechaExpiracion(LocalDateTime fechaExpiracion) {
		this.fechaExpiracion = fechaExpiracion;
	}


	public LocalDateTime getFechaRevocacion() {
		return fechaRevocacion;
	}


	public void setFechaRevocacion(LocalDateTime fechaRevocacion) {
		this.fechaRevocacion = fechaRevocacion;
	}


	public String getMotivo() {
		return motivo;
	}


	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}


	public Boolean getActivo() {
		return activo;
	}


	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

    
}