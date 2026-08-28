package com.kinesiovitality.tratamiento.business;

import org.springframework.stereotype.Service;  


import com.kinesiovitality.common.enums.EstadoTratamiento;
import com.kinesiovitality.sesion.model.Sesion;
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

        tratamientoRepository
                .findByPacienteIdAndEstado(
                        venta.getPaciente().getId(),
                        EstadoTratamiento.ACTIVO)
                .ifPresent(tratamiento -> {

                    Integer sesionesCompradas = venta.getCantidadSesiones();

                    tratamiento.setSesionesPlanificadas(
                            tratamiento.getSesionesPlanificadas()
                                    + sesionesCompradas);

                    tratamientoRepository.save(tratamiento);
                });

    }
    @Override
    public void revertirVenta(Venta venta) {

    	Tratamiento tratamiento =
    	        tratamientoRepository
    	                .findByPacienteIdAndEstado(
    	                        venta.getPaciente().getId(),
    	                        EstadoTratamiento.ACTIVO)
    	                .orElse(null);

    	if (tratamiento == null) {
    	    return;
    	}

        int sesionesVenta = venta.getCantidadSesiones();

        // Validación importante
        if (tratamiento.getSesionesPendientes() < sesionesVenta) {

            throw new IllegalArgumentException(
                    "No es posible anular la venta porque el paciente ya ha utilizado parte de las sesiones adquiridas.");
        }

        tratamiento.setSesionesPlanificadas(
                tratamiento.getSesionesPlanificadas() - sesionesVenta);

        tratamientoRepository.save(tratamiento);

    }
    
    @Override
    public void registrarSesion(Sesion sesion) {

        Tratamiento tratamiento = sesion.getTratamiento();

        Integer realizadas =
                tratamiento.getSesionesRealizadas() == null
                        ? 0
                        : tratamiento.getSesionesRealizadas();

        Integer planificadas =
                tratamiento.getSesionesPlanificadas() == null
                        ? 0
                        : tratamiento.getSesionesPlanificadas();

        if (realizadas >= planificadas) {
            throw new IllegalArgumentException(
                    "El tratamiento ya alcanzó el número de sesiones planificadas.");
        }

        tratamiento.setSesionesRealizadas(realizadas + 1);

     // Si ya no quedan sesiones pendientes,
     // el tratamiento se finaliza automáticamente.
     if (tratamiento.getSesionesRealizadas()
             .equals(tratamiento.getSesionesPlanificadas())) {

         tratamiento.setEstado(EstadoTratamiento.FINALIZADO);
         tratamiento.setFechaAlta(java.time.LocalDate.now());

     }

     tratamientoRepository.save(tratamiento);
    }
    
    @Override
    public void eliminarSesion(Sesion sesion) {

        Tratamiento tratamiento = sesion.getTratamiento();

        Integer realizadas =
                tratamiento.getSesionesRealizadas() == null
                        ? 0
                        : tratamiento.getSesionesRealizadas();

        if (realizadas == 0) {
            return;
        }

        tratamiento.setSesionesRealizadas(realizadas - 1);

        if (tratamiento.getEstado() == EstadoTratamiento.FINALIZADO) {

            tratamiento.setEstado(EstadoTratamiento.ACTIVO);
            tratamiento.setFechaAlta(null);

        }

        tratamientoRepository.save(tratamiento);
    }

}