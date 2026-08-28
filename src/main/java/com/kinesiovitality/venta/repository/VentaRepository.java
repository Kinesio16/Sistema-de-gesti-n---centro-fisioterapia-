package com.kinesiovitality.venta.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kinesiovitality.common.enums.EstadoFactura;
import com.kinesiovitality.common.enums.EstadoPago;
import com.kinesiovitality.common.enums.FormaPago;
import com.kinesiovitality.venta.model.Venta;

public interface VentaRepository extends JpaRepository<Venta, Long> {

    Optional<Venta> findByCodigoVenta(String codigoVenta);

    List<Venta> findByPacienteId(Long pacienteId);

    List<Venta> findByServicioId(Long servicioId);

    List<Venta> findByEstadoPago(EstadoPago estadoPago);

    List<Venta> findByEstadoFactura(EstadoFactura estadoFactura);

    List<Venta> findByFormaPago(FormaPago formaPago);

    List<Venta> findByFechaVenta(LocalDate fechaVenta);

    List<Venta> findByFechaVentaBetween(LocalDate inicio,
                                        LocalDate fin);
    List<Venta> findByFechaVentaAndEstadoPagoNot(
            LocalDate fechaVenta,
            EstadoPago estadoPago);

    List<Venta> findByFechaVentaBetweenAndEstadoPagoNot(
            LocalDate inicio,
            LocalDate fin,
            EstadoPago estadoPago);

    List<Venta> findByPromocionTrue();

    List<Venta> findByPromocionFalse();
    
    boolean existsByPacienteIdAndEstadoPago(Long pacienteId,
            EstadoPago estadoPago);
}
