package com.kinesiovitality.servicio.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kinesiovitality.common.response.ApiResponse;
import com.kinesiovitality.servicio.dto.ServicioRequest;
import com.kinesiovitality.servicio.dto.ServicioResponse;
import com.kinesiovitality.servicio.mapper.ServicioMapper;
import com.kinesiovitality.servicio.model.Servicio;
import com.kinesiovitality.servicio.service.ServicioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/servicios")
@Validated
public class ServicioController {

    private final ServicioService servicioService;

    public ServicioController(ServicioService servicioService) {
        this.servicioService = servicioService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ServicioResponse>>> listar() {

        List<ServicioResponse> response = servicioService.listar()
                .stream()
                .map(ServicioMapper::toResponse)
                .toList();

        ApiResponse<List<ServicioResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setSuccess(true);
        apiResponse.setMessage("Servicios obtenidos correctamente.");
        apiResponse.setData(response);

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/activos")
    public ResponseEntity<ApiResponse<List<ServicioResponse>>> listarActivos() {

        List<ServicioResponse> response = servicioService.listarActivos()
                .stream()
                .map(ServicioMapper::toResponse)
                .toList();

        ApiResponse<List<ServicioResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setSuccess(true);
        apiResponse.setMessage("Servicios activos obtenidos correctamente.");
        apiResponse.setData(response);

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ServicioResponse>> buscarPorId(
            @PathVariable Long id) {

        Servicio servicio = servicioService.buscarPorId(id);

        ApiResponse<ServicioResponse> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setMessage("Servicio encontrado.");
        response.setData(ServicioMapper.toResponse(servicio));

        return ResponseEntity.ok(response);
    }

    @GetMapping("/buscar")
    public ResponseEntity<ApiResponse<List<ServicioResponse>>> buscarPorNombre(
            @RequestParam String nombre) {

        List<ServicioResponse> response = servicioService
                .buscarPorNombre(nombre)
                .stream()
                .map(ServicioMapper::toResponse)
                .toList();

        ApiResponse<List<ServicioResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setSuccess(true);
        apiResponse.setMessage("Búsqueda realizada correctamente.");
        apiResponse.setData(response);

        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ServicioResponse>> guardar(
            @Valid @RequestBody ServicioRequest request) {

        Servicio servicio = ServicioMapper.toEntity(request);

        Servicio guardado = servicioService.guardar(servicio);

        ApiResponse<ServicioResponse> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setMessage("Servicio registrado correctamente.");
        response.setData(ServicioMapper.toResponse(guardado));

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ServicioResponse>> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody ServicioRequest request) {

        Servicio servicio = ServicioMapper.toEntity(request);

        Servicio actualizado = servicioService.actualizar(id, servicio);

        ApiResponse<ServicioResponse> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setMessage("Servicio actualizado correctamente.");
        response.setData(ServicioMapper.toResponse(actualizado));

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(
            @PathVariable Long id) {

        servicioService.eliminar(id);

        ApiResponse<Void> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setMessage("Servicio desactivado correctamente.");
        response.setData(null);

        return ResponseEntity.ok(response);
    }

}
