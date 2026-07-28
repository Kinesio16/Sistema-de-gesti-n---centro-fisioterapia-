package com.kinesiovitality.dashboard.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kinesiovitality.common.response.ApiResponse;
import com.kinesiovitality.dashboard.dto.DashboardResponse;
import com.kinesiovitality.dashboard.service.DashboardService;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<DashboardResponse>> obtenerResumen() {

        DashboardResponse dashboard = dashboardService.obtenerResumen();

        ApiResponse<DashboardResponse> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setMessage("Dashboard obtenido correctamente.");
        response.setData(dashboard);

        return ResponseEntity.ok(response);
    }

}
