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
    public Paciente actualizar(Long id, Paciente paciente) {

        Paciente existente = buscarPorId(id);

        existente.setNombres(paciente.getNombres());
        existente.setApellidos(paciente.getApellidos());
        existente.setCelular(paciente.getCelular());
        existente.setCorreo(paciente.getCorreo());
        existente.setDireccion(paciente.getDireccion());

        return pacienteRepository.save(existente);
    }

    @Override
    public void eliminar(Long id) {

        Paciente paciente = buscarPorId(id);

        paciente.setEstado(EstadoRegistro.INACTIVO);
        
        pacienteRepository.save(paciente);
    }

}
