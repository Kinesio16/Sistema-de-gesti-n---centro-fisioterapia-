package com.kinesiovitality.sucursal.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kinesiovitality.common.enums.EstadoRegistro;
import com.kinesiovitality.common.exception.ResourceNotFoundException;
import com.kinesiovitality.sucursal.model.Sucursal;
import com.kinesiovitality.sucursal.repository.SucursalRepository;

@Service
public class SucursalServiceImpl implements SucursalService {

    private final SucursalRepository sucursalRepository;

    public SucursalServiceImpl(SucursalRepository sucursalRepository) {
        this.sucursalRepository = sucursalRepository;
    }

    @Override
    public Sucursal guardar(Sucursal sucursal) {

        if (sucursalRepository.existsByNombreIgnoreCase(sucursal.getNombre())) {

            throw new IllegalArgumentException(
                    "Ya existe una sucursal con ese nombre."
            );

        }

        if (sucursal.getCorreo() != null
                && !sucursal.getCorreo().isBlank()
                && sucursalRepository.existsByCorreo(sucursal.getCorreo())) {

            throw new IllegalArgumentException(
                    "Ya existe una sucursal con ese correo."
            );

        }

        return sucursalRepository.save(sucursal);

    }

    @Override
    public Sucursal buscarPorId(Long id) {

        return sucursalRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Sucursal no encontrada."
                        ));

    }

    @Override
    public List<Sucursal> listar() {

        return sucursalRepository.findAll();

    }

    @Override
    public Sucursal actualizar(Long id, Sucursal sucursal) {

        Sucursal existente = buscarPorId(id);

        if (!existente.getNombre().equals(sucursal.getNombre())
                && sucursalRepository.existsByNombreIgnoreCase(sucursal.getNombre())) {

            throw new IllegalArgumentException(
                    "El nombre de la sucursal ya está registrado."
            );

        }

        if (sucursal.getCorreo() != null
                && !sucursal.getCorreo().isBlank()
                && !sucursal.getCorreo().equals(existente.getCorreo())
                && sucursalRepository.existsByCorreo(sucursal.getCorreo())) {

            throw new IllegalArgumentException(
                    "El correo ya está registrado."
            );

        }

        existente.setNombre(sucursal.getNombre());
        existente.setDireccion(sucursal.getDireccion());
        existente.setTelefono(sucursal.getTelefono());
        existente.setCorreo(sucursal.getCorreo());

        return sucursalRepository.save(existente);

    }

    @Override
    public void eliminar(Long id) {

        Sucursal sucursal = buscarPorId(id);

        sucursal.setEstado(EstadoRegistro.INACTIVO);

        sucursalRepository.save(sucursal);

    }
    
    @Override
    public void reactivar(Long id) {

        Sucursal sucursal = buscarPorId(id);

        if (sucursal.getEstado() == EstadoRegistro.ACTIVO) {

            throw new IllegalArgumentException(
                    "La sucursal ya se encuentra activa."
            );

        }

        sucursal.setEstado(EstadoRegistro.ACTIVO);

        sucursalRepository.save(sucursal);

    }
    
    @Override
    public List<Sucursal> listarActivas() {

        return sucursalRepository.findByEstado(EstadoRegistro.ACTIVO);

    }

}