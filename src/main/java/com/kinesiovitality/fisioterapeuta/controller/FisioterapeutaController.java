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

import com.kinesiovitality.common.response.ApiResponseDTO;
import com.kinesiovitality.fisioterapeuta.dto.FisioterapeutaRequest;
import com.kinesiovitality.fisioterapeuta.dto.FisioterapeutaResponse;
import com.kinesiovitality.fisioterapeuta.mapper.FisioterapeutaMapper;
import com.kinesiovitality.fisioterapeuta.model.Fisioterapeuta;
import com.kinesiovitality.fisioterapeuta.service.FisioterapeutaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/fisioterapeutas")
@Validated
@Tag(
	    name = "Fisioterapeutas",
	    description = "Gestión de fisioterapeutas del sistema."
	)
public class FisioterapeutaController {
	
	private final FisioterapeutaService fisioterapeutaService;
	
	public FisioterapeutaController(FisioterapeutaService fisioterapeutaService) {
		this.fisioterapeutaService = fisioterapeutaService;
	}
	
	@Operation(
		    summary = "Listar fisioterapeutas",
		    description = "Obtiene todos los fisioterapeutas registrados."
		)
		@ApiResponses({
		    @ApiResponse(responseCode = "200", description = "Listado obtenido correctamente"),
		    @ApiResponse(responseCode = "401", description = "No autenticado"),
		    @ApiResponse(responseCode = "403", description = "No autorizado")
		})
		@SecurityRequirement(name = "Bearer Authentication")
	@GetMapping
	public ResponseEntity<ApiResponseDTO<List<FisioterapeutaResponse>>> listar(){
		
		List<Fisioterapeuta> fisio = fisioterapeutaService.listar();
		
		List<FisioterapeutaResponse> response = fisio.stream()
				.map(FisioterapeutaMapper :: toResponse).toList();
		
		ApiResponseDTO<List<FisioterapeutaResponse>> apiResponse = new ApiResponseDTO<>();
		apiResponse.setSuccess(true);
		apiResponse.setMessage("Fisioterapeutas obtenidos correctamente");
		apiResponse.setData(response);
		
		return ResponseEntity.ok(apiResponse);
	}
	
	@Operation(
		    summary = "Buscar fisioterapeuta",
		    description = "Obtiene un fisioterapeuta mediante su identificador."
		)
		@ApiResponses({
		    @ApiResponse(responseCode = "200", description = "Fisioterapeuta encontrado"),
		    @ApiResponse(responseCode = "404", description = "Fisioterapeuta no encontrado")
		})
		@SecurityRequirement(name = "Bearer Authentication")
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponseDTO<FisioterapeutaResponse>> buscarPorId(@PathVariable Long id){
		
		Fisioterapeuta fisio = fisioterapeutaService.buscarPorId(id);
		
		ApiResponseDTO<FisioterapeutaResponse> response = new ApiResponseDTO<>();
		response.setSuccess(true);
        response.setMessage("Fisioterapeuta encontrado.");
        response.setData(FisioterapeutaMapper.toResponse(fisio));
        
        return ResponseEntity.ok(response);
		
	}
	
	@Operation(
		    summary = "Registrar fisioterapeuta",
		    description = "Registra un nuevo fisioterapeuta."
		)
		@ApiResponses({
		    @ApiResponse(responseCode = "201", description = "Fisioterapeuta registrado"),
		    @ApiResponse(responseCode = "400", description = "Datos inválidos")
		})
		@SecurityRequirement(name = "Bearer Authentication")
	@PostMapping
	public ResponseEntity<ApiResponseDTO<FisioterapeutaResponse>> guardar(@Valid @RequestBody FisioterapeutaRequest request){
		
		Fisioterapeuta fisio = FisioterapeutaMapper.toEntity(request);
		
		Fisioterapeuta guardado = fisioterapeutaService.guardar(fisio);
		
		ApiResponseDTO<FisioterapeutaResponse> response = new ApiResponseDTO<>();
		response.setSuccess(true);
        response.setMessage("Fisioterapeuta registrado correctamente.");
        response.setData(FisioterapeutaMapper.toResponse(guardado));
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@Operation(
		    summary = "Actualizar fisioterapeuta",
		    description = "Actualiza la información de un fisioterapeuta."
		)
		@ApiResponses({
		    @ApiResponse(responseCode = "200", description = "Actualizado correctamente"),
		    @ApiResponse(responseCode = "404", description = "No encontrado")
		})
		@SecurityRequirement(name = "Bearer Authentication")
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponseDTO<FisioterapeutaResponse>> actualizar(
			@PathVariable Long id, @Valid @RequestBody FisioterapeutaRequest request){
		
		Fisioterapeuta fisio = FisioterapeutaMapper.toEntity(request);
		
		Fisioterapeuta actualizado = fisioterapeutaService.actualizar(id, fisio);
		
		ApiResponseDTO<FisioterapeutaResponse> response = new ApiResponseDTO<>();
		response.setSuccess(true);
        response.setMessage("Fisioterapeuta actualizado correctamente.");
        response.setData(FisioterapeutaMapper.toResponse(actualizado));
        
        return ResponseEntity.ok(response);
	}
	
	@Operation(
		    summary = "Inactivar fisioterapeuta",
		    description = "Realiza una eliminación lógica del fisioterapeuta."
		)
		@ApiResponses({
		    @ApiResponse(responseCode = "200", description = "Inactivado correctamente"),
		    @ApiResponse(responseCode = "404", description = "No encontrado")
		})
		@SecurityRequirement(name = "Bearer Authentication")
	@PatchMapping("/{id}/inactivar")
	public ResponseEntity<ApiResponseDTO<Void>> inactivar(@PathVariable Long id){
		
		fisioterapeutaService.eliminar(id);
		
		ApiResponseDTO<Void> response = new ApiResponseDTO<>();
		response.setSuccess(true);
		response.setMessage("Fisioterapeuta inactivado  correctamente.");
		response.setData(null);
		
		return ResponseEntity.ok(response);
	}
}
