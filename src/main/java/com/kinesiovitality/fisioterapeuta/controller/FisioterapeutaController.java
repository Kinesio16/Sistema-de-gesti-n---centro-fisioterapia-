package com.kinesiovitality.fisioterapeuta.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kinesiovitality.common.response.ApiResponse;
import com.kinesiovitality.fisioterapeuta.dto.FisioterapeutaRequest;
import com.kinesiovitality.fisioterapeuta.dto.FisioterapeutaResponse;
import com.kinesiovitality.fisioterapeuta.mapper.FisioterapeutaMapper;
import com.kinesiovitality.fisioterapeuta.model.Fisioterapeuta;
import com.kinesiovitality.fisioterapeuta.service.FisioterapeutaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/fisioterapeutas")
@Validated
public class FisioterapeutaController {
	
	private final FisioterapeutaService fisioterapeutaService;
	
	public FisioterapeutaController(FisioterapeutaService fisioterapeutaService) {
		this.fisioterapeutaService = fisioterapeutaService;
	}
	
	@GetMapping
	public ResponseEntity<ApiResponse<List<FisioterapeutaResponse>>> listar(){
		
		List<Fisioterapeuta> fisio = fisioterapeutaService.listar();
		
		List<FisioterapeutaResponse> response = fisio.stream()
				.map(FisioterapeutaMapper :: toResponse).toList();
		
		ApiResponse<List<FisioterapeutaResponse>> apiResponse = new ApiResponse<>();
		apiResponse.setSuccess(true);
		apiResponse.setMessage("Fisioterapeutas obtenidos correctamente");
		apiResponse.setData(response);
		
		return ResponseEntity.ok(apiResponse);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<FisioterapeutaResponse>> buscarPorId(@PathVariable Long id){
		
		Fisioterapeuta fisio = fisioterapeutaService.buscarPorId(id);
		
		ApiResponse<FisioterapeutaResponse> response = new ApiResponse<>();
		response.setSuccess(true);
        response.setMessage("Fisioterapeuta encontrado.");
        response.setData(FisioterapeutaMapper.toResponse(fisio));
        
        return ResponseEntity.ok(response);
		
	}
	
	@PostMapping
	public ResponseEntity<ApiResponse<FisioterapeutaResponse>> guardar(@Valid @RequestBody FisioterapeutaRequest request){
		
		Fisioterapeuta fisio = FisioterapeutaMapper.toEntity(request);
		
		Fisioterapeuta guardado = fisioterapeutaService.guardar(fisio);
		
		ApiResponse<FisioterapeutaResponse> response = new ApiResponse<>();
		response.setSuccess(true);
        response.setMessage("Fisioterapeuta registrado correctamente.");
        response.setData(FisioterapeutaMapper.toResponse(guardado));
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<FisioterapeutaResponse>> actualizar(
			@PathVariable Long id, @Valid @RequestBody FisioterapeutaRequest request){
		
		Fisioterapeuta fisio = FisioterapeutaMapper.toEntity(request);
		
		Fisioterapeuta actualizado = fisioterapeutaService.actualizar(id, fisio);
		
		ApiResponse<FisioterapeutaResponse> response = new ApiResponse<>();
		response.setSuccess(true);
        response.setMessage("Fisioterapeuta actualizado correctamente.");
        response.setData(FisioterapeutaMapper.toResponse(actualizado));
        
        return ResponseEntity.ok(response);
	}
	
	@PatchMapping("/{id}/inactivar")
	public ResponseEntity<ApiResponse<Void>> inactivar(@PathVariable Long id){
		
		fisioterapeutaService.eliminar(id);
		
		ApiResponse<Void> response = new ApiResponse<>();
		response.setSuccess(true);
		response.setMessage("Fisioterapeuta inactivado  correctamente.");
		response.setData(null);
		
		return ResponseEntity.ok(response);
	}
}
