package com.kinesiovitality.cita.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kinesiovitality.cita.dto.CitaRequest;
import com.kinesiovitality.cita.dto.CitaResponse;
import com.kinesiovitality.cita.mapper.CitaMapper;
import com.kinesiovitality.cita.model.Cita;
import com.kinesiovitality.cita.service.CitaService;
import com.kinesiovitality.common.response.ApiResponseDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/citas")
@Validated
@Tag(
	    name = "Citas",
	    description = "Gestión de citas entre pacientes y fisioterapeutas."
	)
public class CitaController {

    private final CitaService citaService;

    public CitaController(CitaService citaService) {
        this.citaService = citaService;
    }

    @Operation(
    	    summary = "Listar citas",
    	    description = "Obtiene el listado completo de citas registradas."
    	)
    	@ApiResponses({
    	    @ApiResponse(responseCode = "200", description = "Listado obtenido correctamente"),
    	    @ApiResponse(responseCode = "401", description = "No autenticado"),
    	    @ApiResponse(responseCode = "403", description = "Sin permisos")
    	})
    @GetMapping
    public ResponseEntity<ApiResponseDTO<List<CitaResponse>>> listar() {

        List<Cita> citas = citaService.listar();

        List<CitaResponse> response = citas.stream()
                .map(CitaMapper::toResponse)
                .toList();

        ApiResponseDTO<List<CitaResponse>> apiResponse = new ApiResponseDTO<>();
        apiResponse.setSuccess(true);
        apiResponse.setMessage("Citas obtenidas correctamente.");
        apiResponse.setData(response);

        return ResponseEntity.ok(apiResponse);
    }

    @Operation(
    	    summary = "Buscar cita",
    	    description = "Obtiene una cita mediante su identificador."
    	)
    	@ApiResponses({
    	    @ApiResponse(responseCode = "200", description = "Cita encontrada"),
    	    @ApiResponse(responseCode = "404", description = "Cita no encontrada")
    	})
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<CitaResponse>> buscarPorId(
            @PathVariable Long id) {

        Cita cita = citaService.buscarPorId(id);

        ApiResponseDTO<CitaResponse> response = new ApiResponseDTO<>();
        response.setSuccess(true);
        response.setMessage("Cita encontrada.");
        response.setData(CitaMapper.toResponse(cita));

        return ResponseEntity.ok(response);
    }

    @Operation(
    	    summary = "Registrar cita",
    	    description = "Registra una nueva cita."
    	)
    	@ApiResponses({
    	    @ApiResponse(responseCode = "201", description = "Cita registrada"),
    	    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    	})
    @PostMapping
    public ResponseEntity<ApiResponseDTO<CitaResponse>> guardar(
            @Valid @RequestBody CitaRequest request) {

        Cita guardada = citaService.guardar(request);

        ApiResponseDTO<CitaResponse> response = new ApiResponseDTO<>();
        response.setSuccess(true);
        response.setMessage("Cita registrada correctamente.");
        response.setData(CitaMapper.toResponse(guardada));

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(
    	    summary = "Actualizar cita",
    	    description = "Actualiza la información de una cita."
    	)
    	@ApiResponses({
    	    @ApiResponse(responseCode = "200", description = "Cita actualizada"),
    	    @ApiResponse(responseCode = "404", description = "Cita no encontrada")
    	})
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<CitaResponse>> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody CitaRequest request) {

        Cita actualizada = citaService.actualizar(id, request);

        ApiResponseDTO<CitaResponse> response = new ApiResponseDTO<>();
        response.setSuccess(true);
        response.setMessage("Cita actualizada correctamente.");
        response.setData(CitaMapper.toResponse(actualizada));

        return ResponseEntity.ok(response);
    }
    
    @Operation(
    	    summary = "Cancelar cita",
    	    description = "Marca una cita como cancelada o inactiva."
    	)
    	@ApiResponses({
    	    @ApiResponse(responseCode = "200", description = "Cita cancelada"),
    	    @ApiResponse(responseCode = "404", description = "Cita no encontrada")
    	})
    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<ApiResponseDTO<Void>> cancelar(
            @PathVariable Long id) {

        citaService.cancelar(id);

        ApiResponseDTO<Void> response = new ApiResponseDTO<>();
        response.setSuccess(true);
        response.setMessage("Cita cancelada correctamente.");
        response.setData(null);

        return ResponseEntity.ok(response);
    }

}
