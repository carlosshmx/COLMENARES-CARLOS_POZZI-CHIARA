package com.backend.clinica.service;

import com.backend.clinica.entity.Turno;

import java.util.List;

public interface ITurnoService {

    Turno registrarTurno(Turno turno);

    Turno buscarPorId(Long id);
    List<Turno> listarTurnos();
}
