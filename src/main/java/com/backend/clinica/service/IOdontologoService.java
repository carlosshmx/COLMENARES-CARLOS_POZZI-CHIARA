package com.backend.clinica.service;

import com.backend.clinica.dto.entrada.OdontologoEntradaDto;
import com.backend.clinica.dto.salida.OdontologoSalidaDto;
import com.backend.clinica.exceptions.ResourceNotFoundException;

import java.util.List;

public interface IOdontologoService {
    OdontologoSalidaDto registrarOdontologo(OdontologoEntradaDto odontologoEntradaDto);

    OdontologoSalidaDto buscarPorId(Long id);
    List<OdontologoSalidaDto> listarOdontologos();

    OdontologoSalidaDto actualizarOdontologo(OdontologoEntradaDto odontologoEntradaDto, Long id) throws ResourceNotFoundException;

    void eliminarOdontologo(Long id) throws ResourceNotFoundException;

}
