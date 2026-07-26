package com.kinesiovitality.sesion.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kinesiovitality.common.response.ApiResponse;
import com.kinesiovitality.sesion.dto.SesionRequest;
import com.kinesiovitality.sesion.dto.SesionResponse;
import com.kinesiovitality.sesion.mapper.SesionMapper;
import com.kinesiovitality.sesion.model.Sesion;
import com.kinesiovitality.sesion.service.SesionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/sesiones")
@Validated
public class SesionController {

    private final SesionService sesionService;

    public SesionController(SesionService sesionService) {
        this.sesionService = sesionService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<SesionResponse>>> listar() {

        List<SesionResponse> response = sesionService.listar()
                .stream()
                .map(SesionMapper::toResponse)
                .toList();

        ApiResponse<List<SesionResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setSuccess(true);
        apiResponse.setMessage("Sesiones obtenidas correctamente.");
        apiResponse.setData(response);

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<SesionResponse>> buscarPorId(
            @PathVariable Long id) {

        Sesion sesion = sesionService.buscarPorId(id);

        ApiResponse<SesionResponse> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setMessage("Sesión encontrada.");
        response.setData(SesionMapper.toResponse(sesion));

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<SesionResponse>> guardar(
            @Valid @RequestBody SesionRequest request) {

        Sesion sesion = SesionMapper.toEntity(request);

        Sesion guardada = sesionService.guardar(
                sesion,
                request.getTratamientoId());

        ApiResponse<SesionResponse> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setMessage("Sesión registrada correctamente.");
        response.setData(SesionMapper.toResponse(guardada));

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<SesionResponse>> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody SesionRequest request) {

        Sesion sesion = SesionMapper.toEntity(request);

        Sesion actualizada = sesionService.actualizar(
                id,
                sesion,
                request.getTratamientoId());

        ApiResponse<SesionResponse> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setMessage("Sesión actualizada correctamente.");
        response.setData(SesionMapper.toResponse(actualizada));

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/realizada")
    public ResponseEntity<ApiResponse<SesionResponse>> registrarRealizada(
            @PathVariable Long id) {

        Sesion actualizada = sesionService.registrarRealizada(id);

        ApiResponse<SesionResponse> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setMessage("Sesión marcada como realizada correctamente.");
        response.setData(SesionMapper.toResponse(actualizada));

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/reprogramar")
    public ResponseEntity<ApiResponse<SesionResponse>> reprogramar(
            @PathVariable Long id,
            @Valid @RequestBody SesionRequest request) {

        Sesion sesion = SesionMapper.toEntity(request);

        Sesion actualizada = sesionService.reprogramar(id, sesion);

        ApiResponse<SesionResponse> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setMessage("Sesión reprogramada correctamente.");
        response.setData(SesionMapper.toResponse(actualizada));

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<ApiResponse<SesionResponse>> cancelar(
            @PathVariable Long id) {

        Sesion actualizada = sesionService.cancelar(id);

        ApiResponse<SesionResponse> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setMessage("Sesión cancelada correctamente.");
        response.setData(SesionMapper.toResponse(actualizada));

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/no-asistio")
    public ResponseEntity<ApiResponse<SesionResponse>> noAsistio(
            @PathVariable Long id) {

        Sesion actualizada = sesionService.noAsistio(id);

        ApiResponse<SesionResponse> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setMessage("Sesión marcada como no asistida correctamente.");
        response.setData(SesionMapper.toResponse(actualizada));

        return ResponseEntity.ok(response);
    }
}
