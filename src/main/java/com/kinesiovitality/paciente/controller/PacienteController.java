package com.kinesiovitality.paciente.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kinesiovitality.common.response.ApiResponse;
import com.kinesiovitality.paciente.dto.PacienteRequest;
import com.kinesiovitality.paciente.dto.PacienteResponse;
import com.kinesiovitality.paciente.mapper.PacienteMapper;
import com.kinesiovitality.paciente.model.Paciente;
import com.kinesiovitality.paciente.service.PacienteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/pacientes")
@Validated
public class PacienteController {

    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<PacienteResponse>>> listar() {

        List<Paciente> pacientes = pacienteService.listar();

        List<PacienteResponse> response = pacientes.stream()
                .map(PacienteMapper::toResponse)
                .toList();

        ApiResponse<List<PacienteResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setSuccess(true);
        apiResponse.setMessage("Pacientes obtenidos correctamente.");
        apiResponse.setData(response);

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PacienteResponse>> buscarPorId(@PathVariable Long id) {

        Paciente paciente = pacienteService.buscarPorId(id);

        ApiResponse<PacienteResponse> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setMessage("Paciente encontrado.");
        response.setData(PacienteMapper.toResponse(paciente));

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<PacienteResponse>> guardar(
            @Valid @RequestBody PacienteRequest request) {

        Paciente paciente = PacienteMapper.toEntity(request);

        Paciente guardado = pacienteService.guardar(paciente);

        ApiResponse<PacienteResponse> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setMessage("Paciente registrado correctamente.");
        response.setData(PacienteMapper.toResponse(guardado));

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PacienteResponse>> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody PacienteRequest request) {

        Paciente paciente = PacienteMapper.toEntity(request);

        Paciente actualizado = pacienteService.actualizar(id, paciente);

        ApiResponse<PacienteResponse> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setMessage("Paciente actualizado correctamente.");
        response.setData(PacienteMapper.toResponse(actualizado));

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/inactivar")
    public ResponseEntity<ApiResponse<Void>> inactivar(@PathVariable Long id) {

        pacienteService.eliminar(id);

        ApiResponse<Void> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setMessage("Paciente inactivado correctamente.");
        response.setData(null);

        return ResponseEntity.ok(response);
    }

}