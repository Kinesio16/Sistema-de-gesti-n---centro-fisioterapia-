package com.kinesiovitality.tratamiento.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kinesiovitality.common.enums.EstadoTratamiento;
import com.kinesiovitality.common.response.ApiResponse;
import com.kinesiovitality.tratamiento.dto.TratamientoRequest;
import com.kinesiovitality.tratamiento.dto.TratamientoResponse;
import com.kinesiovitality.tratamiento.mapper.TratamientoMapper;
import com.kinesiovitality.tratamiento.model.Tratamiento;
import com.kinesiovitality.tratamiento.service.TratamientoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tratamientos")
@Validated
public class TratamientoController {

    private final TratamientoService tratamientoService;

    public TratamientoController(TratamientoService tratamientoService) {
        this.tratamientoService = tratamientoService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<TratamientoResponse>>> listar() {

        List<TratamientoResponse> response = tratamientoService.listar()
                .stream()
                .map(TratamientoMapper::toResponse)
                .toList();

        ApiResponse<List<TratamientoResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setSuccess(true);
        apiResponse.setMessage("Tratamientos obtenidos correctamente.");
        apiResponse.setData(response);

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TratamientoResponse>> buscarPorId(
            @PathVariable Long id) {

        Tratamiento tratamiento = tratamientoService.buscarPorId(id);

        ApiResponse<TratamientoResponse> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setMessage("Tratamiento encontrado.");
        response.setData(TratamientoMapper.toResponse(tratamiento));

        return ResponseEntity.ok(response);
    }

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<ApiResponse<List<TratamientoResponse>>> listarPorPaciente(
            @PathVariable Long pacienteId) {

        List<TratamientoResponse> response = tratamientoService
                .listarPorPaciente(pacienteId)
                .stream()
                .map(TratamientoMapper::toResponse)
                .toList();

        ApiResponse<List<TratamientoResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setSuccess(true);
        apiResponse.setMessage("Tratamientos del paciente obtenidos correctamente.");
        apiResponse.setData(response);

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/fisioterapeuta/{fisioterapeutaId}")
    public ResponseEntity<ApiResponse<List<TratamientoResponse>>> listarPorFisioterapeuta(
            @PathVariable Long fisioterapeutaId) {

        List<TratamientoResponse> response = tratamientoService
                .listarPorFisioterapeuta(fisioterapeutaId)
                .stream()
                .map(TratamientoMapper::toResponse)
                .toList();

        ApiResponse<List<TratamientoResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setSuccess(true);
        apiResponse.setMessage("Tratamientos del fisioterapeuta obtenidos correctamente.");
        apiResponse.setData(response);

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<ApiResponse<List<TratamientoResponse>>> listarPorEstado(
            @PathVariable EstadoTratamiento estado) {

        List<TratamientoResponse> response = tratamientoService
                .listarPorEstado(estado)
                .stream()
                .map(TratamientoMapper::toResponse)
                .toList();

        ApiResponse<List<TratamientoResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setSuccess(true);
        apiResponse.setMessage("Tratamientos obtenidos correctamente.");
        apiResponse.setData(response);

        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<TratamientoResponse>> guardar(
            @Valid @RequestBody TratamientoRequest request) {

        Tratamiento tratamiento = TratamientoMapper.toEntity(request);

        Tratamiento guardado = tratamientoService.guardar(
                tratamiento,
                request.getPacienteId(),
                request.getFisioterapeutaId(),
                request.getEvaluacionId()
        );

        ApiResponse<TratamientoResponse> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setMessage("Tratamiento registrado correctamente.");
        response.setData(TratamientoMapper.toResponse(guardado));

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<TratamientoResponse>> actualizar(
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

        ApiResponse<TratamientoResponse> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setMessage("Tratamiento actualizado correctamente.");
        response.setData(TratamientoMapper.toResponse(actualizado));

        return ResponseEntity.ok(response);
    }


    @PatchMapping("/{id}/suspender")
    public ResponseEntity<ApiResponse<TratamientoResponse>> suspender(
            @PathVariable Long id) {

        Tratamiento actualizado = tratamientoService.suspender(id);

        ApiResponse<TratamientoResponse> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setMessage("Tratamiento suspendido correctamente.");
        response.setData(TratamientoMapper.toResponse(actualizado));

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/reanudar")
    public ResponseEntity<ApiResponse<TratamientoResponse>> reanudar(
            @PathVariable Long id) {

        Tratamiento actualizado = tratamientoService.reanudar(id);

        ApiResponse<TratamientoResponse> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setMessage("Tratamiento reanudado correctamente.");
        response.setData(TratamientoMapper.toResponse(actualizado));

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/finalizar")
    public ResponseEntity<ApiResponse<TratamientoResponse>> finalizar(
            @PathVariable Long id) {

        Tratamiento actualizado = tratamientoService.finalizar(id);

        ApiResponse<TratamientoResponse> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setMessage("Tratamiento finalizado correctamente.");
        response.setData(TratamientoMapper.toResponse(actualizado));

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<ApiResponse<TratamientoResponse>> cancelar(
            @PathVariable Long id) {

        Tratamiento actualizado = tratamientoService.cancelar(id);

        ApiResponse<TratamientoResponse> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setMessage("Tratamiento cancelado correctamente.");
        response.setData(TratamientoMapper.toResponse(actualizado));

        return ResponseEntity.ok(response);
    }
}
