package com.backend;

import com.backend.clinica.entity.Odontologo;
import com.backend.clinica.entity.Paciente;
import com.backend.clinica.entity.Turno;
import com.backend.clinica.repository.impl.OdontologoDaoH2;
import com.backend.clinica.repository.impl.PacienteDaoH2;
import com.backend.clinica.repository.impl.TurnoDaoH2;
import com.backend.clinica.service.impl.OdontologoService;
import com.backend.clinica.service.impl.PacienteService;
import com.backend.clinica.service.impl.TurnoService;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TurnoServiceTest {

    private TurnoService turnoService;

    @Test
    void deberiaRegistrarseUnTurnoYObtenerElIdCorrespondienteParaPacienteyOdontologoEnH2(){

        PacienteService pacienteService = new PacienteService(new PacienteDaoH2());
        Paciente paciente = new Paciente("Nombre", "Apellido", 123456, LocalDate.of(2023, 05, 02));

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
        Paciente pacienteRegistrado = new Paciente("Nombre", "Apellido", 123456, LocalDate.of(2023, 05, 02));

        OdontologoService odontologoService = new OdontologoService(new OdontologoDaoH2());
        Odontologo odontologoRegistrado = new Odontologo(123456789, "Doctor", "Test");

        turnoService = new TurnoService(new TurnoDaoH2());
        Turno turno = new Turno(pacienteRegistrado,odontologoRegistrado, LocalDate.of(2023, 05, 02));

        Turno turnoRegistrado = turnoService.registrarTurno(turno);
        assertFalse(turnoService.listarTurnos().isEmpty());
    }

}