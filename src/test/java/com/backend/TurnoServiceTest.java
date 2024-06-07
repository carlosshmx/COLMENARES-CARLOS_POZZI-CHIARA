package com.backend;

import com.backend.clinica.dto.entrada.DomicilioEntradaDto;
import com.backend.clinica.dto.entrada.OdontologoEntradaDto;
import com.backend.clinica.dto.entrada.PacienteEntradaDto;
import com.backend.clinica.dto.entrada.TurnoEntradaDto;
import com.backend.clinica.dto.salida.OdontologoSalidaDto;
import com.backend.clinica.dto.salida.PacienteSalidaDto;
import com.backend.clinica.dto.salida.TurnoSalidaDto;
import com.backend.clinica.entity.Domicilio;
import com.backend.clinica.entity.Odontologo;
import com.backend.clinica.entity.Paciente;
import com.backend.clinica.repository.impl.DomicilioDaoH2;
import com.backend.clinica.repository.impl.OdontologoDaoH2;
import com.backend.clinica.repository.impl.PacienteDaoH2;
import com.backend.clinica.repository.impl.TurnoDaoH2;
import com.backend.clinica.service.impl.DomicilioService;
import com.backend.clinica.service.impl.OdontologoService;
import com.backend.clinica.service.impl.PacienteService;
import com.backend.clinica.service.impl.TurnoService;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

class TurnoServiceTest {
    private final Logger LOGGER = LoggerFactory.getLogger(PacienteService.class);

    private TurnoService turnoService;
    private DomicilioService domicilioService;
    private PacienteService pacienteService;
    private OdontologoService odontologoService;
    /*
    @Test
    void deberiaRegistrarseUnTurnoYObtenerElIdCorrespondienteParaPacienteyOdontologoEnH2(){

        pacienteService = new PacienteService(new PacienteDaoH2(), new ModelMapper());
        domicilioService = new DomicilioService(new DomicilioDaoH2());
        DomicilioEntradaDto domicilio = new DomicilioEntradaDto("18 de julio", 2345, "Montevideo", "Montevideo");
        PacienteEntradaDto pacienteEntradaDto = new PacienteEntradaDto("Nombre", "Apellido", 123456, LocalDate.of(2023, 5, 2), domicilio);

        PacienteSalidaDto pacienteRegistrado = pacienteService.registrarPaciente(pacienteEntradaDto);

        odontologoService = new OdontologoService(new OdontologoDaoH2(), new ModelMapper());
        OdontologoEntradaDto odontologoEntradaDto = new OdontologoEntradaDto(123456789, "Doctor", "Test");

        OdontologoSalidaDto odontologoRegistrado = odontologoService.registrarOdontologo(odontologoEntradaDto);

        LOGGER.info("Aki ta el dontologo" + String.valueOf(odontologoRegistrado));
        LOGGER.info("Aki ta el paciente" + String.valueOf(pacienteRegistrado));


        turnoService = new TurnoService(new TurnoDaoH2(), new ModelMapper());
        TurnoEntradaDto turno = new TurnoEntradaDto(pacienteRegistrado,odontologoRegistrado, LocalDateTime.of(2023, Month.JULY, 2,12,12,12));

        TurnoSalidaDto turnoRegistrado = turnoService.registrarTurno(turno);

        assertNotNull(turnoRegistrado.getId());
        assertNotNull(pacienteRegistrado.getId());
        assertNotNull(odontologoRegistrado.getId());
    }


    @Test
    void deberiaRetornarseUnaListaNoVaciaDeTurnosEnH2(){

        pacienteService = new PacienteService(new PacienteDaoH2(), new ModelMapper());
        domicilioService = new DomicilioService(new DomicilioDaoH2());
        DomicilioEntradaDto domicilio = new DomicilioEntradaDto("18 de julio", 2345, "Montevideo", "Montevideo");
        PacienteEntradaDto pacienteEntradaDto = new PacienteEntradaDto("Nombre", "Apellido", 123456, LocalDate.of(2023, 5, 2), domicilio);

        PacienteSalidaDto pacienteRegistrado = pacienteService.registrarPaciente(pacienteEntradaDto);

        odontologoService = new OdontologoService(new OdontologoDaoH2(), new ModelMapper());
        OdontologoEntradaDto odontologoEntradaDto = new OdontologoEntradaDto(123456789, "Doctor", "Test");

        OdontologoSalidaDto odontologoRegistrado = odontologoService.registrarOdontologo(odontologoEntradaDto);

        turnoService = new TurnoService(new TurnoDaoH2(), new ModelMapper());
        TurnoEntradaDto turno = new TurnoEntradaDto(pacienteRegistrado,odontologoRegistrado, LocalDateTime.of(2023, Month.JULY, 2,12,12,12));

        TurnoSalidaDto turnoRegistrado = turnoService.registrarTurno(turno);

        assertFalse(turnoService.listarTurnos().isEmpty());
    }
*/
}