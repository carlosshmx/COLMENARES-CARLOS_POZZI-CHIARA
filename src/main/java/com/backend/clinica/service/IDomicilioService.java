package com.backend.clinica.service;

import com.backend.clinica.entity.Domicilio;

import java.util.List;

public interface IDomicilioService {
    Domicilio registrarDomicilio(Domicilio domicilio);

    Domicilio buscarPorId(Long id);

    List<Domicilio> listarDomicilios();
}
