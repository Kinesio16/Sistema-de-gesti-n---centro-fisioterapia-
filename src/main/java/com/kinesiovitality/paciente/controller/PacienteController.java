package com.kinesiovitality.paciente.controller;

import java.util.List;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kinesiovitality.common.response.ApiResponseDTO;
import com.kinesiovitality.paciente.dto.PacienteRequest;
import com.kinesiovitality.paciente.dto.PacienteResponse;
import com.kinesiovitality.paciente.mapper.PacienteMapper;
import com.kinesiovitality.paciente.model.Paciente;
import com.kinesiovitality.paciente.service.PacienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/pacientes")
@Validated
@Tag(
	    name = "Pacientes",
	    description = "Gestión de pacientes del centro de fisioterapia."
	)
	@SecurityRequirement(name = "Bearer Authentication")
public class PacienteController {

    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }
    
    @Operation(
    	    summary = "Listar pacientes",
    	    description = "Obtiene el listado completo de pacientes activos."
    	)
    	@ApiResponses(value = {
    	    @ApiResponse(responseCode = "200", description = "Listado obtenido correctamente"),
    	    @ApiResponse(responseCode = "401", description = "No autenticado")
    	})
    @GetMapping
    public ResponseEntity<ApiResponseDTO<List<PacienteResponse>>> listar() {

        List<Paciente> pacientes = pacienteService.listar();

        List<PacienteResponse> response = pacientes.stream()
                .map(PacienteMapper::toResponse)
                .toList();

        ApiResponseDTO<List<PacienteResponse>> apiResponse = new ApiResponseDTO<>();
        apiResponse.setSuccess(true);
        apiResponse.setMessage("Pacientes obtenidos correctamente.");
        apiResponse.setData(response);

        return ResponseEntity.ok(apiResponse);
    }
    
    @Operation(
    	    summary = "Buscar paciente por ID",
    	    description = "Obtiene la información de un paciente mediante su identificador."
    	)
    	@ApiResponses(value = {
    	    @ApiResponse(responseCode = "200", description = "Paciente encontrado"),
    	    @ApiResponse(responseCode = "404", description = "Paciente no encontrado")
    	})
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<PacienteResponse>> buscarPorId(@Parameter(description = "ID del paciente")
    @PathVariable Long id) {

        Paciente paciente = pacienteService.buscarPorId(id);

        ApiResponseDTO<PacienteResponse> response = new ApiResponseDTO<>();
        response.setSuccess(true);
        response.setMessage("Paciente encontrado.");
        response.setData(PacienteMapper.toResponse(paciente));

        return ResponseEntity.ok(response);
    }
    
    @Operation(
    	    summary = "Registrar paciente",
    	    description = "Registra un nuevo paciente en el sistema."
    	)
    	@ApiResponses(value = {
    	    @ApiResponse(responseCode = "201", description = "Paciente registrado correctamente"),
    	    @ApiResponse(responseCode = "400", description = "Datos inválidos"),
    	    @ApiResponse(responseCode = "401", description = "No autenticado")
    	})
    @PostMapping
    public ResponseEntity<ApiResponseDTO<PacienteResponse>> guardar(
            @Valid @RequestBody PacienteRequest request) {

        Paciente paciente = PacienteMapper.toEntity(request);

        Paciente guardado = pacienteService.guardar(paciente);

        ApiResponseDTO<PacienteResponse> response = new ApiResponseDTO<>();
        response.setSuccess(true);
        response.setMessage("Paciente registrado correctamente.");
        response.setData(PacienteMapper.toResponse(guardado));

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @Operation(
    	    summary = "Actualizar paciente",
    	    description = "Actualiza la información de un paciente."
    	)
    	@ApiResponses(value = {
    	    @ApiResponse(responseCode = "200", description = "Paciente actualizado"),
    	    @ApiResponse(responseCode = "404", description = "Paciente no encontrado")
    	})
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<PacienteResponse>> actualizar(
    		@Parameter(description = "ID del paciente")
    		@PathVariable Long id,
            @Valid @RequestBody PacienteRequest request) {

        Paciente paciente = PacienteMapper.toEntity(request);

        Paciente actualizado = pacienteService.actualizar(id, paciente);

        ApiResponseDTO<PacienteResponse> response = new ApiResponseDTO<>();
        response.setSuccess(true);
        response.setMessage("Paciente actualizado correctamente.");
        response.setData(PacienteMapper.toResponse(actualizado));

        return ResponseEntity.ok(response);
    }

    @Operation(
    	    summary = "Inactivar paciente",
    	    description = "Realiza una eliminación lógica del paciente."
    	)
    	@ApiResponses(value = {
    	    @ApiResponse(responseCode = "200", description = "Paciente inactivado correctamente"),
    	    @ApiResponse(responseCode = "404", description = "Paciente no encontrado")
    	})
    @PatchMapping("/{id}/inactivar")
    public ResponseEntity<ApiResponseDTO<Void>> inactivar(@Parameter(description = "ID del paciente")
    @PathVariable Long id) {

        pacienteService.eliminar(id);

        ApiResponseDTO<Void> response = new ApiResponseDTO<>();
        response.setSuccess(true);
        response.setMessage("Paciente inactivado correctamente.");
        response.setData(null);

        return ResponseEntity.ok(response);
    }

}