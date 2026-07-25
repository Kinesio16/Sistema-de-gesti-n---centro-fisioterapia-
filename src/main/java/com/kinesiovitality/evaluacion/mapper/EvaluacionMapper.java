package com.kinesiovitality.evaluacion.mapper;

import com.kinesiovitality.evaluacion.dto.EvaluacionRequest;
import com.kinesiovitality.evaluacion.dto.EvaluacionResponse;
import com.kinesiovitality.evaluacion.model.Evaluacion;
import com.kinesiovitality.fisioterapeuta.model.Fisioterapeuta;
import com.kinesiovitality.paciente.model.Paciente;

public class EvaluacionMapper {

    private EvaluacionMapper() {
    }

    public static Evaluacion toEntity(
            EvaluacionRequest request,
            Paciente paciente,
            Fisioterapeuta fisioterapeuta) {

        Evaluacion evaluacion = new Evaluacion();

        evaluacion.setPaciente(paciente);
        evaluacion.setFisioterapeuta(fisioterapeuta);

        evaluacion.setFechaEvaluacion(request.getFechaEvaluacion());

        evaluacion.setMotivoConsulta(request.getMotivoConsulta());
        evaluacion.setAntecedentes(request.getAntecedentes());

        evaluacion.setEscalaDolorEva(request.getEscalaDolorEva());

        evaluacion.setDiagnosticoFisioterapeutico(request.getDiagnosticoFisioterapeutico());

        evaluacion.setObjetivosTratamiento(request.getObjetivosTratamiento());

        evaluacion.setInspeccion(request.getInspeccion());
        evaluacion.setPalpacion(request.getPalpacion());
        evaluacion.setRangoMovimiento(request.getRangoMovimiento());
        evaluacion.setFuerzaMuscular(request.getFuerzaMuscular());
        evaluacion.setPruebasFuncionales(request.getPruebasFuncionales());

        evaluacion.setSesionesRecomendadas(request.getSesionesRecomendadas());
        evaluacion.setFrecuenciaSemanal(request.getFrecuenciaSemanal());

        evaluacion.setTratamientoSugerido(request.getTratamientoSugerido());

        evaluacion.setObservaciones(request.getObservaciones());

        return evaluacion;
    }

    public static EvaluacionResponse toResponse(Evaluacion evaluacion) {

        EvaluacionResponse response = new EvaluacionResponse();

        response.setId(evaluacion.getId());

        response.setPacienteId(evaluacion.getPaciente().getId());
        
        response.setCodigoEvaluacion(
                evaluacion.getCodigoEvaluacion());

        response.setPacienteNombre(
                evaluacion.getPaciente().getNombres()
                + " "
                + evaluacion.getPaciente().getApellidos());

        response.setFisioterapeutaId(
                evaluacion.getFisioterapeuta().getId());

        response.setFisioterapeutaNombre(
                evaluacion.getFisioterapeuta().getNombres()
                + " "
                + evaluacion.getFisioterapeuta().getApellidos());

        response.setFechaEvaluacion(
                evaluacion.getFechaEvaluacion());

        response.setMotivoConsulta(
                evaluacion.getMotivoConsulta());

        response.setAntecedentes(
                evaluacion.getAntecedentes());

        response.setEscalaDolorEva(
                evaluacion.getEscalaDolorEva());

        response.setDiagnosticoFisioterapeutico(
                evaluacion.getDiagnosticoFisioterapeutico());

        response.setObjetivosTratamiento(
                evaluacion.getObjetivosTratamiento());

        response.setInspeccion(
                evaluacion.getInspeccion());

        response.setPalpacion(
                evaluacion.getPalpacion());

        response.setRangoMovimiento(
                evaluacion.getRangoMovimiento());

        response.setFuerzaMuscular(
                evaluacion.getFuerzaMuscular());

        response.setPruebasFuncionales(
                evaluacion.getPruebasFuncionales());

        response.setSesionesRecomendadas(
                evaluacion.getSesionesRecomendadas());

        response.setFrecuenciaSemanal(
                evaluacion.getFrecuenciaSemanal());

        response.setTratamientoSugerido(
                evaluacion.getTratamientoSugerido());

        response.setObservaciones(
                evaluacion.getObservaciones());

        response.setEstado(
                evaluacion.getEstado());

        response.setFechaCreacion(
                evaluacion.getFechaCreacion());

        response.setFechaActualizacion(
                evaluacion.getFechaActualizacion());

        return response;
    }

}
