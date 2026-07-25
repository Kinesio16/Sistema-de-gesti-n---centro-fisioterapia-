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
import com.kinesiovitality.common.response.ApiResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/citas")
@Validated
public class CitaController {

    private final CitaService citaService;

    public CitaController(CitaService citaService) {
        this.citaService = citaService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CitaResponse>>> listar() {

        List<Cita> citas = citaService.listar();

        List<CitaResponse> response = citas.stream()
                .map(CitaMapper::toResponse)
                .toList();

        ApiResponse<List<CitaResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setSuccess(true);
        apiResponse.setMessage("Citas obtenidas correctamente.");
        apiResponse.setData(response);

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CitaResponse>> buscarPorId(
            @PathVariable Long id) {

        Cita cita = citaService.buscarPorId(id);

        ApiResponse<CitaResponse> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setMessage("Cita encontrada.");
        response.setData(CitaMapper.toResponse(cita));

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CitaResponse>> guardar(
            @Valid @RequestBody CitaRequest request) {

        Cita guardada = citaService.guardar(request);

        ApiResponse<CitaResponse> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setMessage("Cita registrada correctamente.");
        response.setData(CitaMapper.toResponse(guardada));

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CitaResponse>> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody CitaRequest request) {

        Cita actualizada = citaService.actualizar(id, request);

        ApiResponse<CitaResponse> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setMessage("Cita actualizada correctamente.");
        response.setData(CitaMapper.toResponse(actualizada));

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<ApiResponse<Void>> cancelar(
            @PathVariable Long id) {

        citaService.cancelar(id);

        ApiResponse<Void> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setMessage("Cita cancelada correctamente.");
        response.setData(null);

        return ResponseEntity.ok(response);
    }

}
