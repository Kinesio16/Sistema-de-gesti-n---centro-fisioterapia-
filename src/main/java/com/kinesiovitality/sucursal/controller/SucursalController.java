package com.kinesiovitality.sucursal.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kinesiovitality.common.response.ApiResponseDTO;
import com.kinesiovitality.sucursal.dto.SucursalRequest;
import com.kinesiovitality.sucursal.dto.SucursalResponse;
import com.kinesiovitality.sucursal.mapper.SucursalMapper;
import com.kinesiovitality.sucursal.model.Sucursal;
import com.kinesiovitality.sucursal.service.SucursalService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/sucursales")
@Validated
@Tag(
        name = "Sucursales",
        description = "Gestión de sucursales de Kinesio Vitality."
)
public class SucursalController {

    private final SucursalService sucursalService;

    public SucursalController(SucursalService sucursalService) {
        this.sucursalService = sucursalService;
    }

    @Operation(
            summary = "Listar sucursales",
            description = "Obtiene todas las sucursales registradas."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Listado obtenido correctamente"),
            @ApiResponse(responseCode = "401", description = "No autenticado"),
            @ApiResponse(responseCode = "403", description = "No autorizado")
    })
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping
    public ResponseEntity<ApiResponseDTO<List<SucursalResponse>>> listar() {

        List<SucursalResponse> response = sucursalService.listar()
                .stream()
                .map(SucursalMapper::toResponse)
                .toList();

        ApiResponseDTO<List<SucursalResponse>> apiResponse = new ApiResponseDTO<>();
        apiResponse.setSuccess(true);
        apiResponse.setMessage("Sucursales obtenidas correctamente.");
        apiResponse.setData(response);

        return ResponseEntity.ok(apiResponse);

    }

    @Operation(
            summary = "Buscar sucursal",
            description = "Obtiene una sucursal mediante su identificador."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sucursal encontrada"),
            @ApiResponse(responseCode = "404", description = "Sucursal no encontrada")
    })
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<SucursalResponse>> buscarPorId(
            @PathVariable Long id) {

        Sucursal sucursal = sucursalService.buscarPorId(id);

        ApiResponseDTO<SucursalResponse> response = new ApiResponseDTO<>();
        response.setSuccess(true);
        response.setMessage("Sucursal encontrada.");
        response.setData(SucursalMapper.toResponse(sucursal));

        return ResponseEntity.ok(response);

    }

    @Operation(
            summary = "Registrar sucursal",
            description = "Permite registrar una nueva sucursal."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Sucursal registrada correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping
    public ResponseEntity<ApiResponseDTO<SucursalResponse>> guardar(
            @Valid @RequestBody SucursalRequest request) {

        Sucursal sucursal = SucursalMapper.toEntity(request);

        Sucursal guardada = sucursalService.guardar(sucursal);

        ApiResponseDTO<SucursalResponse> response = new ApiResponseDTO<>();
        response.setSuccess(true);
        response.setMessage("Sucursal registrada correctamente.");
        response.setData(SucursalMapper.toResponse(guardada));

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @Operation(
            summary = "Actualizar sucursal",
            description = "Actualiza la información de una sucursal."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sucursal actualizada correctamente"),
            @ApiResponse(responseCode = "404", description = "Sucursal no encontrada")
    })
    @SecurityRequirement(name = "Bearer Authentication")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<SucursalResponse>> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody SucursalRequest request) {

        Sucursal sucursal = SucursalMapper.toEntity(request);

        Sucursal actualizada =
                sucursalService.actualizar(id, sucursal);

        ApiResponseDTO<SucursalResponse> response = new ApiResponseDTO<>();
        response.setSuccess(true);
        response.setMessage("Sucursal actualizada correctamente.");
        response.setData(SucursalMapper.toResponse(actualizada));

        return ResponseEntity.ok(response);

    }

    @Operation(
            summary = "Inactivar sucursal",
            description = "Realiza una eliminación lógica de la sucursal."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sucursal inactivada correctamente"),
            @ApiResponse(responseCode = "404", description = "Sucursal no encontrada")
    })
    @SecurityRequirement(name = "Bearer Authentication")
    @PatchMapping("/{id}/inactivar")
    public ResponseEntity<ApiResponseDTO<Void>> eliminar(
            @PathVariable Long id) {

        sucursalService.eliminar(id);

        ApiResponseDTO<Void> response = new ApiResponseDTO<>();
        response.setSuccess(true);
        response.setMessage("Sucursal inactivada correctamente.");
        response.setData(null);

        return ResponseEntity.ok(response);

    }
    
    @Operation(
            summary = "Reactivar sucursal",
            description = "Permite reactivar una sucursal previamente inactivada."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sucursal reactivada correctamente"),
            @ApiResponse(responseCode = "404", description = "Sucursal no encontrada")
    })
    @SecurityRequirement(name = "Bearer Authentication")
    @PatchMapping("/{id}/reactivar")
    public ResponseEntity<ApiResponseDTO<Void>> reactivar(
            @PathVariable Long id) {

        sucursalService.reactivar(id);

        ApiResponseDTO<Void> response = new ApiResponseDTO<>();

        response.setSuccess(true);
        response.setMessage("Sucursal reactivada correctamente.");
        response.setData(null);

        return ResponseEntity.ok(response);

    }
    
    @Operation(
            summary = "Listar sucursales activas",
            description = "Obtiene únicamente las sucursales activas."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Listado obtenido correctamente")
    })
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/activas")
    public ResponseEntity<ApiResponseDTO<List<SucursalResponse>>> listarActivas() {

        List<SucursalResponse> response = sucursalService
                .listarActivas()
                .stream()
                .map(SucursalMapper::toResponse)
                .toList();

        ApiResponseDTO<List<SucursalResponse>> apiResponse = new ApiResponseDTO<>();
        apiResponse.setSuccess(true);
        apiResponse.setMessage("Sucursales activas obtenidas correctamente.");
        apiResponse.setData(response);

        return ResponseEntity.ok(apiResponse);

    }

}