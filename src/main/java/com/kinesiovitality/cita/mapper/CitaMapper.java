package com.kinesiovitality.cita.mapper;

import java.time.Duration;

import com.kinesiovitality.cita.dto.CitaRequest;
import com.kinesiovitality.cita.dto.CitaResponse;
import com.kinesiovitality.cita.model.Cita;
import com.kinesiovitality.paciente.model.Paciente;
import com.kinesiovitality.fisioterapeuta.model.Fisioterapeuta;

public class CitaMapper {

    private CitaMapper() {
    }

    /**
     * Convierte el Request en una entidad.
     * El paciente y el fisioterapeuta llegan ya buscados desde el Service.
     */
    public static Cita toEntity(
            CitaRequest request,
            Paciente paciente,
            Fisioterapeuta fisioterapeuta) {

        Cita cita = new Cita();

        cita.setPaciente(paciente);
        cita.setFisioterapeuta(fisioterapeuta);

        cita.setFecha(request.getFecha());

        cita.setHoraInicio(request.getHoraInicio());
        cita.setHoraFin(request.getHoraFin());

        cita.setTipoTerapia(request.getTipoTerapia());

        cita.setMotivoConsulta(request.getMotivoConsulta());

        cita.setObservaciones(request.getObservaciones());

        // Calcula automáticamente la duración
        long minutos = Duration.between(
                request.getHoraInicio(),
                request.getHoraFin())
                .toMinutes();

        cita.setDuracionMinutos((int) minutos);

        return cita;
    }

    /**
     * Convierte una entidad en Response.
     */
    public static CitaResponse toResponse(Cita cita) {

        CitaResponse response = new CitaResponse();

        response.setId(cita.getId());

        response.setPacienteId(cita.getPaciente().getId());

        response.setPacienteNombre(
                cita.getPaciente().getNombres() + " "
                        + cita.getPaciente().getApellidos());

        response.setFisioterapeutaId(
                cita.getFisioterapeuta().getId());

        response.setFisioterapeutaNombre(
                cita.getFisioterapeuta().getNombres() + " "
                        + cita.getFisioterapeuta().getApellidos());

        response.setFecha(cita.getFecha());

        response.setHoraInicio(cita.getHoraInicio());

        response.setHoraFin(cita.getHoraFin());

        response.setDuracionMinutos(cita.getDuracionMinutos());

        response.setTipoTerapia(cita.getTipoTerapia());

        response.setMotivoConsulta(cita.getMotivoConsulta());

        response.setObservaciones(cita.getObservaciones());

        response.setEstado(cita.getEstado());

        response.setFechaCreacion(cita.getFechaCreacion());

        response.setFechaActualizacion(cita.getFechaActualizacion());

        return response;
    }

}