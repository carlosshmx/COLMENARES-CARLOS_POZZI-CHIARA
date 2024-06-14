package com.backend.clinica.service;

import com.backend.clinica.dto.entrada.PacienteEntradaDto;
import com.backend.clinica.dto.entrada.TurnoEntradaDto;
import com.backend.clinica.dto.salida.PacienteSalidaDto;
import com.backend.clinica.dto.salida.TurnoSalidaDto;
import com.backend.clinica.entity.Turno;

import java.util.List;

public interface ITurnoService {

    TurnoSalidaDto registrarTurno(TurnoEntradaDto turnoentradaDto);

    TurnoSalidaDto buscarPorId(Long id);
    List<TurnoSalidaDto> listarTurnos();

    void eliminarTurno(Long id);
    TurnoSalidaDto actualizarTurno(TurnoEntradaDto turnoEntradaDto, Long id);

}
