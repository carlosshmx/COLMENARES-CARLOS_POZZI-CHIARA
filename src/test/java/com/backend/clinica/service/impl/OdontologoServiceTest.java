package com.backend.clinica.service.impl;

import com.backend.clinica.dto.entrada.OdontologoEntradaDto;
import com.backend.clinica.dto.salida.OdontologoSalidaDto;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

class OdontologoServiceTest {

    @Autowired
    private OdontologoService odontologoService;


    @Test
    @Order(1)
    void deberiaRegistrarseUnOdontologo_yRetornarSuId(){

        OdontologoEntradaDto odontologoEntradaDto = new OdontologoEntradaDto( 123456, "Carlos", "Perez");

        OdontologoSalidaDto odontologoSalidaDto = odontologoService.registrarOdontologo(odontologoEntradaDto);

        assertNotNull(odontologoSalidaDto);
        assertNotNull(odontologoSalidaDto.getId());
    }


    @Test
    @Order(2)
    void deberiaDevolverUnaListaNoVaciaDeOdontologos(){
        List<OdontologoSalidaDto> listadoDeOdontologos = odontologoService.listarOdontologos();
        assertFalse(listadoDeOdontologos.isEmpty());

    }

    @Test
    @Order(3)
    void deberiaDevolverUnOdontologoBuscandoPorSuId(){
        OdontologoSalidaDto odontologoSalidaDto = odontologoService.buscarPorId(1L);
        assertNotNull(odontologoSalidaDto);
        assertNotNull(odontologoSalidaDto.getId());
    }

    @Test
    @Order(4)
    void deberiaActualizarUnOdontologoYDevolverSusDatos() {
        OdontologoEntradaDto odontologoEntradaDto = new OdontologoEntradaDto(51095995 , "Chiara", "Pozzi");

        OdontologoSalidaDto odontologoSalidaDto = assertDoesNotThrow(() -> odontologoService.actualizarOdontologo(odontologoEntradaDto, 1L));
        assertEquals("Chiara", odontologoSalidaDto.getNombre());
        assertEquals("Pozzi", odontologoSalidaDto.getApellido());
    }

    @Test
    @Order(5)
    void deberiaEliminarseElOdontologoConId1(){
        assertDoesNotThrow(() -> odontologoService.eliminarOdontologo(1L));
    }

}