package com.backend.clinica.service;

import com.backend.clinica.entity.Odontologo;
import com.backend.clinica.entity.Turno;

import java.util.List;

public interface IOdontologoService {
    Odontologo registrarOdontologo(Odontologo odontologo);

    Odontologo buscarPorId(Long id);
    List<Odontologo> listarOdontologos();
}
