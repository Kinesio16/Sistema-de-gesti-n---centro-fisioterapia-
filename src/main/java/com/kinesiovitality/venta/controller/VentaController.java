package com.kinesiovitality.venta.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kinesiovitality.common.enums.EstadoFactura;
import com.kinesiovitality.common.enums.EstadoPago;
import com.kinesiovitality.common.enums.FormaPago;
import com.kinesiovitality.common.response.ApiResponseDTO;
import com.kinesiovitality.venta.dto.VentaRequest;
import com.kinesiovitality.venta.dto.VentaResponse;
import com.kinesiovitality.venta.mapper.VentaMapper;
import com.kinesiovitality.venta.model.Venta;
import com.kinesiovitality.venta.service.VentaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/ventas")
@Validated
@Tag(
	    name = "Ventas",
	    description = "Gestión de ventas y pagos realizados por los pacientes."
	)
public class VentaController {

    private final VentaService ventaService;

    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    @Operation(
    	    summary = "Listar ventas",
    	    description = "Obtiene el listado completo de ventas registradas."
    	)
    	@ApiResponses({
    	    @ApiResponse(responseCode = "200", description = "Listado obtenido correctamente"),
    	    @ApiResponse(responseCode = "401", description = "No autorizado")
    	})
    	@SecurityRequirement(name = "Bearer Authentication")
    @GetMapping
    public ResponseEntity<ApiResponseDTO<List<VentaResponse>>> listar() {

        List<VentaResponse> response = ventaService.listar()
                .stream()
                .map(VentaMapper::toResponse)
                .toList();

        ApiResponseDTO<List<VentaResponse>> apiResponse = new ApiResponseDTO<>();
        apiResponse.setSuccess(true);
        apiResponse.setMessage("Ventas obtenidas correctamente.");
        apiResponse.setData(response);

        return ResponseEntity.ok(apiResponse);
    }

    @Operation(
    	    summary = "Buscar venta",
    	    description = "Obtiene una venta mediante su identificador."
    	)
    	@ApiResponses({
    	    @ApiResponse(responseCode = "200", description = "Venta encontrada"),
    	    @ApiResponse(responseCode = "404", description = "Venta no encontrada")
    	})
    	@SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<VentaResponse>> buscarPorId(
            @PathVariable Long id) {

        Venta venta = ventaService.buscarPorId(id);

        ApiResponseDTO<VentaResponse> response = new ApiResponseDTO<>();
        response.setSuccess(true);
        response.setMessage("Venta encontrada.");
        response.setData(VentaMapper.toResponse(venta));

        return ResponseEntity.ok(response);
    }

    @Operation(
    	    summary = "Registrar venta",
    	    description = "Registra una nueva venta de servicios."
    	)
    	@ApiResponses({
    	    @ApiResponse(responseCode = "201", description = "Venta registrada correctamente"),
    	    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    	})
    	@SecurityRequirement(name = "Bearer Authentication")
    @PostMapping
    public ResponseEntity<ApiResponseDTO<VentaResponse>> guardar(
            @Valid @RequestBody VentaRequest request) {

        Venta venta = VentaMapper.toEntity(request);

        Venta guardada = ventaService.guardar(
                venta,
                request.getPacienteId(),
                request.getServicioId(),
                request.getFisioterapeutaId(),
                request.getSucursalId());

        ApiResponseDTO<VentaResponse> response = new ApiResponseDTO<>();
        response.setSuccess(true);
        response.setMessage("Venta registrada correctamente.");
        response.setData(VentaMapper.toResponse(guardada));

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(
    	    summary = "Actualizar venta",
    	    description = "Actualiza la información de una venta."
    	)
    	@ApiResponses({
    	    @ApiResponse(responseCode = "200", description = "Venta actualizada correctamente"),
    	    @ApiResponse(responseCode = "404", description = "Venta no encontrada")
    	})
    	@SecurityRequirement(name = "Bearer Authentication")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<VentaResponse>> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody VentaRequest request) {

        Venta venta = VentaMapper.toEntity(request);

        Venta actualizada = ventaService.actualizar(id, venta);

        ApiResponseDTO<VentaResponse> response = new ApiResponseDTO<>();
        response.setSuccess(true);
        response.setMessage("Venta actualizada correctamente.");
        response.setData(VentaMapper.toResponse(actualizada));

        return ResponseEntity.ok(response);
    }

    @Operation(
    	    summary = "Anular venta",
    	    description = "Realiza la anulación lógica de una venta."
    	)
    	@ApiResponses({
    	    @ApiResponse(responseCode = "200", description = "Venta anulada correctamente"),
    	    @ApiResponse(responseCode = "404", description = "Venta no encontrada")
    	})
    	@SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<Void>> eliminar(
            @PathVariable Long id) {

        ventaService.eliminar(id);

        ApiResponseDTO<Void> response = new ApiResponseDTO<>();
        response.setSuccess(true);
        response.setMessage("Venta anulada correctamente.");

        return ResponseEntity.ok(response);
    }

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<ApiResponseDTO<List<VentaResponse>>> listarPorPaciente(
            @PathVariable Long pacienteId) {

        List<VentaResponse> response = ventaService.listarPorPaciente(pacienteId)
                .stream()
                .map(VentaMapper::toResponse)
                .toList();

        ApiResponseDTO<List<VentaResponse>> apiResponse = new ApiResponseDTO<>();
        apiResponse.setSuccess(true);
        apiResponse.setMessage("Ventas obtenidas correctamente.");
        apiResponse.setData(response);

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/servicio/{servicioId}")
    public ResponseEntity<ApiResponseDTO<List<VentaResponse>>> listarPorServicio(
            @PathVariable Long servicioId) {

        List<VentaResponse> response = ventaService.listarPorServicio(servicioId)
                .stream()
                .map(VentaMapper::toResponse)
                .toList();

        ApiResponseDTO<List<VentaResponse>> apiResponse = new ApiResponseDTO<>();
        apiResponse.setSuccess(true);
        apiResponse.setMessage("Ventas obtenidas correctamente.");
        apiResponse.setData(response);

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/forma-pago/{formaPago}")
    public ResponseEntity<ApiResponseDTO<List<VentaResponse>>> listarPorFormaPago(
            @PathVariable FormaPago formaPago) {

        List<VentaResponse> response = ventaService.listarPorFormaPago(formaPago)
                .stream()
                .map(VentaMapper::toResponse)
                .toList();

        ApiResponseDTO<List<VentaResponse>> apiResponse = new ApiResponseDTO<>();
        apiResponse.setSuccess(true);
        apiResponse.setMessage("Ventas obtenidas correctamente.");
        apiResponse.setData(response);

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/estado-pago/{estado}")
    public ResponseEntity<ApiResponseDTO<List<VentaResponse>>> listarPorEstadoPago(
            @PathVariable EstadoPago estado) {

        List<VentaResponse> response = ventaService.listarPorEstadoPago(estado)
                .stream()
                .map(VentaMapper::toResponse)
                .toList();

        ApiResponseDTO<List<VentaResponse>> apiResponse = new ApiResponseDTO<>();
        apiResponse.setSuccess(true);
        apiResponse.setMessage("Ventas obtenidas correctamente.");
        apiResponse.setData(response);

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/estado-factura/{estado}")
    public ResponseEntity<ApiResponseDTO<List<VentaResponse>>> listarPorEstadoFactura(
            @PathVariable EstadoFactura estado) {

        List<VentaResponse> response = ventaService.listarPorEstadoFactura(estado)
                .stream()
                .map(VentaMapper::toResponse)
                .toList();

        ApiResponseDTO<List<VentaResponse>> apiResponse = new ApiResponseDTO<>();
        apiResponse.setSuccess(true);
        apiResponse.setMessage("Ventas obtenidas correctamente.");
        apiResponse.setData(response);

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/fecha/{fecha}")
    public ResponseEntity<ApiResponseDTO<List<VentaResponse>>> listarPorFecha(
            @PathVariable LocalDate fecha) {

        List<VentaResponse> response = ventaService.listarPorFecha(fecha)
                .stream()
                .map(VentaMapper::toResponse)
                .toList();

        ApiResponseDTO<List<VentaResponse>> apiResponse = new ApiResponseDTO<>();
        apiResponse.setSuccess(true);
        apiResponse.setMessage("Ventas obtenidas correctamente.");
        apiResponse.setData(response);

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/rango")
    public ResponseEntity<ApiResponseDTO<List<VentaResponse>>> listarEntreFechas(
            @RequestParam LocalDate inicio,
            @RequestParam LocalDate fin) {

        List<VentaResponse> response = ventaService.listarEntreFechas(inicio, fin)
                .stream()
                .map(VentaMapper::toResponse)
                .toList();

        ApiResponseDTO<List<VentaResponse>> apiResponse = new ApiResponseDTO<>();
        apiResponse.setSuccess(true);
        apiResponse.setMessage("Ventas obtenidas correctamente.");
        apiResponse.setData(response);

        return ResponseEntity.ok(apiResponse);
    }
    
    @GetMapping("/hoy")
    public ResponseEntity<ApiResponseDTO<List<VentaResponse>>> listarVentasHoy() {

        List<VentaResponse> response = ventaService
                .listarVentasHoy()
                .stream()
                .map(VentaMapper::toResponse)
                .toList();

        ApiResponseDTO<List<VentaResponse>> apiResponse = new ApiResponseDTO<>();
        apiResponse.setSuccess(true);
        apiResponse.setMessage("Ventas de hoy obtenidas correctamente.");
        apiResponse.setData(response);

        return ResponseEntity.ok(apiResponse);
    }
    
    @GetMapping("/semana")
    public ResponseEntity<ApiResponseDTO<List<VentaResponse>>> listarVentasSemana() {

        List<VentaResponse> response = ventaService
                .listarVentasSemanaActual()
                .stream()
                .map(VentaMapper::toResponse)
                .toList();

        ApiResponseDTO<List<VentaResponse>> apiResponse = new ApiResponseDTO<>();
        apiResponse.setSuccess(true);
        apiResponse.setMessage("Ventas de la semana obtenidas correctamente.");
        apiResponse.setData(response);

        return ResponseEntity.ok(apiResponse);
    }
    
    @GetMapping("/mes")
    public ResponseEntity<ApiResponseDTO<List<VentaResponse>>> listarVentasMes() {

        List<VentaResponse> response = ventaService
                .listarVentasMesActual()
                .stream()
                .map(VentaMapper::toResponse)
                .toList();

        ApiResponseDTO<List<VentaResponse>> apiResponse = new ApiResponseDTO<>();
        apiResponse.setSuccess(true);
        apiResponse.setMessage("Ventas del mes obtenidas correctamente.");
        apiResponse.setData(response);

        return ResponseEntity.ok(apiResponse);
    }
    
    @GetMapping("/anio")
    public ResponseEntity<ApiResponseDTO<List<VentaResponse>>> listarVentasAnio() {

        List<VentaResponse> response = ventaService
                .listarVentasAnioActual()
                .stream()
                .map(VentaMapper::toResponse)
                .toList();

        ApiResponseDTO<List<VentaResponse>> apiResponse = new ApiResponseDTO<>();
        apiResponse.setSuccess(true);
        apiResponse.setMessage("Ventas del año obtenidas correctamente.");
        apiResponse.setData(response);

        return ResponseEntity.ok(apiResponse);
    }
    
    @GetMapping("/pagos-pendientes")
    public ResponseEntity<ApiResponseDTO<List<VentaResponse>>> listarPagosPendientes() {

        List<VentaResponse> response = ventaService
                .listarPagosPendientes()
                .stream()
                .map(VentaMapper::toResponse)
                .toList();

        ApiResponseDTO<List<VentaResponse>> apiResponse = new ApiResponseDTO<>();
        apiResponse.setSuccess(true);
        apiResponse.setMessage("Pagos pendientes obtenidos correctamente.");
        apiResponse.setData(response);

        return ResponseEntity.ok(apiResponse);

    }
    
    @GetMapping("/facturas-pendientes")
    public ResponseEntity<ApiResponseDTO<List<VentaResponse>>> listarFacturasPendientes() {

        List<VentaResponse> response = ventaService
                .listarFacturasPendientes()
                .stream()
                .map(VentaMapper::toResponse)
                .toList();

        ApiResponseDTO<List<VentaResponse>> apiResponse = new ApiResponseDTO<>();
        apiResponse.setSuccess(true);
        apiResponse.setMessage("Facturas pendientes obtenidas correctamente.");
        apiResponse.setData(response);

        return ResponseEntity.ok(apiResponse);

    }
    
    @PatchMapping("/{id}/confirmar-pago")
    public ResponseEntity<ApiResponseDTO<VentaResponse>> confirmarPago(
            @PathVariable Long id) {

        Venta venta = ventaService.confirmarPago(id);

        ApiResponseDTO<VentaResponse> response = new ApiResponseDTO<>();
        response.setSuccess(true);
        response.setMessage("Pago confirmado correctamente.");
        response.setData(VentaMapper.toResponse(venta));

        return ResponseEntity.ok(response);
    }
    
    @PatchMapping("/{id}/anular-pago")
    public ResponseEntity<ApiResponseDTO<VentaResponse>> anularPago(
            @PathVariable Long id) {

        Venta venta = ventaService.anularPago(id);

        ApiResponseDTO<VentaResponse> response = new ApiResponseDTO<>();
        response.setSuccess(true);
        response.setMessage("Pago anulado correctamente.");
        response.setData(VentaMapper.toResponse(venta));

        return ResponseEntity.ok(response);
    }
    
    @PatchMapping("/{id}/emitir-factura")
    public ResponseEntity<ApiResponseDTO<VentaResponse>> emitirFactura(
            @PathVariable Long id) {

        Venta venta = ventaService.emitirFactura(id);

        ApiResponseDTO<VentaResponse> response = new ApiResponseDTO<>();
        response.setSuccess(true);
        response.setMessage("Factura emitida correctamente.");
        response.setData(VentaMapper.toResponse(venta));

        return ResponseEntity.ok(response);
    }
    
    @PatchMapping("/{id}/anular-factura")
    public ResponseEntity<ApiResponseDTO<VentaResponse>> anularFactura(
            @PathVariable Long id) {

        Venta venta = ventaService.anularFactura(id);

        ApiResponseDTO<VentaResponse> response = new ApiResponseDTO<>();
        response.setSuccess(true);
        response.setMessage("Factura anulada correctamente.");
        response.setData(VentaMapper.toResponse(venta));

        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/pagos-realizados")
    public ResponseEntity<ApiResponseDTO<List<VentaResponse>>> listarPagosRealizados() {

        List<VentaResponse> response = ventaService
                .listarPagosRealizados()
                .stream()
                .map(VentaMapper::toResponse)
                .toList();

        ApiResponseDTO<List<VentaResponse>> apiResponse = new ApiResponseDTO<>();
        apiResponse.setSuccess(true);
        apiResponse.setMessage("Pagos realizados obtenidos correctamente.");
        apiResponse.setData(response);

        return ResponseEntity.ok(apiResponse);
    }
    
    @GetMapping("/facturas-emitidas")
    public ResponseEntity<ApiResponseDTO<List<VentaResponse>>> listarFacturasEmitidas() {

        List<VentaResponse> response = ventaService
                .listarFacturasEmitidas()
                .stream()
                .map(VentaMapper::toResponse)
                .toList();

        ApiResponseDTO<List<VentaResponse>> apiResponse = new ApiResponseDTO<>();
        apiResponse.setSuccess(true);
        apiResponse.setMessage("Facturas emitidas obtenidas correctamente.");
        apiResponse.setData(response);

        return ResponseEntity.ok(apiResponse);
    }

}