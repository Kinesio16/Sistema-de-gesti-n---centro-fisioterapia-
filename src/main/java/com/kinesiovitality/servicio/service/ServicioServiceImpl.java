package com.kinesiovitality.servicio.service;

import java.time.Year;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kinesiovitality.common.exception.ResourceNotFoundException;
import com.kinesiovitality.servicio.model.Servicio;
import com.kinesiovitality.servicio.repository.ServicioRepository;

@Service
public class ServicioServiceImpl implements ServicioService {

    private final ServicioRepository servicioRepository;

    public ServicioServiceImpl(ServicioRepository servicioRepository) {
        this.servicioRepository = servicioRepository;
    }

    @Override
    public Servicio guardar(Servicio servicio) {

        validarNombreDuplicado(servicio.getNombre());

        servicio.setCodigoServicio(generarCodigo());

        return servicioRepository.save(servicio);
    }

    @Override
    public Servicio buscarPorId(Long id) {

        return servicioRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Servicio no encontrado."));
    }

    @Override
    public List<Servicio> listar() {

        return servicioRepository.findAll();
    }

    @Override
    public List<Servicio> listarActivos() {

        return servicioRepository.findByActivoTrue();
    }

    @Override
    public List<Servicio> buscarPorNombre(String nombre) {

        return servicioRepository.findByNombreContainingIgnoreCase(nombre);
    }

    @Override
    public Servicio actualizar(Long id, Servicio servicio) {

        Servicio existente = buscarPorId(id);

        validarNombreDuplicadoActualizar(
                servicio.getNombre(),
                existente.getId());

        existente.setNombre(servicio.getNombre());
        existente.setDescripcion(servicio.getDescripcion());
        existente.setCantidadSesiones(servicio.getCantidadSesiones());
        existente.setPrecioCosto(servicio.getPrecioCosto());
        existente.setPrecioVenta(servicio.getPrecioVenta());

        return servicioRepository.save(existente);
    }

    @Override
    public void eliminar(Long id) {

        Servicio servicio = buscarPorId(id);
        
        if (!servicio.getActivo()) {

            throw new IllegalArgumentException(
                    "El servicio ya se encuentra inactivo.");
        }

        servicio.setActivo(false);

        servicioRepository.save(servicio);
    }

    // ==========================
    // MÉTODOS PRIVADOS
    // ==========================

    private void validarNombreDuplicado(String nombre) {

        if (servicioRepository.existsByNombreIgnoreCase(nombre)) {

            throw new IllegalArgumentException(
                    "Ya existe un servicio con ese nombre.");
        }

    }

    private void validarNombreDuplicadoActualizar(
            String nombre,
            Long idActual) {

        servicioRepository.findByNombre(nombre)
                .ifPresent(servicio -> {

                    if (!servicio.getId().equals(idActual)) {

                        throw new IllegalArgumentException(
                                "Ya existe un servicio con ese nombre.");
                    }

                });

    }

    private String generarCodigo() {

        long siguiente = servicioRepository.count() + 1;

        return String.format(
                "SER-%d-%06d",
                Year.now().getValue(),
                siguiente);
    }

}
