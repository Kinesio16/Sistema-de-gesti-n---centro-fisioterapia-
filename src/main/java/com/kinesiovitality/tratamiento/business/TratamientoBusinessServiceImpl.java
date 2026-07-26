package com.kinesiovitality.tratamiento.business;

import org.springframework.stereotype.Service;

import com.kinesiovitality.common.enums.EstadoTratamiento;
import com.kinesiovitality.common.exception.ResourceNotFoundException;
import com.kinesiovitality.tratamiento.model.Tratamiento;
import com.kinesiovitality.tratamiento.repository.TratamientoRepository;
import com.kinesiovitality.venta.model.Venta;

@Service
public class TratamientoBusinessServiceImpl
        implements TratamientoBusinessService {

    private final TratamientoRepository tratamientoRepository;

    public TratamientoBusinessServiceImpl(
            TratamientoRepository tratamientoRepository) {

        this.tratamientoRepository = tratamientoRepository;
    }

    @Override
    public void procesarVenta(Venta venta) {

        Tratamiento tratamiento =
                tratamientoRepository
                .findByPacienteIdAndEstado(
                        venta.getPaciente().getId(),
                        EstadoTratamiento.ACTIVO)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "El paciente no posee un tratamiento activo."));

        Integer sesionesCompradas =
                venta.getCantidadSesiones();

        tratamiento.setSesionesPlanificadas(

                tratamiento.getSesionesPlanificadas()

                + sesionesCompradas);

        tratamientoRepository.save(tratamiento);

    }

}