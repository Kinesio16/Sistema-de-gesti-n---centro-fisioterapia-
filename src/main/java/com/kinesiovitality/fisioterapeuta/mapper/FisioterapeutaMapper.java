package com.kinesiovitality.fisioterapeuta.mapper;


import com.kinesiovitality.fisioterapeuta.dto.FisioterapeutaRequest;
import com.kinesiovitality.fisioterapeuta.dto.FisioterapeutaResponse;
import com.kinesiovitality.fisioterapeuta.model.Fisioterapeuta;

public class FisioterapeutaMapper {
	
	private FisioterapeutaMapper() {
		
	}
	
	
	public static Fisioterapeuta toEntity(FisioterapeutaRequest request) {
		
		Fisioterapeuta fisio = new Fisioterapeuta();
		
		fisio.setNombres(request.getNombres());
		fisio.setApellidos(request.getApellidos());
		fisio.setCedula(request.getCedula());
		fisio.setCelular(request.getCelular());
		fisio.setCorreo(request.getCorreo());
		fisio.setEspecialidad(request.getEspecialidad());
		fisio.setNumeroLicencia(request.getNumeroLicencia());
		
		return fisio;
		
	}
	
	public static FisioterapeutaResponse toResponse(Fisioterapeuta fisioterapeuta) {
		
		
		FisioterapeutaResponse response = new FisioterapeutaResponse();
		
		response.setId(fisioterapeuta.getId());
		response.setNombres(fisioterapeuta.getNombres());
		response.setApellidos(fisioterapeuta.getApellidos());
		response.setCedula(fisioterapeuta.getCedula());
		response.setCelular(fisioterapeuta.getCelular());
		response.setCorreo(fisioterapeuta.getCorreo());
		response.setEspecialidad(fisioterapeuta.getEspecialidad());
		response.setNumeroLicencia(fisioterapeuta.getNumeroLicencia());
		response.setEstado(fisioterapeuta.getEstado());
		response.setFechaCreacion(fisioterapeuta.getFechaCreacion());
		response.setFechaActualizacion(fisioterapeuta.getFechaActualizacion());
		
		return response;
	}
}
