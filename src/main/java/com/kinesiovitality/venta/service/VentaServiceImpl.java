package com.kinesiovitality.venta.service;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Year;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kinesiovitality.common.enums.EstadoFactura;
import com.kinesiovitality.common.enums.EstadoPago;
import com.kinesiovitality.common.enums.EstadoRegistro;
import com.kinesiovitality.common.enums.FormaPago;
import com.kinesiovitality.common.exception.ResourceNotFoundException;
import com.kinesiovitality.fisioterapeuta.model.Fisioterapeuta;
import com.kinesiovitality.fisioterapeuta.repository.FisioterapeutaRepository;
import com.kinesiovitality.paciente.model.Paciente;
import com.kinesiovitality.paciente.repository.PacienteRepository;
import com.kinesiovitality.servicio.model.Servicio;
import com.kinesiovitality.servicio.repository.ServicioRepository;
import com.kinesiovitality.sucursal.model.Sucursal;
import com.kinesiovitality.sucursal.repository.SucursalRepository;
import com.kinesiovitality.tratamiento.business.TratamientoBusinessService;
import com.kinesiovitality.venta.model.Venta;
import com.kinesiovitality.venta.repository.VentaRepository;

import jakarta.transaction.Transactional;

@Service
public class VentaServiceImpl implements VentaService {

    private final VentaRepository ventaRepository;
    private final PacienteRepository pacienteRepository;
    private final ServicioRepository servicioRepository;
    private final FisioterapeutaRepository fisioterapeutaRepository;

    private final SucursalRepository sucursalRepository;
    private final TratamientoBusinessService
    tratamientoBusinessService;

    

    
	public VentaServiceImpl(VentaRepository ventaRepository, PacienteRepository pacienteRepository,
			ServicioRepository servicioRepository, FisioterapeutaRepository fisioterapeutaRepository,
			SucursalRepository sucursalRepository, TratamientoBusinessService tratamientoBusinessService) {
		super();
		this.ventaRepository = ventaRepository;
		this.pacienteRepository = pacienteRepository;
		this.servicioRepository = servicioRepository;
		this.fisioterapeutaRepository = fisioterapeutaRepository;
		this.sucursalRepository = sucursalRepository;
		this.tratamientoBusinessService = tratamientoBusinessService;
	}
    
    @Transactional
	@Override
	public Venta guardar(
	        Venta venta,
	        Long pacienteId,
	        Long servicioId,
	        Long fisioterapeutaId,
	        Long sucursalId){

        Paciente paciente = buscarPaciente(pacienteId);
        Servicio servicio = buscarServicio(servicioId);
        Fisioterapeuta fisioterapeuta =
                buscarFisioterapeuta(fisioterapeutaId);

        Sucursal sucursal =
                buscarSucursal(sucursalId);
      

        validarServicioActivo(servicio);
        validarFisioterapeutaActivo(fisioterapeuta);

        validarSucursalActiva(sucursal);

        BigDecimal precioUnitario = servicio.getPrecioVenta();
        BigDecimal descuento = venta.getDescuento() == null
                ? BigDecimal.ZERO
                : venta.getDescuento();

        validarDescuento(precioUnitario, descuento);

        BigDecimal total = precioUnitario.subtract(descuento);

        venta.setPaciente(paciente);
        venta.setServicio(servicio);
        
        venta.setFisioterapeuta(fisioterapeuta);

        venta.setSucursal(sucursal);

        venta.setNombreServicio(servicio.getNombre());
        venta.setCantidadSesiones(servicio.getCantidadSesiones());

        venta.setPrecioUnitario(precioUnitario);
        venta.setDescuento(descuento);
        venta.setTotal(total);

        venta.setPromocion(descuento.compareTo(BigDecimal.ZERO) > 0);

        venta.setCodigoVenta(generarCodigo());

        Venta ventaGuardada =
        		ventaRepository.save(venta);

        		tratamientoBusinessService
        		.procesarVenta(ventaGuardada);

        		return ventaGuardada;
    }

    @Override
    public Venta buscarPorId(Long id) {

        return ventaRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Venta no encontrada."));
    }

    @Override
    public List<Venta> listar() {

        return ventaRepository.findAll();
    }
    
    
    @Transactional
    @Override
    public Venta actualizar(
            Long id,
            Venta venta,
            Long pacienteId,
            Long servicioId,
            Long fisioterapeutaId,
            Long sucursalId) {

        Venta existente = buscarPorId(id);
        if (existente.getEstadoPago() == EstadoPago.ANULADO) {
            throw new IllegalArgumentException(
                    "No es posible actualizar una venta anulada.");
        }
        Paciente paciente = buscarPaciente(pacienteId);

        Servicio servicio = buscarServicio(servicioId);

        Fisioterapeuta fisioterapeuta =
                buscarFisioterapeuta(fisioterapeutaId);

        Sucursal sucursal =
                buscarSucursal(sucursalId);
        
        validarServicioActivo(servicio);

        validarFisioterapeutaActivo(fisioterapeuta);

        validarSucursalActiva(sucursal);
        
        BigDecimal precioUnitario = servicio.getPrecioVenta();

        BigDecimal descuento =
                venta.getDescuento() == null
                        ? BigDecimal.ZERO
                        : venta.getDescuento();

        validarDescuento(precioUnitario, descuento);

        BigDecimal total =
                precioUnitario.subtract(descuento);


     // Relaciones
        existente.setPaciente(paciente);
        existente.setServicio(servicio);
        existente.setFisioterapeuta(fisioterapeuta);
        existente.setSucursal(sucursal);

        // Datos del servicio
        existente.setNombreServicio(servicio.getNombre());
        existente.setCantidadSesiones(servicio.getCantidadSesiones());

        // Valores económicos
        existente.setPrecioUnitario(precioUnitario);
        existente.setDescuento(descuento);
        existente.setTotal(total);

        // Promoción
        existente.setPromocion(
                descuento.compareTo(BigDecimal.ZERO) > 0
        );

        // Estados
        existente.setFormaPago(venta.getFormaPago());
        existente.setEstadoPago(venta.getEstadoPago());
        existente.setEstadoFactura(venta.getEstadoFactura());

        // Observaciones
        existente.setObservaciones(venta.getObservaciones());

        return ventaRepository.save(existente);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {

        Venta venta = buscarPorId(id);

        // No permitir anular dos veces
        if (venta.getEstadoPago() == EstadoPago.ANULADO) {
            throw new IllegalArgumentException(
                    "La venta ya fue anulada.");
        }

        // Primero revertimos el tratamiento
        tratamientoBusinessService.revertirVenta(venta);

        // Luego anulamos la venta
        venta.setEstadoPago(EstadoPago.ANULADO);

        if (venta.getEstadoFactura() == EstadoFactura.PENDIENTE) {
            venta.setEstadoFactura(EstadoFactura.NO_REQUIERE);
        }

        ventaRepository.save(venta);
    }

    @Override
    public List<Venta> listarPorPaciente(Long pacienteId) {

        buscarPaciente(pacienteId);

        return ventaRepository.findByPacienteId(pacienteId);
    }

    @Override
    public List<Venta> listarPorServicio(Long servicioId) {

        buscarServicio(servicioId);

        return ventaRepository.findByServicioId(servicioId);
    }

    @Override
    public List<Venta> listarPorFormaPago(FormaPago formaPago) {

        return ventaRepository.findByFormaPago(formaPago);
    }

    @Override
    public List<Venta> listarPorEstadoPago(EstadoPago estadoPago) {

        return ventaRepository.findByEstadoPago(estadoPago);
    }

    @Override
    public List<Venta> listarPorEstadoFactura(EstadoFactura estadoFactura) {

        return ventaRepository.findByEstadoFactura(estadoFactura);
    }

    @Override
    public List<Venta> listarPorFecha(LocalDate fecha) {

        return ventaRepository.findByFechaVenta(fecha);
    }

    @Override
    public List<Venta> listarEntreFechas(LocalDate inicio, LocalDate fin) {

        return ventaRepository.findByFechaVentaBetween(inicio, fin);
    }
    
    @Override
    public List<Venta> listarVentasHoy() {

        return ventaRepository.findByFechaVenta(LocalDate.now());

    }
    
    @Override
    public List<Venta> listarVentasSemanaActual() {

        LocalDate hoy = LocalDate.now();

        LocalDate inicioSemana = hoy.with(DayOfWeek.MONDAY);

        LocalDate finSemana = hoy.with(DayOfWeek.SUNDAY);

        return ventaRepository.findByFechaVentaBetween(
                inicioSemana,
                finSemana);

    }
    
    @Override
    public List<Venta> listarVentasMesActual() {

        LocalDate hoy = LocalDate.now();

        LocalDate inicioMes = hoy.withDayOfMonth(1);

        LocalDate finMes = hoy.withDayOfMonth(hoy.lengthOfMonth());

        return ventaRepository.findByFechaVentaBetween(
                inicioMes,
                finMes);

    }
    
    @Override
    public List<Venta> listarVentasAnioActual() {

        LocalDate hoy = LocalDate.now();

        LocalDate inicioAnio = hoy.withDayOfYear(1);

        LocalDate finAnio = hoy.withDayOfYear(hoy.lengthOfYear());

        return ventaRepository.findByFechaVentaBetween(
                inicioAnio,
                finAnio);

    }
    
    @Override
    public List<Venta> listarPagosPendientes() {

        return ventaRepository.findByEstadoPago(
                EstadoPago.PENDIENTE);

    }
    
    @Override
    public List<Venta> listarFacturasPendientes() {

        return ventaRepository.findByEstadoFactura(
                EstadoFactura.PENDIENTE);

    }
    
    @Override
    public Venta confirmarPago(Long id) {

        Venta venta = buscarPorId(id);

        if (venta.getEstadoPago() == EstadoPago.PAGADO) {
            throw new IllegalArgumentException(
                    "El pago ya fue confirmado.");
        }

        if (venta.getEstadoPago() == EstadoPago.ANULADO) {
            throw new IllegalArgumentException(
                    "No es posible confirmar un pago anulado.");
        }

        venta.setEstadoPago(EstadoPago.PAGADO);

        return ventaRepository.save(venta);
    }
    
    @Override
    public Venta anularPago(Long id) {

        Venta venta = buscarPorId(id);

        if (venta.getEstadoPago() == EstadoPago.ANULADO) {
            throw new IllegalArgumentException(
                    "El pago ya fue anulado.");
        }

        venta.setEstadoPago(EstadoPago.ANULADO);

        return ventaRepository.save(venta);
    }
    
    @Override
    public Venta emitirFactura(Long id) {

        Venta venta = buscarPorId(id);

        if (venta.getEstadoFactura() == EstadoFactura.EMITIDA) {
            throw new IllegalArgumentException(
                    "La factura ya fue emitida.");
        }

        if (venta.getEstadoFactura() == EstadoFactura.ANULADA) {
            throw new IllegalArgumentException(
                    "No es posible emitir una factura anulada.");
        }

        if (venta.getEstadoPago() != EstadoPago.PAGADO) {
            throw new IllegalArgumentException(
                    "Solo se puede emitir la factura cuando el pago está confirmado.");
        }
        venta.setEstadoFactura(EstadoFactura.EMITIDA);

        return ventaRepository.save(venta);
    }
    
    @Override
    public Venta anularFactura(Long id) {

        Venta venta = buscarPorId(id);

        if (venta.getEstadoFactura() == EstadoFactura.ANULADA) {
            throw new IllegalArgumentException(
                    "La factura ya fue anulada.");
        }

        venta.setEstadoFactura(EstadoFactura.ANULADA);

        return ventaRepository.save(venta);
    }
    
    @Override
    public List<Venta> listarPagosRealizados() {

        return ventaRepository.findByEstadoPago(
                EstadoPago.PAGADO);
    }
    
    @Override
    public List<Venta> listarFacturasEmitidas() {

        return ventaRepository.findByEstadoFactura(
                EstadoFactura.EMITIDA);
    }

    // =========================
    // MÉTODOS PRIVADOS
    // =========================

    private Paciente buscarPaciente(Long id) {

        return pacienteRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Paciente no encontrado."));
    }

    private Servicio buscarServicio(Long id) {

        return servicioRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Servicio no encontrado."));
    }

    private Fisioterapeuta buscarFisioterapeuta(Long id) {

        return fisioterapeutaRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Fisioterapeuta no encontrado."));

    }

    private Sucursal buscarSucursal(Long id) {

        return sucursalRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Sucursal no encontrada."));

    }
    private void validarServicioActivo(Servicio servicio) {

        if (servicio.getActivo() == null || !servicio.getActivo()) {
            throw new IllegalArgumentException(
                    "El servicio no está disponible para la venta.");
        }
    }
    
    private void validarFisioterapeutaActivo(
            Fisioterapeuta fisioterapeuta) {

        if (fisioterapeuta.getEstado() == null
                || fisioterapeuta.getEstado() != EstadoRegistro.ACTIVO) {

            throw new IllegalArgumentException(
                    "El fisioterapeuta no está disponible para registrar ventas.");

        }

    }
    
    private void validarSucursalActiva(
            Sucursal sucursal) {

        if (sucursal.getEstado() == null
                || sucursal.getEstado() != EstadoRegistro.ACTIVO) {

            throw new IllegalArgumentException(
                    "La sucursal no se encuentra activa.");

        }

    }

    private void validarDescuento(BigDecimal precioUnitario, BigDecimal descuento) {

        if (descuento.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(
                    "El descuento no puede ser negativo.");
        }

        if (descuento.compareTo(precioUnitario) > 0) {
            throw new IllegalArgumentException(
                    "El descuento no puede ser mayor al precio del servicio.");
        }
    }

    private String generarCodigo() {

        long siguiente = ventaRepository.count() + 1;

        return String.format(
                "VEN-%d-%06d",
                Year.now().getValue(),
                siguiente);
    }
}