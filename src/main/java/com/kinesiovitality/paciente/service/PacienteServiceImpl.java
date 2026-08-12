package com.kinesiovitality.paciente.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kinesiovitality.common.enums.EstadoRegistro;
import com.kinesiovitality.common.exception.ResourceNotFoundException;
import com.kinesiovitality.paciente.model.Paciente;
import com.kinesiovitality.paciente.repository.PacienteRepository;

@Service
public class PacienteServiceImpl implements PacienteService {

    private final PacienteRepository pacienteRepository;

    public PacienteServiceImpl(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public Paciente guardar(Paciente paciente) {

        if (pacienteRepository.existsByCedula(paciente.getCedula())) {
            throw new IllegalArgumentException("Ya existe un paciente con esa cédula.");
        }

        return pacienteRepository.save(paciente);
    }

    @Override
    public Paciente buscarPorId(Long id) {

        return pacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado."));
    }

    @Override
    public List<Paciente> listar() {
        return pacienteRepository.findAll();
    }
    
    @Override
    public List<Paciente> listarActivos() {

        return pacienteRepository.findByEstado(
                EstadoRegistro.ACTIVO);

    }

    @Override
    public Paciente actualizar(Long id, Paciente paciente) {

        Paciente existente = buscarPorId(id);
        
        if (!existente.getCedula().equals(paciente.getCedula())
                && pacienteRepository.existsByCedula(paciente.getCedula())) {

            throw new IllegalArgumentException(
                "Ya existe un paciente con esa cédula."
            );
        }

        existente.setNombres(paciente.getNombres());
        existente.setApellidos(paciente.getApellidos());
        existente.setCedula(paciente.getCedula());
        existente.setFechaNacimiento(paciente.getFechaNacimiento());
        existente.setSexo(paciente.getSexo());
        existente.setCelular(paciente.getCelular());
        existente.setCorreo(paciente.getCorreo());

        existente.setDireccion(paciente.getDireccion());
        existente.setCiudad(paciente.getCiudad());
        existente.setTipoSangre(paciente.getTipoSangre());
        existente.setAlergias(paciente.getAlergias());
        existente.setEnfermedades(paciente.getEnfermedades());
        existente.setObservaciones(paciente.getObservaciones());
        existente.setContactoEmergencia(paciente.getContactoEmergencia());
        existente.setParentescoContacto(paciente.getParentescoContacto());
        existente.setTelefonoContacto(paciente.getTelefonoContacto());

        return pacienteRepository.save(existente);

    }

    @Override
    public void eliminar(Long id) {

        Paciente paciente = buscarPorId(id);
        
        if (paciente.getEstado() == EstadoRegistro.INACTIVO) {

            throw new IllegalArgumentException(
                "El paciente ya se encuentra inactivo."
            );

        }

        paciente.setEstado(EstadoRegistro.INACTIVO);
        
        pacienteRepository.save(paciente);
    }
    
    @Override
    public void reactivar(Long id) {

        Paciente paciente = buscarPorId(id);

        if (paciente.getEstado() == EstadoRegistro.ACTIVO) {
            throw new IllegalArgumentException(
                "El paciente ya se encuentra activo."
            );
        }

        paciente.setEstado(EstadoRegistro.ACTIVO);

        pacienteRepository.save(paciente);
    }

}
