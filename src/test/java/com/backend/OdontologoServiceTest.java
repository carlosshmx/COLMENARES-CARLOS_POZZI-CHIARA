package com.backend;

import com.backend.clinica.dto.entrada.OdontologoEntradaDto;
import com.backend.clinica.dto.salida.OdontologoSalidaDto;
import com.backend.clinica.entity.Odontologo;
import com.backend.clinica.repository.impl.OdontologoDaoH2;
import com.backend.clinica.service.impl.OdontologoService;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.*;

class OdontologoServiceTest {

    private OdontologoService odontologoService;

   /*  @org.junit.jupiter.api.Test
    void deberiaRegistrarUnOdontologoYObtenerElIdCorrespondiente() {
        odontologoService = new OdontologoService(new OdontologoDaoH2(), new ModelMapper());
        OdontologoEntradaDto odontologoEntradaDto = new OdontologoEntradaDto(123456789, "Doctor", "Test");

        OdontologoSalidaDto odontologoRegistrado = odontologoService.registrarOdontologo(odontologoEntradaDto);

        assertNotNull(odontologoRegistrado.getId());
    }

    @org.junit.jupiter.api.Test
    void deberiaRetornarUnaListaDeOdontologosNoVacia() {

        odontologoService = new OdontologoService(new OdontologoDaoH2(), new ModelMapper());

        OdontologoEntradaDto odontologoEntradaDto = new OdontologoEntradaDto(123456789, "Doctor", "Test");

        OdontologoSalidaDto odontologoRegistrado = odontologoService.registrarOdontologo(odontologoEntradaDto);

        assertFalse(odontologoService.listarOdontologos().isEmpty());
    }*/
}