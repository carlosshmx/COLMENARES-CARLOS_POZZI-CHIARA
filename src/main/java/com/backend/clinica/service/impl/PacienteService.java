package com.backend.clinica.service.impl;

import com.backend.clinica.entity.Odontologo;
import com.backend.clinica.entity.Paciente;
import com.backend.clinica.repository.IDao;
import com.backend.clinica.service.IPacienteService;

import java.util.List;

public class PacienteService implements IPacienteService {

    private IDao<Paciente> pacienteIDao;

    public PacienteService(IDao<Paciente> pacienteIDao) {
        this.pacienteIDao = pacienteIDao;
    }

    @Override
    public Paciente registrarPaciente(Paciente paciente) {
        return pacienteIDao.registrar(paciente);
    }

    @Override
    public Paciente buscarPorId(Long Id){
        return pacienteIDao.buscarPorId(Id);
    }
    @Override
    public List<Paciente> listarPacientes() {
        return pacienteIDao.listarTodos();
    }
}
