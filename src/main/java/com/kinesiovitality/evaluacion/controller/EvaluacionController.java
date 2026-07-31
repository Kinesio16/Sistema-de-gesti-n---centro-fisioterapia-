package com.kinesiovitality.evaluacion.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kinesiovitality.common.response.ApiResponseDTO;
import com.kinesiovitality.evaluacion.dto.EvaluacionRequest;
import com.kinesiovitality.evaluacion.dto.EvaluacionResponse;
import com.kinesiovitality.evaluacion.mapper.EvaluacionMapper;
import com.kinesiovitality.evaluacion.model.Evaluacion;
import com.kinesiovitality.evaluacion.service.EvaluacionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/evaluaciones")
@Validated
@Tag(
	    name = "Evaluaciones",
	    description = "Gestión de evaluaciones fisioterapéuticas realizadas a los pacientes."
	)
	@SecurityRequirement(name = "Bearer Authentication")
public class EvaluacionController {

    private final EvaluacionService evaluacionService;

    public EvaluacionController(EvaluacionService evaluacionService) {
        this.evaluacionService = evaluacionService;
    }

    @Operation(
    	    summary = "Listar evaluaciones",
    	    description = "Obtiene el listado completo de evaluaciones registradas."
    	)
    	@ApiResponses({
    	    @ApiResponse(responseCode = "200", description = "Listado obtenido correctamente"),
    	    @ApiResponse(responseCode = "401", description = "No autorizado")
    	})
    @GetMapping
    public ResponseEntity<ApiResponseDTO<List<EvaluacionResponse>>> listar() {

        List<Evaluacion> evaluaciones = evaluacionService.listar();

        List<EvaluacionResponse> response = evaluaciones.stream()
                .map(EvaluacionMapper::toResponse)
                .toList();

        ApiResponseDTO<List<EvaluacionResponse>> apiResponse = new ApiResponseDTO<>();
        apiResponse.setSuccess(true);
        apiResponse.setMessage("Evaluaciones obtenidas correctamente.");
        apiResponse.setData(response);

        return ResponseEntity.ok(apiResponse);
    }

    @Operation(
    	    summary = "Buscar evaluación",
    	    description = "Obtiene una evaluación mediante su identificador."
    	)
    	@ApiResponses({
    	    @ApiResponse(responseCode = "200", description = "Evaluación encontrada"),
    	    @ApiResponse(responseCode = "404", description = "Evaluación no encontrada")
    	})
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<EvaluacionResponse>> buscarPorId(
            @PathVariable Long id) {

        Evaluacion evaluacion = evaluacionService.buscarPorId(id);

        ApiResponseDTO<EvaluacionResponse> response = new ApiResponseDTO<>();
        response.setSuccess(true);
        response.setMessage("Evaluación encontrada.");
        response.setData(EvaluacionMapper.toResponse(evaluacion));

        return ResponseEntity.ok(response);
    }

    @Operation(
    	    summary = "Registrar evaluación",
    	    description = "Registra una nueva evaluación fisioterapéutica para un paciente."
    	)
    	@ApiResponses({
    	    @ApiResponse(responseCode = "201", description = "Evaluación registrada correctamente"),
    	    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    	})
    @PostMapping
    public ResponseEntity<ApiResponseDTO<EvaluacionResponse>> guardar(
            @Valid @RequestBody EvaluacionRequest request) {

        Evaluacion guardada = evaluacionService.guardar(request);

        ApiResponseDTO<EvaluacionResponse> response = new ApiResponseDTO<>();
        response.setSuccess(true);
        response.setMessage("Evaluación registrada correctamente.");
        response.setData(EvaluacionMapper.toResponse(guardada));

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(
    	    summary = "Actualizar evaluación",
    	    description = "Actualiza la información de una evaluación."
    	)
    	@ApiResponses({
    	    @ApiResponse(responseCode = "200", description = "Evaluación actualizada"),
    	    @ApiResponse(responseCode = "404", description = "Evaluación no encontrada")
    	})
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<EvaluacionResponse>> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody EvaluacionRequest request) {

        Evaluacion actualizada = evaluacionService.actualizar(id, request);

        ApiResponseDTO<EvaluacionResponse> response = new ApiResponseDTO<>();
        response.setSuccess(true);
        response.setMessage("Evaluación actualizada correctamente.");
        response.setData(EvaluacionMapper.toResponse(actualizada));

        return ResponseEntity.ok(response);
    }

    @Operation(
    	    summary = "Inactivar evaluación",
    	    description = "Realiza la eliminación lógica de una evaluación."
    	)
    	@ApiResponses({
    	    @ApiResponse(responseCode = "200", description = "Evaluación inactivada"),
    	    @ApiResponse(responseCode = "404", description = "Evaluación no encontrada")
    	})
    @PatchMapping("/{id}/inactivar")
    public ResponseEntity<ApiResponseDTO<Void>> inactivar(
            @PathVariable Long id) {

        evaluacionService.eliminar(id);

        ApiResponseDTO<Void> response = new ApiResponseDTO<>();
        response.setSuccess(true);
        response.setMessage("Evaluación inactivada correctamente.");
        response.setData(null);

        return ResponseEntity.ok(response);
    }

}
