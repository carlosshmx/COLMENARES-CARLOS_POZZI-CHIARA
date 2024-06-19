package com.backend.clinica.service.impl;

import com.backend.clinica.dto.entrada.DomicilioEntradaDto;
import com.backend.clinica.dto.entrada.OdontologoEntradaDto;
import com.backend.clinica.dto.entrada.PacienteEntradaDto;
import com.backend.clinica.dto.entrada.TurnoEntradaDto;
import com.backend.clinica.dto.salida.OdontologoSalidaDto;
import com.backend.clinica.dto.salida.PacienteSalidaDto;
import com.backend.clinica.dto.salida.TurnoSalidaDto;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

class TurnoServiceTest {

    @Autowired
    private TurnoService turnoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;

    Long segundoOdontologoId;
    Long odontologoId;
    Long pacienteId;

    @BeforeAll
    void setup() {
        PacienteEntradaDto pacienteEntradaDto = new PacienteEntradaDto("Carlos", "Perez", 123456, LocalDate.of(2024, 6, 22), new DomicilioEntradaDto("Calle", 123, "Localidad", "Provincia"));
        PacienteSalidaDto pacienteSalidaDto = pacienteService.registrarPaciente(pacienteEntradaDto);
        pacienteId= pacienteSalidaDto.getId();

        OdontologoEntradaDto odontologoEntradaDto = new OdontologoEntradaDto( 123456, "Carlos", "Perez");
        OdontologoSalidaDto odontologoSalidaDto = odontologoService.registrarOdontologo(odontologoEntradaDto);
        odontologoId= odontologoSalidaDto.getId();

        OdontologoEntradaDto segundoOdontologoEntradaDto = new OdontologoEntradaDto( 123456, "Carolina", "Chuleta");
        OdontologoSalidaDto segundoOdontologoSalidaDto = odontologoService.registrarOdontologo(segundoOdontologoEntradaDto);
        segundoOdontologoId= segundoOdontologoSalidaDto.getId();
    }

    @Test
    @Order(1)
    void deberiaRegistrarseUnTurno_yRetornarSuPacienteySuDomicilio(){

        TurnoEntradaDto turnoEntradaDto = new TurnoEntradaDto(pacienteId, odontologoId, LocalDateTime.of(2025, 06, 22, 12, 00));

        TurnoSalidaDto turnoSalidaDto = assertDoesNotThrow(() -> turnoService.registrarTurno(turnoEntradaDto));

        assertNotNull(turnoSalidaDto);
        assertNotNull(turnoSalidaDto.getId());
        assertNotNull(turnoSalidaDto.getOdontologo());

    }


    @Test
    @Order(2)
    void deberiaDevolverUnaListaNoVaciaDeTurnos(){
        List<TurnoSalidaDto> listadoDeTurnos = turnoService.listarTurnos();
        assertFalse(listadoDeTurnos.isEmpty());
    }

    @Test
    @Order(3)
    void deberiaDevolverUnTurnoBuscandoPorSuId(){
        TurnoSalidaDto turnoSalidaDto = turnoService.buscarPorId(1L);
        assertNotNull(turnoSalidaDto);
        assertNotNull(turnoSalidaDto.getId());
    }


    @Test
    @Order(4)
    void deberiaActualizarUnTurnoYDevolverSusDatosActualizados() {
        TurnoEntradaDto turnoEntradaDto =  new TurnoEntradaDto(pacienteId, segundoOdontologoId , LocalDateTime.of(2025, 06, 22, 12, 00));

        TurnoSalidaDto turnoSalidaDto = assertDoesNotThrow(() -> turnoService.actualizarTurno(turnoEntradaDto, 1L));
        assertEquals("Carolina", turnoSalidaDto.getOdontologo().getNombre());
        assertEquals("Chuleta", turnoSalidaDto.getOdontologo().getApellido());
    }

    @Test
    @Order(5)
    void deberiaEliminarseElTurno(){
        assertDoesNotThrow(() -> turnoService.eliminarTurno(pacienteId));
    }

}