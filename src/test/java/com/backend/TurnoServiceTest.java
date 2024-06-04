package com.backend;

import com.backend.clinica.entity.Domicilio;
import com.backend.clinica.entity.Odontologo;
import com.backend.clinica.entity.Paciente;
import com.backend.clinica.entity.Turno;
import com.backend.clinica.repository.impl.DomicilioDaoH2;
import com.backend.clinica.repository.impl.OdontologoDaoH2;
import com.backend.clinica.repository.impl.PacienteDaoH2;
import com.backend.clinica.repository.impl.TurnoDaoH2;
import com.backend.clinica.service.impl.DomicilioService;
import com.backend.clinica.service.impl.OdontologoService;
import com.backend.clinica.service.impl.PacienteService;
import com.backend.clinica.service.impl.TurnoService;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TurnoServiceTest {

    private TurnoService turnoService;
    private DomicilioService domicilioService;

    @Test
    void deberiaRegistrarseUnTurnoYObtenerElIdCorrespondienteParaPacienteyOdontologoEnH2(){

        PacienteService pacienteService = new PacienteService(new PacienteDaoH2());
        domicilioService = new DomicilioService(new DomicilioDaoH2());
        Domicilio domicilio = new Domicilio("18 de julio", 2345, "Montevideo", "Montevideo");
        Paciente paciente = new Paciente("Nombre", "Apellido", 123456, LocalDate.of(2023, 05, 02), domicilio);

        Paciente pacienteRegistrado = pacienteService.registrarPaciente(paciente);

        OdontologoService odontologoService = new OdontologoService(new OdontologoDaoH2());
        Odontologo odontologo = new Odontologo(123456789, "Doctor", "Test");

        Odontologo odontologoRegistrado = odontologoService.registrarOdontologo(odontologo);

        turnoService = new TurnoService(new TurnoDaoH2());
        Turno turno = new Turno(pacienteRegistrado,odontologoRegistrado, LocalDate.of(2023, 05, 02));

        Turno turnoRegistrado = turnoService.registrarTurno(turno);

        assertNotNull(turnoRegistrado.getId());
        assertNotNull(pacienteRegistrado.getId());
        assertNotNull(odontologoRegistrado.getId());
    }


    @Test
    void deberiaRetornarseUnaListaNoVaciaDeTurnosEnH2(){

        PacienteService pacienteService = new PacienteService(new PacienteDaoH2());
        domicilioService = new DomicilioService(new DomicilioDaoH2());
        Domicilio domicilio = new Domicilio("188 de julio", 2345, "Montevideo", "Montevideo");
        Paciente pacienteRegistrado = new Paciente("Nombre3", "Apellido", 123456, LocalDate.of(2023, 05, 02), domicilio);

        OdontologoService odontologoService = new OdontologoService(new OdontologoDaoH2());
        Odontologo odontologoRegistrado = new Odontologo(123456789, "Doctor3", "Test");

        turnoService = new TurnoService(new TurnoDaoH2());
        Turno turno = new Turno(pacienteRegistrado,odontologoRegistrado, LocalDate.of(2023, 05, 02));

        Turno turnoRegistrado= turnoService.registrarTurno(turno);
        assertFalse(turnoService.listarTurnos().isEmpty());
    }

}