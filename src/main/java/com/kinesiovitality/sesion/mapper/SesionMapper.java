package com.kinesiovitality.sesion.mapper;

import com.kinesiovitality.sesion.dto.SesionRequest;
import com.kinesiovitality.sesion.dto.SesionResponse;
import com.kinesiovitality.sesion.model.Sesion;

public class SesionMapper {

    private SesionMapper() {
    }

    public static Sesion toEntity(SesionRequest request) {

        Sesion sesion = new Sesion();

        sesion.setFechaSesion(request.getFechaSesion());
        sesion.setHoraInicio(request.getHoraInicio());
        sesion.setHoraFin(request.getHoraFin());

        sesion.setEvolucionClinica(request.getEvolucionClinica());
        sesion.setObservaciones(request.getObservaciones());
        sesion.setProximaSesionObservacion(request.getProximaSesionObservacion());

        sesion.setEvaAntes(request.getEvaAntes());
        sesion.setEvaDespues(request.getEvaDespues());

        sesion.setTecnicasAplicadas(request.getTecnicasAplicadas());
        sesion.setProximaSesion(request.getProximaSesion());

        return sesion;
    }

    public static SesionResponse toResponse(Sesion sesion) {

        SesionResponse response = new SesionResponse();

        response.setId(sesion.getId());
        response.setCodigoSesion(sesion.getCodigoSesion());

        response.setTratamientoId(sesion.getTratamiento().getId());
        response.setCodigoTratamiento(sesion.getTratamiento().getCodigoTratamiento());

        response.setPacienteId(sesion.getTratamiento().getPaciente().getId());
        response.setNombrePaciente(
                sesion.getTratamiento().getPaciente().getNombres() + " "
                        + sesion.getTratamiento().getPaciente().getApellidos());

        response.setFisioterapeutaId(sesion.getTratamiento().getFisioterapeuta().getId());
        response.setNombreFisioterapeuta(
                sesion.getTratamiento().getFisioterapeuta().getNombres() + " "
                        + sesion.getTratamiento().getFisioterapeuta().getApellidos());

        response.setFechaSesion(sesion.getFechaSesion());
        response.setHoraInicio(sesion.getHoraInicio());
        response.setHoraFin(sesion.getHoraFin());
        response.setDuracionMinutos(sesion.getDuracionMinutos());

        response.setEstado(sesion.getEstado());

        response.setEvolucionClinica(sesion.getEvolucionClinica());
        response.setObservaciones(sesion.getObservaciones());
        response.setProximaSesionObservacion(sesion.getProximaSesionObservacion());

        response.setEvaAntes(sesion.getEvaAntes());
        response.setEvaDespues(sesion.getEvaDespues());

        response.setTecnicasAplicadas(sesion.getTecnicasAplicadas());
        response.setProximaSesion(sesion.getProximaSesion());

        response.setFechaCreacion(sesion.getFechaCreacion());
        response.setFechaActualizacion(sesion.getFechaActualizacion());

        return response;
    }
}