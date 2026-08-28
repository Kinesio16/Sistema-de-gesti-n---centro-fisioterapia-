package com.kinesiovitality.dashboard.service;

import java.math.BigDecimal;

import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kinesiovitality.common.enums.EstadoTratamiento;
import com.kinesiovitality.cita.repository.CitaRepository;
import com.kinesiovitality.common.enums.EstadoCita;
import com.kinesiovitality.common.enums.EstadoRegistro;
import com.kinesiovitality.common.enums.EstadoSesion;
import com.kinesiovitality.dashboard.dto.DashboardResponse;
import com.kinesiovitality.paciente.repository.PacienteRepository;
import com.kinesiovitality.fisioterapeuta.repository.FisioterapeutaRepository;
import com.kinesiovitality.tratamiento.model.Tratamiento;
import com.kinesiovitality.tratamiento.repository.TratamientoRepository;
import com.kinesiovitality.sesion.repository.SesionRepository;
import com.kinesiovitality.venta.model.Venta;
import com.kinesiovitality.venta.repository.VentaRepository;
import com.kinesiovitality.common.enums.EstadoPago;

@Service
public class DashboardServiceImpl implements DashboardService {

    private final PacienteRepository pacienteRepository;
    private final FisioterapeutaRepository fisioterapeutaRepository;
    private final TratamientoRepository tratamientoRepository;
    private final SesionRepository sesionRepository;
    private final VentaRepository ventaRepository;
    private final CitaRepository citaRepository;

    public DashboardServiceImpl(
            PacienteRepository pacienteRepository,
            FisioterapeutaRepository fisioterapeutaRepository,
            TratamientoRepository tratamientoRepository,
            SesionRepository sesionRepository,
            VentaRepository ventaRepository,
            CitaRepository citaRepository) {

        this.pacienteRepository = pacienteRepository;
        this.fisioterapeutaRepository = fisioterapeutaRepository;
        this.tratamientoRepository = tratamientoRepository;
        this.sesionRepository = sesionRepository;
        this.ventaRepository = ventaRepository;
        this.citaRepository = citaRepository;
    }

    @Override
    public DashboardResponse obtenerResumen() {

        LocalDate hoy = LocalDate.now();
        LocalDate inicioSemana = hoy.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate finSemana = hoy.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
        LocalDate inicioMes = hoy.withDayOfMonth(1);
        LocalDate finMes = hoy.withDayOfMonth(hoy.lengthOfMonth());
        LocalDate inicioAnio = hoy.withDayOfYear(1);
        LocalDate finAnio = hoy.withDayOfYear(hoy.lengthOfYear());

        DashboardResponse response = new DashboardResponse();

        // Pacientes
        response.setTotalPacientes(pacienteRepository.count());
        response.setPacientesActivos(
                pacienteRepository.countByEstado(EstadoRegistro.ACTIVO));
        response.setPacientesNuevosMes(
                pacienteRepository.countByFechaCreacionBetween(inicioMes.atStartOfDay(), finMes.plusDays(1).atStartOfDay()));

        // Fisioterapeutas
        response.setTotalFisioterapeutas(fisioterapeutaRepository.count());
        response.setFisioterapeutasActivos(
                fisioterapeutaRepository.countByEstado(EstadoRegistro.ACTIVO));

        // Tratamientos
        response.setTratamientosActivos(tratamientoRepository.countByEstado(EstadoTratamiento.ACTIVO));
        response.setTratamientosSuspendidos(tratamientoRepository.countByEstado(EstadoTratamiento.SUSPENDIDO));
        response.setTratamientosFinalizados(tratamientoRepository.countByEstado(EstadoTratamiento.FINALIZADO));
        response.setTratamientosCancelados(tratamientoRepository.countByEstado(EstadoTratamiento.CANCELADO));
        List<Tratamiento> tratamientos =
                tratamientoRepository.findByEstado(EstadoTratamiento.ACTIVO);

        long pendientes = 0;

        for (Tratamiento tratamiento : tratamientos) {

            pendientes += tratamiento.getSesionesPendientes();

        }

        response.setSesionesPendientes(pendientes);

        // Sesiones
        response.setSesionesHoy(
                sesionRepository.countByFechaSesionAndEstado(hoy, EstadoSesion.REALIZADA));
        response.setSesionesSemana(
                sesionRepository.countByFechaSesionBetweenAndEstado(
                        inicioSemana,
                        finSemana,
                        EstadoSesion.REALIZADA));
        response.setSesionesMes(
                sesionRepository.countByFechaSesionBetweenAndEstado(
                        inicioMes,
                        finMes,
                        EstadoSesion.REALIZADA));
        
     // Citas
        response.setCitasHoy(
                citaRepository.countByFecha(hoy));

        response.setCitasPendientes(
                citaRepository.countByEstadoIn(
                        Arrays.asList(
                                EstadoCita.PENDIENTE,
                                EstadoCita.CONFIRMADA)));

        // Ventas
     // Ventas (solo las que NO están anuladas)
        List<Venta> ventasHoy =
                ventaRepository.findByFechaVentaAndEstadoPagoNot(
                        hoy,
                        EstadoPago.ANULADO);

        List<Venta> ventasSemana =
                ventaRepository.findByFechaVentaBetweenAndEstadoPagoNot(
                        inicioSemana,
                        finSemana,
                        EstadoPago.ANULADO);

        List<Venta> ventasMes =
                ventaRepository.findByFechaVentaBetweenAndEstadoPagoNot(
                        inicioMes,
                        finMes,
                        EstadoPago.ANULADO);

        List<Venta> ventasAnio =
                ventaRepository.findByFechaVentaBetweenAndEstadoPagoNot(
                        inicioAnio,
                        finAnio,
                        EstadoPago.ANULADO);

        response.setVentasHoy((long) ventasHoy.size());
        response.setVentasSemana((long) ventasSemana.size());
        response.setVentasMes((long) ventasMes.size());
        response.setVentasAnio((long) ventasAnio.size());

        // Ingresos
        response.setIngresosHoy(sumarTotales(ventasHoy));
        response.setIngresosSemana(sumarTotales(ventasSemana));
        response.setIngresosMes(sumarTotales(ventasMes));
        response.setIngresosAnio(sumarTotales(ventasAnio));
        
        if (response.getVentasMes() > 0) {

            response.setTicketPromedio(
                    response.getIngresosMes().divide(
                            BigDecimal.valueOf(response.getVentasMes()),
                            2,
                            RoundingMode.HALF_UP));

        } else {

            response.setTicketPromedio(BigDecimal.ZERO);

        }
        
     // Temporal:
     // Actualmente cada venta representa un paquete o servicio.
     // Cuando se implemente TipoVenta, este indicador deberá
     // contar únicamente las ventas de tipo PAQUETE.
     response.setPaquetesVendidosMes(response.getVentasMes());

        return response;
    }

    private BigDecimal sumarTotales(List<Venta> ventas) {

        BigDecimal total = BigDecimal.ZERO;

        for (Venta venta : ventas) {
            if (venta.getTotal() != null) {
                total = total.add(venta.getTotal());
            }
        }

        return total;
    }
}