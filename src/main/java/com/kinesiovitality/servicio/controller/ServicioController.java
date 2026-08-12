package com.kinesiovitality.servicio.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kinesiovitality.common.response.ApiResponseDTO;
import com.kinesiovitality.servicio.dto.ServicioRequest;
import com.kinesiovitality.servicio.dto.ServicioResponse;
import com.kinesiovitality.servicio.mapper.ServicioMapper;
import com.kinesiovitality.servicio.model.Servicio;
import com.kinesiovitality.servicio.service.ServicioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/servicios")
@Validated
@Tag(
	    name = "Servicios",
	    description = "Gestión de servicios de fisioterapia disponibles en el sistema."
	)
public class ServicioController {

    private final ServicioService servicioService;

    public ServicioController(ServicioService servicioService) {
        this.servicioService = servicioService;
    }

    @Operation(
    	    summary = "Listar servicios",
    	    description = "Obtiene el listado de todos los servicios registrados."
    	)
    	@ApiResponses({
    	    @ApiResponse(responseCode = "200", description = "Listado obtenido correctamente"),
    	    @ApiResponse(responseCode = "401", description = "No autenticado"),
    	    @ApiResponse(responseCode = "403", description = "No autorizado")
    	})
    	@SecurityRequirement(name = "Bearer Authentication")
    @GetMapping
    public ResponseEntity<ApiResponseDTO<List<ServicioResponse>>> listar() {

        List<ServicioResponse> response = servicioService.listar()
                .stream()
                .map(ServicioMapper::toResponse)
                .toList();

        ApiResponseDTO<List<ServicioResponse>> apiResponse = new ApiResponseDTO<>();
        apiResponse.setSuccess(true);
        apiResponse.setMessage("Servicios obtenidos correctamente.");
        apiResponse.setData(response);

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/activos")
    public ResponseEntity<ApiResponseDTO<List<ServicioResponse>>> listarActivos() {

        List<ServicioResponse> response = servicioService.listarActivos()
                .stream()
                .map(ServicioMapper::toResponse)
                .toList();

        ApiResponseDTO<List<ServicioResponse>> apiResponse = new ApiResponseDTO<>();
        apiResponse.setSuccess(true);
        apiResponse.setMessage("Servicios activos obtenidos correctamente.");
        apiResponse.setData(response);

        return ResponseEntity.ok(apiResponse);
    }

    @Operation(
    	    summary = "Buscar servicio",
    	    description = "Obtiene un servicio mediante su identificador."
    	)
    	@ApiResponses({
    	    @ApiResponse(responseCode = "200", description = "Servicio encontrado"),
    	    @ApiResponse(responseCode = "404", description = "Servicio no encontrado")
    	})
    	@SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<ServicioResponse>> buscarPorId(
            @PathVariable Long id) {

        Servicio servicio = servicioService.buscarPorId(id);

        ApiResponseDTO<ServicioResponse> response = new ApiResponseDTO<>();
        response.setSuccess(true);
        response.setMessage("Servicio encontrado.");
        response.setData(ServicioMapper.toResponse(servicio));

        return ResponseEntity.ok(response);
    }

    @GetMapping("/buscar")
    public ResponseEntity<ApiResponseDTO<List<ServicioResponse>>> buscarPorNombre(
            @RequestParam String nombre) {

        List<ServicioResponse> response = servicioService
                .buscarPorNombre(nombre)
                .stream()
                .map(ServicioMapper::toResponse)
                .toList();

        ApiResponseDTO<List<ServicioResponse>> apiResponse = new ApiResponseDTO<>();
        apiResponse.setSuccess(true);
        apiResponse.setMessage("Búsqueda realizada correctamente.");
        apiResponse.setData(response);

        return ResponseEntity.ok(apiResponse);
    }

    @Operation(
    	    summary = "Registrar servicio",
    	    description = "Permite registrar un nuevo servicio."
    	)
    	@ApiResponses({
    	    @ApiResponse(responseCode = "201", description = "Servicio registrado correctamente"),
    	    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    	})
    	@SecurityRequirement(name = "Bearer Authentication")
    @PostMapping
    public ResponseEntity<ApiResponseDTO<ServicioResponse>> guardar(
            @Valid @RequestBody ServicioRequest request) {

        Servicio servicio = ServicioMapper.toEntity(request);

        Servicio guardado = servicioService.guardar(servicio);

        ApiResponseDTO<ServicioResponse> response = new ApiResponseDTO<>();
        response.setSuccess(true);
        response.setMessage("Servicio registrado correctamente.");
        response.setData(ServicioMapper.toResponse(guardado));

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(
    	    summary = "Actualizar servicio",
    	    description = "Actualiza la información de un servicio."
    	)
    	@ApiResponses({
    	    @ApiResponse(responseCode = "200", description = "Servicio actualizado correctamente"),
    	    @ApiResponse(responseCode = "404", description = "Servicio no encontrado")
    	})
    	@SecurityRequirement(name = "Bearer Authentication")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<ServicioResponse>> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody ServicioRequest request) {

        Servicio servicio = ServicioMapper.toEntity(request);

        Servicio actualizado = servicioService.actualizar(id, servicio);

        ApiResponseDTO<ServicioResponse> response = new ApiResponseDTO<>();
        response.setSuccess(true);
        response.setMessage("Servicio actualizado correctamente.");
        response.setData(ServicioMapper.toResponse(actualizado));

        return ResponseEntity.ok(response);
    }

    @Operation(
    	    summary = "Inactivar servicio",
    	    description = "Realiza una eliminación lógica del servicio."
    	)
    	@ApiResponses({
    	    @ApiResponse(responseCode = "200", description = "Servicio inactivado correctamente"),
    	    @ApiResponse(responseCode = "404", description = "Servicio no encontrado")
    	})
    	@SecurityRequirement(name = "Bearer Authentication")

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<Void>> eliminar(
            @PathVariable Long id) {

        servicioService.eliminar(id);

        ApiResponseDTO<Void> response = new ApiResponseDTO<>();
        response.setSuccess(true);
        response.setMessage("Servicio desactivado correctamente.");
        response.setData(null);

        return ResponseEntity.ok(response);
    }
    
    @PatchMapping("/{id}/reactivar")
    public ResponseEntity<ApiResponseDTO<Void>> reactivar(
            @PathVariable Long id) {

        servicioService.reactivar(id);

        ApiResponseDTO<Void> response = new ApiResponseDTO<>();

        response.setSuccess(true);
        response.setMessage("Servicio reactivado correctamente.");
        response.setData(null);

        return ResponseEntity.ok(response);
    }

}
