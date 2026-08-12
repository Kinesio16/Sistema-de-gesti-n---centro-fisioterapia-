package com.kinesiovitality.fisioterapeuta.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kinesiovitality.common.enums.EstadoRegistro;
import com.kinesiovitality.common.exception.ResourceNotFoundException;
import com.kinesiovitality.fisioterapeuta.model.Fisioterapeuta;
import com.kinesiovitality.fisioterapeuta.repository.FisioterapeutaRepository;

@Service
public class FisioterapeutaServiceImpl implements FisioterapeutaService {

    private final FisioterapeutaRepository fisioterapeutaRepository;

    public FisioterapeutaServiceImpl(FisioterapeutaRepository fisioterapeutaRepository) {
        this.fisioterapeutaRepository = fisioterapeutaRepository;
    }

    @Override
    public Fisioterapeuta guardar(Fisioterapeuta fisioterapeuta) {

        if (fisioterapeutaRepository.existsByCedula(fisioterapeuta.getCedula())) {
            throw new IllegalArgumentException("Ya existe un fisioterapeuta con esa cédula.");
        }

        if (fisioterapeutaRepository.existsByCorreo(fisioterapeuta.getCorreo())) {
            throw new IllegalArgumentException("Ya existe un fisioterapeuta con ese correo.");
        }
        
        if (fisioterapeutaRepository.existsByNumeroLicencia(
                fisioterapeuta.getNumeroLicencia())) {

            throw new IllegalArgumentException(
                "Ya existe un fisioterapeuta con ese número de licencia.");

        }

        return fisioterapeutaRepository.save(fisioterapeuta);
    }

    @Override
    public Fisioterapeuta buscarPorId(Long id) {

        return fisioterapeutaRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Fisioterapeuta no encontrado."));
    }

    @Override
    public List<Fisioterapeuta> listar() {

        return fisioterapeutaRepository.findAll();
    }
    
    @Override
    public List<Fisioterapeuta> listarActivos() {

        return fisioterapeutaRepository.findByEstado(
                EstadoRegistro.ACTIVO);

    }

    @Override
    public Fisioterapeuta actualizar(Long id, Fisioterapeuta fisioterapeuta) {

        Fisioterapeuta existente = buscarPorId(id);
        
        if (!existente.getCedula().equals(fisioterapeuta.getCedula())
                && fisioterapeutaRepository.existsByCedula(fisioterapeuta.getCedula())) {

            throw new IllegalArgumentException("La cédula ya está registrada.");
        }
        
        if (!existente.getCorreo().equals(fisioterapeuta.getCorreo())
                && fisioterapeutaRepository.existsByCorreo(fisioterapeuta.getCorreo())) {

            throw new IllegalArgumentException("El correo ya está registrado.");
        }
        
        if(!existente.getNumeroLicencia().equals(fisioterapeuta.getNumeroLicencia())
        	&& fisioterapeutaRepository.existsByNumeroLicencia(fisioterapeuta.getNumeroLicencia())){
        	
        	throw new IllegalArgumentException("El numero de licencia ya esta registrado.");
        }

        existente.setNombres(fisioterapeuta.getNombres());
        existente.setApellidos(fisioterapeuta.getApellidos());
        existente.setCedula(fisioterapeuta.getCedula());
        existente.setCelular(fisioterapeuta.getCelular());
        existente.setCorreo(fisioterapeuta.getCorreo());
        existente.setEspecialidad(fisioterapeuta.getEspecialidad());
        existente.setNumeroLicencia(fisioterapeuta.getNumeroLicencia());

        return fisioterapeutaRepository.save(existente);
    }

    @Override
    public void eliminar(Long id) {

        Fisioterapeuta fisioterapeuta = buscarPorId(id);
        
        if (fisioterapeuta.getEstado() == EstadoRegistro.INACTIVO) {

            throw new IllegalArgumentException(
                "El fisioterapeuta ya se encuentra inactivo."
            );

        }
        fisioterapeuta.setEstado(EstadoRegistro.INACTIVO);

        fisioterapeutaRepository.save(fisioterapeuta);
    }
    
    @Override
    public void reactivar(Long id) {

        Fisioterapeuta fisioterapeuta = buscarPorId(id);

        if (fisioterapeuta.getEstado() == EstadoRegistro.ACTIVO) {

            throw new IllegalArgumentException(
                "El fisioterapeuta ya se encuentra activo."
            );

        }

        fisioterapeuta.setEstado(EstadoRegistro.ACTIVO);

        fisioterapeutaRepository.save(fisioterapeuta);

    }
}