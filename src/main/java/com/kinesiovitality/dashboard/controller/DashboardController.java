package com.kinesiovitality.dashboard.controller;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kinesiovitality.common.response.ApiResponseDTO;
import com.kinesiovitality.dashboard.dto.DashboardResponse;
import com.kinesiovitality.dashboard.service.DashboardService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@RestController
@RequestMapping("/api/dashboard")
@Tag(
	    name = "Dashboard",
	    description = "Indicadores y estadísticas generales del sistema."
	)
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @Operation(
    	    summary = "Obtener resumen del dashboard",
    	    description = "Devuelve las estadísticas generales del sistema como pacientes, fisioterapeutas, citas, sesiones, tratamientos y ventas."
    	)
    @ApiResponses({
    	    @ApiResponse(responseCode = "200", description = "Resumen obtenido correctamente"),
    	    @ApiResponse(responseCode = "401", description = "No autorizado"),
    	    @ApiResponse(responseCode = "403", description = "Acceso denegado")
    	})
    	@SecurityRequirement(name = "Bearer Authentication")
    @GetMapping
    public ResponseEntity<ApiResponseDTO<DashboardResponse>> obtenerResumen() {

        DashboardResponse dashboard = dashboardService.obtenerResumen();

        ApiResponseDTO<DashboardResponse> response = new ApiResponseDTO<>();
        response.setSuccess(true);
        response.setMessage("Dashboard obtenido correctamente.");
        response.setData(dashboard);

        return ResponseEntity.ok(response);
    }

}
