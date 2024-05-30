package com.backend.clinica.service.impl;

import com.backend.clinica.entity.Turno;
import com.backend.clinica.repository.IDao;
import com.backend.clinica.service.ITurnoService;

import java.util.List;

public class TurnoService implements ITurnoService {

    private IDao<Turno> turnoIDao;

    public TurnoService(IDao<Turno> turnoIDao) {
        this.turnoIDao = turnoIDao;
    }

    @Override
    public Turno registrarTurno(Turno turno) {
        return turnoIDao.registrar(turno);
    }
    @Override
    public Turno buscarPorId(Long Id){
        return turnoIDao.buscarPorId(Id);
    }
    @Override
    public List<Turno> listarTurnos() {
        return turnoIDao.listarTodos();
    }
}
