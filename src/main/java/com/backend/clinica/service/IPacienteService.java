package com.backend.clinica.service;

import com.backend.clinica.entity.Paciente;


import java.util.List;

public interface IPacienteService {

    Paciente registrarPaciente(Paciente paciente);

    Paciente buscarPorId(Long id);
    List<Paciente> listarPacientes();
}
