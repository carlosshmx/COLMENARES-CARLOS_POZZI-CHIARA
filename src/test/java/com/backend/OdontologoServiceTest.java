package com.backend;

import com.backend.clinica.entity.Odontologo;
import com.backend.clinica.repository.impl.OdontologoDaoH2;
import com.backend.clinica.service.impl.OdontologoService;

import static org.junit.jupiter.api.Assertions.*;

class OdontologoServiceTest {

    private OdontologoService odontologoService;

    @org.junit.jupiter.api.Test
    void deberiaRegistrarUnOdontologoYObtenerElIdCorrespondiente() {
        odontologoService = new OdontologoService(new OdontologoDaoH2());
        Odontologo odontologo = new Odontologo(123456789, "Doctor", "Test");

        Odontologo odontologoRegistrado = odontologoService.registrarOdontologo(odontologo);

        assertNotNull(odontologoRegistrado.getId());
    }

    @org.junit.jupiter.api.Test
    void deberiaRetornarUnaListaDeOdontologosNoVacia() {

        odontologoService = new OdontologoService(new OdontologoDaoH2());

        Odontologo odontologo = new Odontologo(123456789, "Doctor2", "Test2");
        Odontologo odontologoRegistrado = odontologoService.registrarOdontologo(odontologo);


        assertFalse(odontologoService.listarOdontologos().isEmpty());
    }
}