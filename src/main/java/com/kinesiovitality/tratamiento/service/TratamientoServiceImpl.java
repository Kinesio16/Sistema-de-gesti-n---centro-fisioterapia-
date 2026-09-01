package com.kinesiovitality.tratamiento.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.kinesiovitality.common.enums.EstadoTratamiento;
import com.kinesiovitality.common.exception.ResourceNotFoundException;
import com.kinesiovitality.evaluacion.model.Evaluacion;
import com.kinesiovitality.evaluacion.repository.EvaluacionRepository;
import com.kinesiovitality.fisioterapeuta.model.Fisioterapeuta;
import com.kinesiovitality.fisioterapeuta.repository.FisioterapeutaRepository;
import com.kinesiovitality.paciente.model.Paciente;
import com.kinesiovitality.paciente.repository.PacienteRepository;
import com.kinesiovitality.tratamiento.model.Tratamiento;
import com.kinesiovitality.tratamiento.repository.TratamientoRepository;

@Service
public class TratamientoServiceImpl implements TratamientoService {

    private final TratamientoRepository tratamientoRepository;
    private final PacienteRepository pacienteRepository;
    private final FisioterapeutaRepository fisioterapeutaRepository;
    private final EvaluacionRepository evaluacionRepository;

    public TratamientoServiceImpl(
            TratamientoRepository tratamientoRepository,
            PacienteRepository pacienteRepository,
            FisioterapeutaRepository fisioterapeutaRepository,
            EvaluacionRepository evaluacionRepository) {

        this.tratamientoRepository = tratamientoRepository;
        this.pacienteRepository = pacienteRepository;
        this.fisioterapeutaRepository = fisioterapeutaRepository;
        this.evaluacionRepository = evaluacionRepository;
    }

    // Métodos públicos
    
    @Override
    public Tratamiento guardar(Tratamiento tratamiento,
                               Long pacienteId,
                               Long fisioterapeutaId,
                               Long evaluacionId) {

        Paciente paciente = buscarPaciente(pacienteId);

        Fisioterapeuta fisioterapeuta = buscarFisioterapeuta(fisioterapeutaId);

        Evaluacion evaluacion = buscarEvaluacion(evaluacionId);

        validarEvaluacionPaciente(evaluacion, paciente);
        validarFechaInicio(tratamiento.getFechaInicio());

        tratamiento.setPaciente(paciente);
        tratamiento.setFisioterapeuta(fisioterapeuta);
        tratamiento.setEvaluacion(evaluacion);

        tratamiento.setCodigoTratamiento(generarCodigo());
        
        tratamiento.setEstado(EstadoTratamiento.ACTIVO);

        tratamiento.setSesionesRealizadas(0);
        

        return tratamientoRepository.save(tratamiento);
    }
    
    @Override
    public Tratamiento buscarPorId(Long id) {

        return tratamientoRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Tratamiento no encontrado."));
    }
    
    @Override
    public List<Tratamiento> listar() {

        return tratamientoRepository.findAll();

    }
    
    @Override
    public List<Tratamiento> listarPorPaciente(Long pacienteId) {

        buscarPaciente(pacienteId);

        return tratamientoRepository.findByPacienteId(pacienteId);
    }
    
    
    @Override
    public List<Tratamiento> listarPorFisioterapeuta(Long fisioterapeutaId) {

        buscarFisioterapeuta(fisioterapeutaId);

        return tratamientoRepository.findByFisioterapeutaId(fisioterapeutaId);
    }
    
    @Override
    public List<Tratamiento> listarPorEstado(EstadoTratamiento estado) {

        return tratamientoRepository.findByEstado(estado);
    }
    
    
    @Override
    public Tratamiento actualizar(Long id,
                                  Tratamiento tratamiento,
                                  Long pacienteId,
                                  Long fisioterapeutaId,
                                  Long evaluacionId) {

        Tratamiento existente = buscarPorId(id);
        
        if (existente.getEstado() == EstadoTratamiento.FINALIZADO
                || existente.getEstado() == EstadoTratamiento.CANCELADO) {
            throw new IllegalArgumentException(
                "No es posible actualizar un tratamiento finalizado o cancelado.");
        }

        Paciente paciente = buscarPaciente(pacienteId);

        Fisioterapeuta fisioterapeuta = buscarFisioterapeuta(fisioterapeutaId);

        Evaluacion evaluacion = buscarEvaluacion(evaluacionId);

        validarEvaluacionPaciente(evaluacion, paciente);
        validarFechaInicio(tratamiento.getFechaInicio());

        existente.setPaciente(paciente);
        existente.setFisioterapeuta(fisioterapeuta);
        existente.setEvaluacion(evaluacion);

        existente.setFechaInicio(tratamiento.getFechaInicio());
        existente.setFechaEstimadaAlta(tratamiento.getFechaEstimadaAlta());

        existente.setObjetivoGeneral(tratamiento.getObjetivoGeneral());
        existente.setObjetivosEspecificos(tratamiento.getObjetivosEspecificos());

        existente.setDiagnostico(tratamiento.getDiagnostico());

        existente.setTratamientoPropuesto(
                tratamiento.getTratamientoPropuesto());

        existente.setTecnicas(tratamiento.getTecnicas());

        existente.setSesionesPlanificadas(
                tratamiento.getSesionesPlanificadas());

        existente.setFrecuenciaSemanal(
                tratamiento.getFrecuenciaSemanal());

        existente.setObservacionesIniciales(
                tratamiento.getObservacionesIniciales());

        existente.setObservacionesFinales(
                tratamiento.getObservacionesFinales());

        return tratamientoRepository.save(existente);

    }
    
    
    
    @Override
    public Tratamiento finalizar(Long id) {

        Tratamiento tratamiento = buscarPorId(id);

        if (tratamiento.getEstado() == EstadoTratamiento.FINALIZADO) {
            throw new IllegalArgumentException(
                    "El tratamiento ya fue finalizado.");
        }

        if (tratamiento.getEstado() == EstadoTratamiento.CANCELADO) {
            throw new IllegalArgumentException(
                    "No es posible finalizar un tratamiento cancelado.");
        }

        if (tratamiento.getSesionesRealizadas()
                < tratamiento.getSesionesPlanificadas()) {
            throw new IllegalArgumentException(
                "No es posible finalizar el tratamiento porque aún existen sesiones pendientes.");
        }
        
        tratamiento.setEstado(EstadoTratamiento.FINALIZADO);

        tratamiento.setFechaAlta(LocalDate.now());

        return tratamientoRepository.save(tratamiento);

    }
    
    @Override
    public Tratamiento suspender(Long id) {

        Tratamiento tratamiento = buscarPorId(id);

        if (tratamiento.getEstado() != EstadoTratamiento.ACTIVO) {

            throw new IllegalArgumentException(
                    "Solo un tratamiento activo puede suspenderse.");
        }

        tratamiento.setEstado(EstadoTratamiento.SUSPENDIDO);

        return tratamientoRepository.save(tratamiento);

    }
    
    @Override
    public Tratamiento reanudar(Long id) {

        Tratamiento tratamiento = buscarPorId(id);

        if (tratamiento.getEstado() != EstadoTratamiento.SUSPENDIDO) {

            throw new IllegalArgumentException(
                    "Solo un tratamiento suspendido puede reanudarse.");
        }

        tratamiento.setEstado(EstadoTratamiento.ACTIVO);

        return tratamientoRepository.save(tratamiento);

    }
    
    @Override
    public Tratamiento cancelar(Long id) {

        Tratamiento tratamiento = buscarPorId(id);

        if (tratamiento.getEstado() == EstadoTratamiento.FINALIZADO) {

            throw new IllegalArgumentException(
                    "No es posible cancelar un tratamiento finalizado.");
        }

        tratamiento.setEstado(EstadoTratamiento.CANCELADO);

        return tratamientoRepository.save(tratamiento);

    }
    
    @Override
    public Tratamiento registrarSesion(Long id) {
        Tratamiento tratamiento = buscarPorId(id);

        if (tratamiento.getEstado() != EstadoTratamiento.ACTIVO) {
            throw new IllegalArgumentException(
                "Solo se pueden registrar sesiones en tratamientos activos.");
        }

        if (tratamiento.getSesionesRealizadas()
                >= tratamiento.getSesionesPlanificadas()) {
            throw new IllegalArgumentException(
                "Ya se completaron las sesiones planificadas.");
        }

        tratamiento.setSesionesRealizadas(
            tratamiento.getSesionesRealizadas() + 1
        );

        return tratamientoRepository.save(tratamiento);
    }

    // Métodos privados
    
    private Paciente buscarPaciente(Long id) {

        return pacienteRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Paciente no encontrado."));
    }
    
    private Fisioterapeuta buscarFisioterapeuta(Long id) {

        return fisioterapeutaRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Fisioterapeuta no encontrado."));
    }
    
    private Evaluacion buscarEvaluacion(Long id) {

        return evaluacionRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Evaluación no encontrada."));
    }
    
	private void validarEvaluacionPaciente(Evaluacion evaluacion, Paciente paciente) {

		if (!evaluacion.getPaciente().getId().equals(paciente.getId())) {

			throw new IllegalArgumentException("La evaluación no pertenece al paciente seleccionado.");
		}

	}
	
	private String generarCodigo() {

	    String identificador = UUID.randomUUID()
	            .toString()
	            .substring(0, 8)
	            .toUpperCase(Locale.ROOT);

	    return String.format(
	            "TR-%d-%s",
	            java.time.Year.now().getValue(),
	            identificador);
	}
	
	private void validarFechaInicio(LocalDate fechaInicio) {

	    LocalDate hoy = LocalDate.now();
	    LocalDate limite = hoy.minusDays(15);

	    if (fechaInicio.isBefore(limite)) {
	        throw new IllegalArgumentException(
	                "Solo se permiten registrar tratamientos con una antigüedad máxima de 15 días.");
	    }

	    if (fechaInicio.isAfter(hoy)) {
	        throw new IllegalArgumentException(
	                "La fecha de inicio no puede ser posterior a la fecha actual.");
	    }
	}
	
	
}
