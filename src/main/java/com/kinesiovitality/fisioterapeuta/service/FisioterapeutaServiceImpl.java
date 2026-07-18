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

        fisioterapeuta.setEstado(EstadoRegistro.INACTIVO);

        fisioterapeutaRepository.save(fisioterapeuta);
    }

}