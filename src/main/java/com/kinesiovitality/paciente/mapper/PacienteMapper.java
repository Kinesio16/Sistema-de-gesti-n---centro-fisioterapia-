package com.kinesiovitality.paciente.mapper;

import com.kinesiovitality.common.enums.EstadoRegistro;
import com.kinesiovitality.paciente.dto.PacienteRequest;
import com.kinesiovitality.paciente.dto.PacienteResponse;
import com.kinesiovitality.paciente.model.Paciente;

public class PacienteMapper {

    private PacienteMapper() {
    }

    public static Paciente toEntity(PacienteRequest request) {

        Paciente paciente = new Paciente();

        paciente.setNombres(request.getNombres());
        paciente.setApellidos(request.getApellidos());
        paciente.setCedula(request.getCedula());
        paciente.setFechaNacimiento(request.getFechaNacimiento());
        paciente.setSexo(request.getSexo());
        paciente.setCelular(request.getCelular());
        paciente.setCorreo(request.getCorreo());
        paciente.setDireccion(request.getDireccion());
        paciente.setCiudad(request.getCiudad());
        paciente.setTipoSangre(request.getTipoSangre());
        paciente.setAlergias(request.getAlergias());
        paciente.setEnfermedades(request.getEnfermedades());
        paciente.setObservaciones(request.getObservaciones());
        paciente.setContactoEmergencia(request.getContactoEmergencia());
        paciente.setParentescoContacto(request.getParentescoContacto());
        paciente.setTelefonoContacto(request.getTelefonoContacto());

        paciente.setEstado(EstadoRegistro.ACTIVO);

        return paciente;
    }

    public static PacienteResponse toResponse(Paciente paciente) {

        PacienteResponse response = new PacienteResponse();

        response.setId(paciente.getId());
        response.setNombres(paciente.getNombres());
        response.setApellidos(paciente.getApellidos());
        response.setCedula(paciente.getCedula());
        response.setFechaNacimiento(paciente.getFechaNacimiento());
        response.setSexo(paciente.getSexo());
        response.setCelular(paciente.getCelular());
        response.setCorreo(paciente.getCorreo());
        response.setEstado(paciente.getEstado());
        response.setFechaCreacion(paciente.getFechaCreacion());

        return response;
    }

}
