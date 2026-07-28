package com.kinesiovitality.fisioterapeuta.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kinesiovitality.common.enums.EstadoRegistro;
import com.kinesiovitality.fisioterapeuta.model.Fisioterapeuta;


@Repository
public interface FisioterapeutaRepository  extends JpaRepository<Fisioterapeuta, Long>{
	
	Optional<Fisioterapeuta> findByCedula(String cedula);
	boolean existsByCedula(String cedula);
	
	Optional<Fisioterapeuta> findByCorreo(String correo);
	boolean existsByCorreo(String correo);
	
	Optional<Fisioterapeuta> findByNumeroLicencia(String numeroLicencia);
	boolean existsByNumeroLicencia(String numeroLicencia);
	
	List<Fisioterapeuta> findByEstado(EstadoRegistro estado);
	
	List<Fisioterapeuta> findByEspecialidad(String especialidad);
	
	long countByEstado(EstadoRegistro estado);
}
