package com.kinesiovitality.evaluacion.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kinesiovitality.common.enums.EstadoRegistro;
import com.kinesiovitality.common.exception.ResourceNotFoundException;
import com.kinesiovitality.evaluacion.dto.EvaluacionRequest;
import com.kinesiovitality.evaluacion.mapper.EvaluacionMapper;
import com.kinesiovitality.evaluacion.model.Evaluacion;
import com.kinesiovitality.evaluacion.repository.EvaluacionRepository;
import com.kinesiovitality.fisioterapeuta.model.Fisioterapeuta;
import com.kinesiovitality.fisioterapeuta.repository.FisioterapeutaRepository;
import com.kinesiovitality.paciente.model.Paciente;
import com.kinesiovitality.paciente.repository.PacienteRepository;

@Service
public class EvaluacionServiceImpl implements EvaluacionService {

    private final EvaluacionRepository evaluacionRepository;
    private final PacienteRepository pacienteRepository;
    private final FisioterapeutaRepository fisioterapeutaRepository;

    public EvaluacionServiceImpl(
            EvaluacionRepository evaluacionRepository,
            PacienteRepository pacienteRepository,
            FisioterapeutaRepository fisioterapeutaRepository) {

        this.evaluacionRepository = evaluacionRepository;
        this.pacienteRepository = pacienteRepository;
        this.fisioterapeutaRepository = fisioterapeutaRepository;
    }

    @Override
    public Evaluacion guardar(EvaluacionRequest request) {

        Paciente paciente = pacienteRepository.findById(request.getPacienteId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Paciente no encontrado."));

        Fisioterapeuta fisioterapeuta = fisioterapeutaRepository.findById(request.getFisioterapeutaId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Fisioterapeuta no encontrado."));

        Evaluacion evaluacion = EvaluacionMapper.toEntity(
                request,
                paciente,
                fisioterapeuta);

        evaluacion.setCodigoEvaluacion(generarCodigoEvaluacion());

        return evaluacionRepository.save(evaluacion);
    }

    @Override
    public Evaluacion buscarPorId(Long id) {

        return evaluacionRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Evaluación no encontrada."));
    }

    @Override
    public List<Evaluacion> listar() {

        return evaluacionRepository.findAll();
    }

    @Override
    public Evaluacion actualizar(Long id, EvaluacionRequest request) {

        Evaluacion existente = buscarPorId(id);

        Paciente paciente = pacienteRepository.findById(request.getPacienteId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Paciente no encontrado."));

        Fisioterapeuta fisioterapeuta = fisioterapeutaRepository.findById(request.getFisioterapeutaId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Fisioterapeuta no encontrado."));

        existente.setPaciente(paciente);
        existente.setFisioterapeuta(fisioterapeuta);

        existente.setFechaEvaluacion(request.getFechaEvaluacion());
        existente.setMotivoConsulta(request.getMotivoConsulta());
        existente.setAntecedentes(request.getAntecedentes());
        existente.setEscalaDolorEva(request.getEscalaDolorEva());
        existente.setDiagnosticoFisioterapeutico(request.getDiagnosticoFisioterapeutico());
        existente.setObjetivosTratamiento(request.getObjetivosTratamiento());
        existente.setInspeccion(request.getInspeccion());
        existente.setPalpacion(request.getPalpacion());
        existente.setRangoMovimiento(request.getRangoMovimiento());
        existente.setFuerzaMuscular(request.getFuerzaMuscular());
        existente.setPruebasFuncionales(request.getPruebasFuncionales());
        existente.setSesionesRecomendadas(request.getSesionesRecomendadas());
        existente.setFrecuenciaSemanal(request.getFrecuenciaSemanal());
        existente.setTratamientoSugerido(request.getTratamientoSugerido());
        existente.setObservaciones(request.getObservaciones());

        return evaluacionRepository.save(existente);
    }

    @Override
    public void eliminar(Long id) {

        Evaluacion evaluacion = buscarPorId(id);

        evaluacion.setEstado(EstadoRegistro.INACTIVO);

        evaluacionRepository.save(evaluacion);
    }

    private String generarCodigoEvaluacion() {

        long siguiente = evaluacionRepository.count() + 1;

        return String.format(
                "EV-%d-%06d",
                LocalDate.now().getYear(),
                siguiente);
    }

}
