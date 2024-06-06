package com.backend.clinica.service;

import com.backend.clinica.dto.entrada.TurnoEntradaDto;
import com.backend.clinica.dto.salida.TurnoSalidaDto;
import com.backend.clinica.entity.Turno;

import java.util.List;

public interface ITurnoService {

    TurnoSalidaDto registrarTurno(TurnoEntradaDto turnoentradaDto);

    Turno buscarPorId(Long id);
    List<TurnoSalidaDto> listarTurnos();
}
