package com.kinesiovitality.sesion.service;


import java.time.Year;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kinesiovitality.common.enums.EstadoSesion;
import com.kinesiovitality.common.enums.EstadoTratamiento;
import com.kinesiovitality.common.exception.ResourceNotFoundException;
import com.kinesiovitality.sesion.model.Sesion;
import com.kinesiovitality.sesion.repository.SesionRepository;
import com.kinesiovitality.tratamiento.model.Tratamiento;
import com.kinesiovitality.tratamiento.repository.TratamientoRepository;
import com.kinesiovitality.tratamiento.business.TratamientoBusinessService;

@Service
public class SesionServiceImpl implements SesionService {

	private final SesionRepository sesionRepository;
	private final TratamientoRepository tratamientoRepository;
	private final TratamientoBusinessService tratamientoBusinessService;

	public SesionServiceImpl(
	        SesionRepository sesionRepository,
	        TratamientoRepository tratamientoRepository,
	        TratamientoBusinessService tratamientoBusinessService) {

	    this.sesionRepository = sesionRepository;
	    this.tratamientoRepository = tratamientoRepository;
	    this.tratamientoBusinessService = tratamientoBusinessService;
	}

    @Override
    public Sesion guardar(Sesion sesion, Long tratamientoId) {

        Tratamiento tratamiento = buscarTratamiento(tratamientoId);
        validarTratamientoActivo(tratamiento);
        validarHorario(sesion.getHoraInicio(), sesion.getHoraFin());

        sesion.setTratamiento(tratamiento);
        sesion.setCodigoSesion(generarCodigoSesion());
        sesion.setEstado(EstadoSesion.PROGRAMADA);

        return sesionRepository.save(sesion);
    }

    @Override
    public Sesion buscarPorId(Long id) {

        return sesionRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Sesión no encontrada."));
    }

    @Override
    public List<Sesion> listar() {
        return sesionRepository.findAll();
    }

    @Override
    public Sesion actualizar(Long id, Sesion sesion, Long tratamientoId) {

        Sesion existente = buscarPorId(id);

        if (existente.getEstado() == EstadoSesion.REALIZADA) {
            throw new IllegalArgumentException(
                    "No es posible actualizar una sesión realizada.");
        }

        Tratamiento tratamiento = buscarTratamiento(tratamientoId);
        validarTratamientoActivo(tratamiento);
        validarHorario(sesion.getHoraInicio(), sesion.getHoraFin());

        existente.setTratamiento(tratamiento);
        existente.setFechaSesion(sesion.getFechaSesion());
        existente.setHoraInicio(sesion.getHoraInicio());
        existente.setHoraFin(sesion.getHoraFin());
        existente.setEvolucionClinica(sesion.getEvolucionClinica());
        existente.setObservaciones(sesion.getObservaciones());
        existente.setProximaSesionObservacion(sesion.getProximaSesionObservacion());
        existente.setEvaAntes(sesion.getEvaAntes());
        existente.setEvaDespues(sesion.getEvaDespues());
        existente.setTecnicasAplicadas(sesion.getTecnicasAplicadas());
        existente.setProximaSesion(sesion.getProximaSesion());

        return sesionRepository.save(existente);
    }

    @Override
    public Sesion registrarRealizada(Long id) {

        Sesion sesion = buscarPorId(id);

        if (sesion.getEstado() == EstadoSesion.REALIZADA) {
            throw new IllegalArgumentException(
                    "La sesión ya fue registrada como realizada.");
        }

        if (sesion.getEstado() == EstadoSesion.CANCELADA) {
            throw new IllegalArgumentException(
                    "No es posible realizar una sesión cancelada.");
        }

        if (sesion.getEstado() == EstadoSesion.NO_ASISTIO) {
            throw new IllegalArgumentException(
                    "No es posible marcar como realizada una sesión con inasistencia.");
        }

        validarTratamientoActivo(sesion.getTratamiento());

        sesion.setEstado(EstadoSesion.REALIZADA);

        sesionRepository.save(sesion);

        tratamientoBusinessService.registrarSesion(sesion);

        return sesion;
    }

    @Override
    public Sesion reprogramar(Long id, Sesion sesionNueva) {

        Sesion existente = buscarPorId(id);

        if (existente.getEstado() == EstadoSesion.REALIZADA) {
            throw new IllegalArgumentException("No es posible reprogramar una sesión realizada.");
        }

        if (existente.getEstado() == EstadoSesion.CANCELADA) {
            throw new IllegalArgumentException("No es posible reprogramar una sesión cancelada.");
        }

        validarHorario(sesionNueva.getHoraInicio(), sesionNueva.getHoraFin());

        Tratamiento tratamiento = existente.getTratamiento();
        validarTratamientoActivo(tratamiento);

        existente.setFechaSesion(sesionNueva.getFechaSesion());
        existente.setHoraInicio(sesionNueva.getHoraInicio());
        existente.setHoraFin(sesionNueva.getHoraFin());
        existente.setEvolucionClinica(sesionNueva.getEvolucionClinica());
        existente.setObservaciones(sesionNueva.getObservaciones());
        existente.setProximaSesionObservacion(sesionNueva.getProximaSesionObservacion());
        existente.setEvaAntes(sesionNueva.getEvaAntes());
        existente.setEvaDespues(sesionNueva.getEvaDespues());
        existente.setTecnicasAplicadas(sesionNueva.getTecnicasAplicadas());
        existente.setProximaSesion(sesionNueva.getProximaSesion());
        existente.setEstado(EstadoSesion.REPROGRAMADA);

        return sesionRepository.save(existente);
    }

    @Override
    public Sesion cancelar(Long id) {

        Sesion sesion = buscarPorId(id);

        if (sesion.getEstado() == EstadoSesion.REALIZADA) {
            throw new IllegalArgumentException("No es posible cancelar una sesión realizada.");
        }

        sesion.setEstado(EstadoSesion.CANCELADA);

        return sesionRepository.save(sesion);
    }

    @Override
    public Sesion noAsistio(Long id) {

        Sesion sesion = buscarPorId(id);

        if (sesion.getEstado() == EstadoSesion.REALIZADA) {
            throw new IllegalArgumentException("No es posible marcar inasistencia en una sesión realizada.");
        }

        if (sesion.getEstado() == EstadoSesion.CANCELADA) {
            throw new IllegalArgumentException("No es posible marcar inasistencia en una sesión cancelada.");
        }

        sesion.setEstado(EstadoSesion.NO_ASISTIO);

        return sesionRepository.save(sesion);
    }
    
    @Override
    public void eliminar(Long id) {

        Sesion sesion = buscarPorId(id);

        // Si la sesión ya fue realizada,
        // debemos devolver la sesión al tratamiento.
        if (sesion.getEstado() == EstadoSesion.REALIZADA) {

            tratamientoBusinessService.eliminarSesion(sesion);

        }

        sesionRepository.delete(sesion);

    }

    private Tratamiento buscarTratamiento(Long id) {

        return tratamientoRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Tratamiento no encontrado."));
    }

    private void validarTratamientoActivo(Tratamiento tratamiento) {

        if (tratamiento.getEstado() != EstadoTratamiento.ACTIVO) {
            throw new IllegalArgumentException(
                    "Solo se pueden registrar sesiones para tratamientos activos.");
        }
    }

    private void validarHorario(java.time.LocalTime inicio, java.time.LocalTime fin) {

        if (inicio == null || fin == null) {
            throw new IllegalArgumentException("La hora de inicio y fin son obligatorias.");
        }

        if (!fin.isAfter(inicio)) {
            throw new IllegalArgumentException("La hora de fin debe ser posterior a la hora de inicio.");
        }
    }

    private String generarCodigoSesion() {

        long siguiente = sesionRepository.count() + 1;

        return String.format("SES-%d-%06d", Year.now().getValue(), siguiente);
    }
}
