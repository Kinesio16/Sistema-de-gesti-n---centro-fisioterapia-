package com.kinesiovitality.sesion.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kinesiovitality.common.response.ApiResponseDTO;
import com.kinesiovitality.sesion.dto.SesionRequest;
import com.kinesiovitality.sesion.dto.SesionResponse;
import com.kinesiovitality.sesion.mapper.SesionMapper;
import com.kinesiovitality.sesion.model.Sesion;
import com.kinesiovitality.sesion.service.SesionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/sesiones")
@Validated
@Tag(
	    name = "Sesiones",
	    description = "Gestión de las sesiones de tratamiento realizadas a los pacientes."
	)
	@SecurityRequirement(name = "Bearer Authentication")
public class SesionController {

    private final SesionService sesionService;

    public SesionController(SesionService sesionService) {
        this.sesionService = sesionService;
    }

    @Operation(
    	    summary = "Listar sesiones",
    	    description = "Obtiene el listado completo de sesiones registradas."
    	)
    	@ApiResponses({
    	    @ApiResponse(responseCode = "200", description = "Listado obtenido correctamente"),
    	    @ApiResponse(responseCode = "401", description = "No autorizado")
    	})
    @GetMapping
    public ResponseEntity<ApiResponseDTO<List<SesionResponse>>> listar() {

        List<SesionResponse> response = sesionService.listar()
                .stream()
                .map(SesionMapper::toResponse)
                .toList();

        ApiResponseDTO<List<SesionResponse>> apiResponse = new ApiResponseDTO<>();
        apiResponse.setSuccess(true);
        apiResponse.setMessage("Sesiones obtenidas correctamente.");
        apiResponse.setData(response);

        return ResponseEntity.ok(apiResponse);
    }

    @Operation(
    	    summary = "Buscar sesión",
    	    description = "Obtiene una sesión mediante su identificador."
    	)
    	@ApiResponses({
    	    @ApiResponse(responseCode = "200", description = "Sesión encontrada"),
    	    @ApiResponse(responseCode = "404", description = "Sesión no encontrada")
    	})
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<SesionResponse>> buscarPorId(
            @PathVariable Long id) {

        Sesion sesion = sesionService.buscarPorId(id);

        ApiResponseDTO<SesionResponse> response = new ApiResponseDTO<>();
        response.setSuccess(true);
        response.setMessage("Sesión encontrada.");
        response.setData(SesionMapper.toResponse(sesion));

        return ResponseEntity.ok(response);
    }

    @Operation(
    	    summary = "Registrar sesión",
    	    description = "Registra una nueva sesión correspondiente a un tratamiento."
    	)
    	@ApiResponses({
    	    @ApiResponse(responseCode = "201", description = "Sesión registrada correctamente"),
    	    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    	})
    @PostMapping
    public ResponseEntity<ApiResponseDTO<SesionResponse>> guardar(
            @Valid @RequestBody SesionRequest request) {

        Sesion sesion = SesionMapper.toEntity(request);

        Sesion guardada = sesionService.guardar(
                sesion,
                request.getTratamientoId());

        ApiResponseDTO<SesionResponse> response = new ApiResponseDTO<>();
        response.setSuccess(true);
        response.setMessage("Sesión registrada correctamente.");
        response.setData(SesionMapper.toResponse(guardada));

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(
    	    summary = "Actualizar sesión",
    	    description = "Actualiza la información de una sesión."
    	)
    	@ApiResponses({
    	    @ApiResponse(responseCode = "200", description = "Sesión actualizada"),
    	    @ApiResponse(responseCode = "404", description = "Sesión no encontrada")
    	})
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<SesionResponse>> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody SesionRequest request) {

        Sesion sesion = SesionMapper.toEntity(request);

        Sesion actualizada = sesionService.actualizar(
                id,
                sesion,
                request.getTratamientoId());

        ApiResponseDTO<SesionResponse> response = new ApiResponseDTO<>();
        response.setSuccess(true);
        response.setMessage("Sesión actualizada correctamente.");
        response.setData(SesionMapper.toResponse(actualizada));

        return ResponseEntity.ok(response);
    }

    @Operation(
    	    summary = "Finalizar sesión",
    	    description = "Marca una sesión como finalizada."
    	)
    	@ApiResponses({
    	    @ApiResponse(responseCode = "200", description = "Sesión finalizada"),
    	    @ApiResponse(responseCode = "404", description = "Sesión no encontrada")
    	})
    @PatchMapping("/{id}/realizada")
    public ResponseEntity<ApiResponseDTO<SesionResponse>> registrarRealizada(
            @PathVariable Long id) {

        Sesion actualizada = sesionService.registrarRealizada(id);

        ApiResponseDTO<SesionResponse> response = new ApiResponseDTO<>();
        response.setSuccess(true);
        response.setMessage("Sesión marcada como realizada correctamente.");
        response.setData(SesionMapper.toResponse(actualizada));

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/reprogramar")
    public ResponseEntity<ApiResponseDTO<SesionResponse>> reprogramar(
            @PathVariable Long id,
            @Valid @RequestBody SesionRequest request) {

        Sesion sesion = SesionMapper.toEntity(request);

        Sesion actualizada = sesionService.reprogramar(id, sesion);

        ApiResponseDTO<SesionResponse> response = new ApiResponseDTO<>();
        response.setSuccess(true);
        response.setMessage("Sesión reprogramada correctamente.");
        response.setData(SesionMapper.toResponse(actualizada));

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<ApiResponseDTO<SesionResponse>> cancelar(
            @PathVariable Long id) {

        Sesion actualizada = sesionService.cancelar(id);

        ApiResponseDTO<SesionResponse> response = new ApiResponseDTO<>();
        response.setSuccess(true);
        response.setMessage("Sesión cancelada correctamente.");
        response.setData(SesionMapper.toResponse(actualizada));

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/no-asistio")
    public ResponseEntity<ApiResponseDTO<SesionResponse>> noAsistio(
            @PathVariable Long id) {

        Sesion actualizada = sesionService.noAsistio(id);

        ApiResponseDTO<SesionResponse> response = new ApiResponseDTO<>();
        response.setSuccess(true);
        response.setMessage("Sesión marcada como no asistida correctamente.");
        response.setData(SesionMapper.toResponse(actualizada));

        return ResponseEntity.ok(response);
    }
}
