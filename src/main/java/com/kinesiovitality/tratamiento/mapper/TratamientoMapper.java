package com.kinesiovitality.tratamiento.mapper;

import com.kinesiovitality.tratamiento.dto.TratamientoRequest;
import com.kinesiovitality.tratamiento.dto.TratamientoResponse;
import com.kinesiovitality.tratamiento.model.Tratamiento;

public class TratamientoMapper {

    private TratamientoMapper() {
    }

    public static Tratamiento toEntity(TratamientoRequest request) {

        Tratamiento tratamiento = new Tratamiento();

        tratamiento.setFechaInicio(request.getFechaInicio());
        tratamiento.setFechaEstimadaAlta(request.getFechaEstimadaAlta());

        tratamiento.setObjetivoGeneral(request.getObjetivoGeneral());
        tratamiento.setObjetivosEspecificos(request.getObjetivosEspecificos());

        tratamiento.setDiagnostico(request.getDiagnostico());
        tratamiento.setTratamientoPropuesto(request.getTratamientoPropuesto());

        tratamiento.setTecnicas(request.getTecnicas());

        tratamiento.setSesionesPlanificadas(request.getSesionesPlanificadas());

        tratamiento.setFrecuenciaSemanal(request.getFrecuenciaSemanal());

        tratamiento.setObservacionesIniciales(request.getObservacionesIniciales());

        return tratamiento;
    }

    public static TratamientoResponse toResponse(Tratamiento tratamiento) {

        TratamientoResponse response = new TratamientoResponse();

        response.setId(tratamiento.getId());

        response.setCodigoTratamiento(tratamiento.getCodigoTratamiento());

        response.setPacienteId(tratamiento.getPaciente().getId());

        response.setNombrePaciente(
                tratamiento.getPaciente().getNombres() + " "
                        + tratamiento.getPaciente().getApellidos());

        response.setFisioterapeutaId(
                tratamiento.getFisioterapeuta().getId());

        response.setNombreFisioterapeuta(
                tratamiento.getFisioterapeuta().getNombres() + " "
                        + tratamiento.getFisioterapeuta().getApellidos());

        response.setEvaluacionId(
                tratamiento.getEvaluacion().getId());

        response.setFechaInicio(tratamiento.getFechaInicio());

        response.setFechaEstimadaAlta(
                tratamiento.getFechaEstimadaAlta());

        response.setFechaAlta(
                tratamiento.getFechaAlta());

        response.setObjetivoGeneral(
                tratamiento.getObjetivoGeneral());

        response.setObjetivosEspecificos(
                tratamiento.getObjetivosEspecificos());

        response.setDiagnostico(
                tratamiento.getDiagnostico());

        response.setTratamientoPropuesto(
                tratamiento.getTratamientoPropuesto());

        response.setTecnicas(
                tratamiento.getTecnicas());

        response.setSesionesPlanificadas(
                tratamiento.getSesionesPlanificadas());

        response.setSesionesRealizadas(
                tratamiento.getSesionesRealizadas());
        
        response.setSesionesPendientes(
                tratamiento.getSesionesPendientes());

        response.setFrecuenciaSemanal(
                tratamiento.getFrecuenciaSemanal());

        response.setPorcentajeAvance(
                tratamiento.getPorcentajeAvance());

        response.setEstado(
                tratamiento.getEstado());

        response.setObservacionesIniciales(
                tratamiento.getObservacionesIniciales());

        response.setObservacionesFinales(
                tratamiento.getObservacionesFinales());

        response.setFechaCreacion(
                tratamiento.getFechaCreacion());

        response.setFechaActualizacion(
                tratamiento.getFechaActualizacion());

        return response;
    }

}
