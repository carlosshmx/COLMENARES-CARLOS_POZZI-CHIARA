package com.backend.clinica.service;

import com.backend.clinica.dto.entrada.TurnoEntradaDto;
import com.backend.clinica.dto.salida.TurnoSalidaDto;
import com.backend.clinica.exceptions.BadRequestException;
import com.backend.clinica.exceptions.ResourceNotFoundException;

import java.util.List;

public interface ITurnoService {

    TurnoSalidaDto registrarTurno(TurnoEntradaDto turnoentradaDto) throws BadRequestException;

    TurnoSalidaDto buscarPorId(Long id);
    List<TurnoSalidaDto> listarTurnos();

    void eliminarTurno(Long id) throws ResourceNotFoundException;
    TurnoSalidaDto actualizarTurno(TurnoEntradaDto turnoEntradaDto, Long id) throws BadRequestException, ResourceNotFoundException;

}
