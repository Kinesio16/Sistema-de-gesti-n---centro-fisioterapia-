package com.kinesiovitality.cita.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kinesiovitality.cita.dto.CitaRequest;
import com.kinesiovitality.cita.mapper.CitaMapper;
import com.kinesiovitality.cita.model.Cita;
import com.kinesiovitality.cita.repository.CitaRepository;
import com.kinesiovitality.common.enums.EstadoCita;
import com.kinesiovitality.common.exception.ResourceNotFoundException;
import com.kinesiovitality.fisioterapeuta.model.Fisioterapeuta;
import com.kinesiovitality.fisioterapeuta.repository.FisioterapeutaRepository;
import com.kinesiovitality.paciente.model.Paciente;
import com.kinesiovitality.paciente.repository.PacienteRepository;

@Service
public class CitaServiceImpl implements CitaService {

    private final CitaRepository citaRepository;
    private final PacienteRepository pacienteRepository;
    private final FisioterapeutaRepository fisioterapeutaRepository;

    public CitaServiceImpl(
            CitaRepository citaRepository,
            PacienteRepository pacienteRepository,
            FisioterapeutaRepository fisioterapeutaRepository) {

        this.citaRepository = citaRepository;
        this.pacienteRepository = pacienteRepository;
        this.fisioterapeutaRepository = fisioterapeutaRepository;
    }

    @Override
    public Cita guardar(CitaRequest request) {

        Paciente paciente = pacienteRepository.findById(request.getPacienteId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Paciente no encontrado."));

        Fisioterapeuta fisioterapeuta = fisioterapeutaRepository.findById(request.getFisioterapeutaId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Fisioterapeuta no encontrado."));

        // Validar horario
        if (!request.getHoraFin().isAfter(request.getHoraInicio())) {
            throw new IllegalArgumentException(
                    "La hora de fin debe ser posterior a la hora de inicio.");
        }

        // Validar que el paciente no tenga otra cita en el mismo horario
        List<Cita> citasPaciente = citaRepository.findByPacienteIdAndFecha(
                paciente.getId(),
                request.getFecha());

        for (Cita cita : citasPaciente) {

            boolean hayCruce =
                    request.getHoraInicio().isBefore(cita.getHoraFin())
                    &&
                    request.getHoraFin().isAfter(cita.getHoraInicio());

            if (hayCruce) {
                throw new IllegalArgumentException(
                        "El paciente ya tiene una cita en ese horario.");
            }
        }

        Cita nuevaCita = CitaMapper.toEntity(
                request,
                paciente,
                fisioterapeuta);

        return citaRepository.save(nuevaCita);
    }

    @Override
    public Cita buscarPorId(Long id) {

        return citaRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Cita no encontrada."));
    }

    @Override
    public List<Cita> listar() {

        return citaRepository.findAll();
    }

    @Override
    public Cita actualizar(Long id, CitaRequest request) {

        Cita existente = buscarPorId(id);

        Paciente paciente = pacienteRepository.findById(request.getPacienteId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Paciente no encontrado."));

        Fisioterapeuta fisioterapeuta = fisioterapeutaRepository.findById(request.getFisioterapeutaId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Fisioterapeuta no encontrado."));

        if (!request.getHoraFin().isAfter(request.getHoraInicio())) {
            throw new IllegalArgumentException(
                    "La hora de fin debe ser posterior a la hora de inicio.");
        }

        List<Cita> citasPaciente = citaRepository.findByPacienteIdAndFecha(
                paciente.getId(),
                request.getFecha());

        for (Cita cita : citasPaciente) {

            if (!cita.getId().equals(id)) {

                boolean hayCruce =
                        request.getHoraInicio().isBefore(cita.getHoraFin())
                        &&
                        request.getHoraFin().isAfter(cita.getHoraInicio());

                if (hayCruce) {
                    throw new IllegalArgumentException(
                            "El paciente ya tiene otra cita en ese horario.");
                }
            }
        }

        existente.setPaciente(paciente);
        existente.setFisioterapeuta(fisioterapeuta);
        existente.setFecha(request.getFecha());
        existente.setHoraInicio(request.getHoraInicio());
        existente.setHoraFin(request.getHoraFin());

        existente.setDuracionMinutos(
                (int) java.time.Duration.between(
                        request.getHoraInicio(),
                        request.getHoraFin())
                        .toMinutes());

        existente.setTipoTerapia(request.getTipoTerapia());
        existente.setMotivoConsulta(request.getMotivoConsulta());
        existente.setObservaciones(request.getObservaciones());

        return citaRepository.save(existente);
    }

    @Override
    public void cancelar(Long id) {

        Cita cita = buscarPorId(id);

        cita.setEstado(EstadoCita.CANCELADA);

        citaRepository.save(cita);
    }

}