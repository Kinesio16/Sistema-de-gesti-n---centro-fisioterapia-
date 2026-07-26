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
import com.kinesiovitality.common.response.ApiResponse;
import com.kinesiovitality.venta.dto.VentaRequest;
import com.kinesiovitality.venta.dto.VentaResponse;
import com.kinesiovitality.venta.mapper.VentaMapper;
import com.kinesiovitality.venta.model.Venta;
import com.kinesiovitality.venta.service.VentaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/ventas")
@Validated
public class VentaController {

    private final VentaService ventaService;

    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<VentaResponse>>> listar() {

        List<VentaResponse> response = ventaService.listar()
                .stream()
                .map(VentaMapper::toResponse)
                .toList();

        ApiResponse<List<VentaResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setSuccess(true);
        apiResponse.setMessage("Ventas obtenidas correctamente.");
        apiResponse.setData(response);

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<VentaResponse>> buscarPorId(
            @PathVariable Long id) {

        Venta venta = ventaService.buscarPorId(id);

        ApiResponse<VentaResponse> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setMessage("Venta encontrada.");
        response.setData(VentaMapper.toResponse(venta));

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<VentaResponse>> guardar(
            @Valid @RequestBody VentaRequest request) {

        Venta venta = VentaMapper.toEntity(request);

        Venta guardada = ventaService.guardar(
                venta,
                request.getPacienteId(),
                request.getServicioId());

        ApiResponse<VentaResponse> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setMessage("Venta registrada correctamente.");
        response.setData(VentaMapper.toResponse(guardada));

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<VentaResponse>> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody VentaRequest request) {

        Venta venta = VentaMapper.toEntity(request);

        Venta actualizada = ventaService.actualizar(id, venta);

        ApiResponse<VentaResponse> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setMessage("Venta actualizada correctamente.");
        response.setData(VentaMapper.toResponse(actualizada));

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(
            @PathVariable Long id) {

        ventaService.eliminar(id);

        ApiResponse<Void> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setMessage("Venta anulada correctamente.");

        return ResponseEntity.ok(response);
    }

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<ApiResponse<List<VentaResponse>>> listarPorPaciente(
            @PathVariable Long pacienteId) {

        List<VentaResponse> response = ventaService.listarPorPaciente(pacienteId)
                .stream()
                .map(VentaMapper::toResponse)
                .toList();

        ApiResponse<List<VentaResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setSuccess(true);
        apiResponse.setMessage("Ventas obtenidas correctamente.");
        apiResponse.setData(response);

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/servicio/{servicioId}")
    public ResponseEntity<ApiResponse<List<VentaResponse>>> listarPorServicio(
            @PathVariable Long servicioId) {

        List<VentaResponse> response = ventaService.listarPorServicio(servicioId)
                .stream()
                .map(VentaMapper::toResponse)
                .toList();

        ApiResponse<List<VentaResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setSuccess(true);
        apiResponse.setMessage("Ventas obtenidas correctamente.");
        apiResponse.setData(response);

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/forma-pago/{formaPago}")
    public ResponseEntity<ApiResponse<List<VentaResponse>>> listarPorFormaPago(
            @PathVariable FormaPago formaPago) {

        List<VentaResponse> response = ventaService.listarPorFormaPago(formaPago)
                .stream()
                .map(VentaMapper::toResponse)
                .toList();

        ApiResponse<List<VentaResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setSuccess(true);
        apiResponse.setMessage("Ventas obtenidas correctamente.");
        apiResponse.setData(response);

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/estado-pago/{estado}")
    public ResponseEntity<ApiResponse<List<VentaResponse>>> listarPorEstadoPago(
            @PathVariable EstadoPago estado) {

        List<VentaResponse> response = ventaService.listarPorEstadoPago(estado)
                .stream()
                .map(VentaMapper::toResponse)
                .toList();

        ApiResponse<List<VentaResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setSuccess(true);
        apiResponse.setMessage("Ventas obtenidas correctamente.");
        apiResponse.setData(response);

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/estado-factura/{estado}")
    public ResponseEntity<ApiResponse<List<VentaResponse>>> listarPorEstadoFactura(
            @PathVariable EstadoFactura estado) {

        List<VentaResponse> response = ventaService.listarPorEstadoFactura(estado)
                .stream()
                .map(VentaMapper::toResponse)
                .toList();

        ApiResponse<List<VentaResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setSuccess(true);
        apiResponse.setMessage("Ventas obtenidas correctamente.");
        apiResponse.setData(response);

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/fecha/{fecha}")
    public ResponseEntity<ApiResponse<List<VentaResponse>>> listarPorFecha(
            @PathVariable LocalDate fecha) {

        List<VentaResponse> response = ventaService.listarPorFecha(fecha)
                .stream()
                .map(VentaMapper::toResponse)
                .toList();

        ApiResponse<List<VentaResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setSuccess(true);
        apiResponse.setMessage("Ventas obtenidas correctamente.");
        apiResponse.setData(response);

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/rango")
    public ResponseEntity<ApiResponse<List<VentaResponse>>> listarEntreFechas(
            @RequestParam LocalDate inicio,
            @RequestParam LocalDate fin) {

        List<VentaResponse> response = ventaService.listarEntreFechas(inicio, fin)
                .stream()
                .map(VentaMapper::toResponse)
                .toList();

        ApiResponse<List<VentaResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setSuccess(true);
        apiResponse.setMessage("Ventas obtenidas correctamente.");
        apiResponse.setData(response);

        return ResponseEntity.ok(apiResponse);
    }
    
    @GetMapping("/hoy")
    public ResponseEntity<ApiResponse<List<VentaResponse>>> listarVentasHoy() {

        List<VentaResponse> response = ventaService
                .listarVentasHoy()
                .stream()
                .map(VentaMapper::toResponse)
                .toList();

        ApiResponse<List<VentaResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setSuccess(true);
        apiResponse.setMessage("Ventas de hoy obtenidas correctamente.");
        apiResponse.setData(response);

        return ResponseEntity.ok(apiResponse);
    }
    
    @GetMapping("/semana")
    public ResponseEntity<ApiResponse<List<VentaResponse>>> listarVentasSemana() {

        List<VentaResponse> response = ventaService
                .listarVentasSemanaActual()
                .stream()
                .map(VentaMapper::toResponse)
                .toList();

        ApiResponse<List<VentaResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setSuccess(true);
        apiResponse.setMessage("Ventas de la semana obtenidas correctamente.");
        apiResponse.setData(response);

        return ResponseEntity.ok(apiResponse);
    }
    
    @GetMapping("/mes")
    public ResponseEntity<ApiResponse<List<VentaResponse>>> listarVentasMes() {

        List<VentaResponse> response = ventaService
                .listarVentasMesActual()
                .stream()
                .map(VentaMapper::toResponse)
                .toList();

        ApiResponse<List<VentaResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setSuccess(true);
        apiResponse.setMessage("Ventas del mes obtenidas correctamente.");
        apiResponse.setData(response);

        return ResponseEntity.ok(apiResponse);
    }
    
    @GetMapping("/anio")
    public ResponseEntity<ApiResponse<List<VentaResponse>>> listarVentasAnio() {

        List<VentaResponse> response = ventaService
                .listarVentasAnioActual()
                .stream()
                .map(VentaMapper::toResponse)
                .toList();

        ApiResponse<List<VentaResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setSuccess(true);
        apiResponse.setMessage("Ventas del año obtenidas correctamente.");
        apiResponse.setData(response);

        return ResponseEntity.ok(apiResponse);
    }
    
    @GetMapping("/pagos-pendientes")
    public ResponseEntity<ApiResponse<List<VentaResponse>>> listarPagosPendientes() {

        List<VentaResponse> response = ventaService
                .listarPagosPendientes()
                .stream()
                .map(VentaMapper::toResponse)
                .toList();

        ApiResponse<List<VentaResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setSuccess(true);
        apiResponse.setMessage("Pagos pendientes obtenidos correctamente.");
        apiResponse.setData(response);

        return ResponseEntity.ok(apiResponse);

    }
    
    @GetMapping("/facturas-pendientes")
    public ResponseEntity<ApiResponse<List<VentaResponse>>> listarFacturasPendientes() {

        List<VentaResponse> response = ventaService
                .listarFacturasPendientes()
                .stream()
                .map(VentaMapper::toResponse)
                .toList();

        ApiResponse<List<VentaResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setSuccess(true);
        apiResponse.setMessage("Facturas pendientes obtenidas correctamente.");
        apiResponse.setData(response);

        return ResponseEntity.ok(apiResponse);

    }
    
    @PatchMapping("/{id}/confirmar-pago")
    public ResponseEntity<ApiResponse<VentaResponse>> confirmarPago(
            @PathVariable Long id) {

        Venta venta = ventaService.confirmarPago(id);

        ApiResponse<VentaResponse> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setMessage("Pago confirmado correctamente.");
        response.setData(VentaMapper.toResponse(venta));

        return ResponseEntity.ok(response);
    }
    
    @PatchMapping("/{id}/anular-pago")
    public ResponseEntity<ApiResponse<VentaResponse>> anularPago(
            @PathVariable Long id) {

        Venta venta = ventaService.anularPago(id);

        ApiResponse<VentaResponse> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setMessage("Pago anulado correctamente.");
        response.setData(VentaMapper.toResponse(venta));

        return ResponseEntity.ok(response);
    }
    
    @PatchMapping("/{id}/emitir-factura")
    public ResponseEntity<ApiResponse<VentaResponse>> emitirFactura(
            @PathVariable Long id) {

        Venta venta = ventaService.emitirFactura(id);

        ApiResponse<VentaResponse> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setMessage("Factura emitida correctamente.");
        response.setData(VentaMapper.toResponse(venta));

        return ResponseEntity.ok(response);
    }
    
    @PatchMapping("/{id}/anular-factura")
    public ResponseEntity<ApiResponse<VentaResponse>> anularFactura(
            @PathVariable Long id) {

        Venta venta = ventaService.anularFactura(id);

        ApiResponse<VentaResponse> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setMessage("Factura anulada correctamente.");
        response.setData(VentaMapper.toResponse(venta));

        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/pagos-realizados")
    public ResponseEntity<ApiResponse<List<VentaResponse>>> listarPagosRealizados() {

        List<VentaResponse> response = ventaService
                .listarPagosRealizados()
                .stream()
                .map(VentaMapper::toResponse)
                .toList();

        ApiResponse<List<VentaResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setSuccess(true);
        apiResponse.setMessage("Pagos realizados obtenidos correctamente.");
        apiResponse.setData(response);

        return ResponseEntity.ok(apiResponse);
    }
    
    @GetMapping("/facturas-emitidas")
    public ResponseEntity<ApiResponse<List<VentaResponse>>> listarFacturasEmitidas() {

        List<VentaResponse> response = ventaService
                .listarFacturasEmitidas()
                .stream()
                .map(VentaMapper::toResponse)
                .toList();

        ApiResponse<List<VentaResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setSuccess(true);
        apiResponse.setMessage("Facturas emitidas obtenidas correctamente.");
        apiResponse.setData(response);

        return ResponseEntity.ok(apiResponse);
    }

}