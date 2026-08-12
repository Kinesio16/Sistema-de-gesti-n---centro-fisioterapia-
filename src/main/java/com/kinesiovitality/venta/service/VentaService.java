package com.kinesiovitality.venta.service;

import java.time.LocalDate;
import java.util.List;

import com.kinesiovitality.common.enums.EstadoFactura;
import com.kinesiovitality.common.enums.EstadoPago;
import com.kinesiovitality.common.enums.FormaPago;
import com.kinesiovitality.venta.model.Venta;

public interface VentaService {

	Venta guardar(Venta venta,
            Long pacienteId,
            Long servicioId,
            Long fisioterapeutaId,
            Long sucursalId);

    Venta buscarPorId(Long id);

    List<Venta> listar();

    Venta actualizar(Long id,
                     Venta venta);

    void eliminar(Long id);

    List<Venta> listarPorPaciente(Long pacienteId);

    List<Venta> listarPorServicio(Long servicioId);

    List<Venta> listarPorFormaPago(FormaPago formaPago);

    List<Venta> listarPorEstadoPago(EstadoPago estadoPago);

    List<Venta> listarPorEstadoFactura(EstadoFactura estadoFactura);

    List<Venta> listarPorFecha(LocalDate fecha);

    List<Venta> listarEntreFechas(LocalDate inicio,
                                  LocalDate fin);
    
    List<Venta> listarVentasHoy();

    List<Venta> listarVentasSemanaActual();

    List<Venta> listarVentasMesActual();

    List<Venta> listarVentasAnioActual();
    
    List<Venta> listarPagosPendientes();

    List<Venta> listarFacturasPendientes();
    
    Venta confirmarPago(Long id);

    Venta anularPago(Long id);

    Venta emitirFactura(Long id);

    Venta anularFactura(Long id);

    List<Venta> listarPagosRealizados();

    List<Venta> listarFacturasEmitidas();

}