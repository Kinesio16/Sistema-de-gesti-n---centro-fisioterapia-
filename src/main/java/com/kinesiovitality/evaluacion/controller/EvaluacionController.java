package com.kinesiovitality.evaluacion.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kinesiovitality.common.response.ApiResponse;
import com.kinesiovitality.evaluacion.dto.EvaluacionRequest;
import com.kinesiovitality.evaluacion.dto.EvaluacionResponse;
import com.kinesiovitality.evaluacion.mapper.EvaluacionMapper;
import com.kinesiovitality.evaluacion.model.Evaluacion;
import com.kinesiovitality.evaluacion.service.EvaluacionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/evaluaciones")
@Validated
public class EvaluacionController {

    private final EvaluacionService evaluacionService;

    public EvaluacionController(EvaluacionService evaluacionService) {
        this.evaluacionService = evaluacionService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<EvaluacionResponse>>> listar() {

        List<Evaluacion> evaluaciones = evaluacionService.listar();

        List<EvaluacionResponse> response = evaluaciones.stream()
                .map(EvaluacionMapper::toResponse)
                .toList();

        ApiResponse<List<EvaluacionResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setSuccess(true);
        apiResponse.setMessage("Evaluaciones obtenidas correctamente.");
        apiResponse.setData(response);

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<EvaluacionResponse>> buscarPorId(
            @PathVariable Long id) {

        Evaluacion evaluacion = evaluacionService.buscarPorId(id);

        ApiResponse<EvaluacionResponse> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setMessage("Evaluación encontrada.");
        response.setData(EvaluacionMapper.toResponse(evaluacion));

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<EvaluacionResponse>> guardar(
            @Valid @RequestBody EvaluacionRequest request) {

        Evaluacion guardada = evaluacionService.guardar(request);

        ApiResponse<EvaluacionResponse> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setMessage("Evaluación registrada correctamente.");
        response.setData(EvaluacionMapper.toResponse(guardada));

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<EvaluacionResponse>> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody EvaluacionRequest request) {

        Evaluacion actualizada = evaluacionService.actualizar(id, request);

        ApiResponse<EvaluacionResponse> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setMessage("Evaluación actualizada correctamente.");
        response.setData(EvaluacionMapper.toResponse(actualizada));

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/inactivar")
    public ResponseEntity<ApiResponse<Void>> inactivar(
            @PathVariable Long id) {

        evaluacionService.eliminar(id);

        ApiResponse<Void> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setMessage("Evaluación inactivada correctamente.");
        response.setData(null);

        return ResponseEntity.ok(response);
    }

}
