package com.backend.clinica.service.impl;

import com.backend.clinica.dto.entrada.DomicilioEntradaDto;
import com.backend.clinica.dto.entrada.PacienteEntradaDto;
import com.backend.clinica.dto.salida.PacienteSalidaDto;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;


import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

class PacienteServiceTest {

    @Autowired
    private PacienteService pacienteService;


    @Test
    @Order(1)
    void deberiaRegistrarseUnPaciente_yRetornarSuDomicilio(){

        PacienteEntradaDto pacienteEntradaDto = new PacienteEntradaDto("Carlos", "Perez", 123456, LocalDate.of(2024, 6, 22), new DomicilioEntradaDto("Calle", 123, "Localidad", "Provincia"));

        PacienteSalidaDto pacienteSalidaDto = pacienteService.registrarPaciente(pacienteEntradaDto);

        assertNotNull(pacienteSalidaDto);
        assertNotNull(pacienteSalidaDto.getId());
        assertEquals("Carlos", pacienteSalidaDto.getNombre());
        assertEquals("Calle", pacienteSalidaDto.getDomicilio().getCalle());
        assertEquals(123, pacienteSalidaDto.getDomicilio().getNumero());
        assertEquals("Localidad", pacienteSalidaDto.getDomicilio().getLocalidad());
        assertEquals("Provincia", pacienteSalidaDto.getDomicilio().getProvincia());
    }


    @Test
    @Order(2)
    void deberiaDevolverUnaListaNoVaciaDePacientes(){
        List<PacienteSalidaDto> listadoDePacientes = pacienteService.listarPacientes();
        assertFalse(listadoDePacientes.isEmpty());
    }

    @Test
    @Order(3)
    void deberiaDevolverUnPacienteBuscandoPorSuId(){
        PacienteSalidaDto pacienteSalidaDto = pacienteService.buscarPorId(1L);
        assertNotNull(pacienteSalidaDto);
        assertNotNull(pacienteSalidaDto.getId());
    }


    @Test
    @Order(4)
    void deberiaActualizarUnPacienteYDevolverSusDatos() {
        PacienteEntradaDto pacienteEntradaDto = new PacienteEntradaDto("Chiara", "Pozzi", 51095995, LocalDate.of(2024, 6, 22), new DomicilioEntradaDto("Frutillitas", 123, "Localidad", "Provincia"));

        PacienteSalidaDto pacienteSalidaDto = assertDoesNotThrow(() -> pacienteService.actualizarPaciente(pacienteEntradaDto, 1L));
        assertEquals("Chiara", pacienteSalidaDto.getNombre());
        assertEquals("Frutillitas", pacienteSalidaDto.getDomicilio().getCalle());
    }

    @Test
    @Order(5)
    void deberiaEliminarseElPacienteConId1(){
        assertDoesNotThrow(() -> pacienteService.eliminarPaciente(1L));
    }

}