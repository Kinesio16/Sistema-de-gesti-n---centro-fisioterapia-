package com.kinesiovitality.tratamiento.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kinesiovitality.common.enums.EstadoTratamiento;
import com.kinesiovitality.common.response.ApiResponseDTO;
import com.kinesiovitality.tratamiento.dto.TratamientoRequest;
import com.kinesiovitality.tratamiento.dto.TratamientoResponse;
import com.kinesiovitality.tratamiento.mapper.TratamientoMapper;
import com.kinesiovitality.tratamiento.model.Tratamiento;
import com.kinesiovitality.tratamiento.service.TratamientoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tratamientos")
@Validated
@Tag(
	    name = "Tratamientos",
	    description = "Gestión de los tratamientos fisioterapéuticos asignados a los pacientes."
	)
	@SecurityRequirement(name = "Bearer Authentication")
public class TratamientoController {

    private final TratamientoService tratamientoService;

    public TratamientoController(TratamientoService tratamientoService) {
        this.tratamientoService = tratamientoService;
    }

    @Operation(
    	    summary = "Listar tratamientos",
    	    description = "Obtiene el listado completo de tratamientos registrados."
    	)
    	@ApiResponses({
    	    @ApiResponse(responseCode = "200", description = "Listado obtenido correctamente"),
    	    @ApiResponse(responseCode = "401", description = "No autorizado")
    	})
    @GetMapping
    public ResponseEntity<ApiResponseDTO<List<TratamientoResponse>>> listar() {

        List<TratamientoResponse> response = tratamientoService.listar()
                .stream()
                .map(TratamientoMapper::toResponse)
                .toList();

        ApiResponseDTO<List<TratamientoResponse>> apiResponse = new ApiResponseDTO<>();
        apiResponse.setSuccess(true);
        apiResponse.setMessage("Tratamientos obtenidos correctamente.");
        apiResponse.setData(response);

        return ResponseEntity.ok(apiResponse);
    }

    @Operation(
    	    summary = "Buscar tratamiento",
    	    description = "Obtiene un tratamiento mediante su identificador."
    	)
    	@ApiResponses({
    	    @ApiResponse(responseCode = "200", description = "Tratamiento encontrado"),
    	    @ApiResponse(responseCode = "404", description = "Tratamiento no encontrado")
    	})
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<TratamientoResponse>> buscarPorId(
            @PathVariable Long id) {

        Tratamiento tratamiento = tratamientoService.buscarPorId(id);

        ApiResponseDTO<TratamientoResponse> response = new ApiResponseDTO<>();
        response.setSuccess(true);
        response.setMessage("Tratamiento encontrado.");
        response.setData(TratamientoMapper.toResponse(tratamiento));

        return ResponseEntity.ok(response);
    }

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<ApiResponseDTO<List<TratamientoResponse>>> listarPorPaciente(
            @PathVariable Long pacienteId) {

        List<TratamientoResponse> response = tratamientoService
                .listarPorPaciente(pacienteId)
                .stream()
                .map(TratamientoMapper::toResponse)
                .toList();

        ApiResponseDTO<List<TratamientoResponse>> apiResponse = new ApiResponseDTO<>();
        apiResponse.setSuccess(true);
        apiResponse.setMessage("Tratamientos del paciente obtenidos correctamente.");
        apiResponse.setData(response);

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/fisioterapeuta/{fisioterapeutaId}")
    public ResponseEntity<ApiResponseDTO<List<TratamientoResponse>>> listarPorFisioterapeuta(
            @PathVariable Long fisioterapeutaId) {

        List<TratamientoResponse> response = tratamientoService
                .listarPorFisioterapeuta(fisioterapeutaId)
                .stream()
                .map(TratamientoMapper::toResponse)
                .toList();

        ApiResponseDTO<List<TratamientoResponse>> apiResponse = new ApiResponseDTO<>();
        apiResponse.setSuccess(true);
        apiResponse.setMessage("Tratamientos del fisioterapeuta obtenidos correctamente.");
        apiResponse.setData(response);

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<ApiResponseDTO<List<TratamientoResponse>>> listarPorEstado(
            @PathVariable EstadoTratamiento estado) {

        List<TratamientoResponse> response = tratamientoService
                .listarPorEstado(estado)
                .stream()
                .map(TratamientoMapper::toResponse)
                .toList();

        ApiResponseDTO<List<TratamientoResponse>> apiResponse = new ApiResponseDTO<>();
        apiResponse.setSuccess(true);
        apiResponse.setMessage("Tratamientos obtenidos correctamente.");
        apiResponse.setData(response);

        return ResponseEntity.ok(apiResponse);
    }

    @Operation(
    	    summary = "Registrar tratamiento",
    	    description = "Registra un nuevo tratamiento fisioterapéutico."
    	)
    	@ApiResponses({
    	    @ApiResponse(responseCode = "201", description = "Tratamiento registrado correctamente"),
    	    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    	})
    @PostMapping
    public ResponseEntity<ApiResponseDTO<TratamientoResponse>> guardar(
            @Valid @RequestBody TratamientoRequest request) {

        Tratamiento tratamiento = TratamientoMapper.toEntity(request);

        Tratamiento guardado = tratamientoService.guardar(
                tratamiento,
                request.getPacienteId(),
                request.getFisioterapeutaId(),
                request.getEvaluacionId()
        );

        ApiResponseDTO<TratamientoResponse> response = new ApiResponseDTO<>();
        response.setSuccess(true);
        response.setMessage("Tratamiento registrado correctamente.");
        response.setData(TratamientoMapper.toResponse(guardado));

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(
    	    summary = "Actualizar tratamiento",
    	    description = "Actualiza la información de un tratamiento."
    	)
    	@ApiResponses({
    	    @ApiResponse(responseCode = "200", description = "Tratamiento actualizado"),
    	    @ApiResponse(responseCode = "404", description = "Tratamiento no encontrado")
    	})
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<TratamientoResponse>> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody TratamientoRequest request) {

        Tratamiento tratamiento = TratamientoMapper.toEntity(request);

        Tratamiento actualizado = tratamientoService.actualizar(
                id,
                tratamiento,
                request.getPacienteId(),
                request.getFisioterapeutaId(),
                request.getEvaluacionId()
        );

        ApiResponseDTO<TratamientoResponse> response = new ApiResponseDTO<>();
        response.setSuccess(true);
        response.setMessage("Tratamiento actualizado correctamente.");
        response.setData(TratamientoMapper.toResponse(actualizado));

        return ResponseEntity.ok(response);
    }


    @Operation(
    	    summary = "Inactivar tratamiento",
    	    description = "Realiza la eliminación lógica de un tratamiento."
    	)
    	@ApiResponses({
    	    @ApiResponse(responseCode = "200", description = "Tratamiento inactivado"),
    	    @ApiResponse(responseCode = "404", description = "Tratamiento no encontrado")
    	})
    @PatchMapping("/{id}/suspender")
    public ResponseEntity<ApiResponseDTO<TratamientoResponse>> suspender(
            @PathVariable Long id) {

        Tratamiento actualizado = tratamientoService.suspender(id);

        ApiResponseDTO<TratamientoResponse> response = new ApiResponseDTO<>();
        response.setSuccess(true);
        response.setMessage("Tratamiento suspendido correctamente.");
        response.setData(TratamientoMapper.toResponse(actualizado));

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/reanudar")
    public ResponseEntity<ApiResponseDTO<TratamientoResponse>> reanudar(
            @PathVariable Long id) {

        Tratamiento actualizado = tratamientoService.reanudar(id);

        ApiResponseDTO<TratamientoResponse> response = new ApiResponseDTO<>();
        response.setSuccess(true);
        response.setMessage("Tratamiento reanudado correctamente.");
        response.setData(TratamientoMapper.toResponse(actualizado));

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/finalizar")
    public ResponseEntity<ApiResponseDTO<TratamientoResponse>> finalizar(
            @PathVariable Long id) {

        Tratamiento actualizado = tratamientoService.finalizar(id);

        ApiResponseDTO<TratamientoResponse> response = new ApiResponseDTO<>();
        response.setSuccess(true);
        response.setMessage("Tratamiento finalizado correctamente.");
        response.setData(TratamientoMapper.toResponse(actualizado));

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<ApiResponseDTO<TratamientoResponse>> cancelar(
            @PathVariable Long id) {

        Tratamiento actualizado = tratamientoService.cancelar(id);

        ApiResponseDTO<TratamientoResponse> response = new ApiResponseDTO<>();
        response.setSuccess(true);
        response.setMessage("Tratamiento cancelado correctamente.");
        response.setData(TratamientoMapper.toResponse(actualizado));

        return ResponseEntity.ok(response);
    }
}
