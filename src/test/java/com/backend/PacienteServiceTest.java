package com.backend;

import com.backend.clinica.entity.Domicilio;
import com.backend.clinica.entity.Paciente;
import com.backend.clinica.repository.impl.DomicilioDaoH2;
import com.backend.clinica.repository.impl.PacienteDaoH2;
import com.backend.clinica.service.impl.DomicilioService;
import com.backend.clinica.service.impl.PacienteService;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PacienteServiceTest {

    private PacienteService pacienteService;
    private DomicilioService domicilioService;

    @Test
    void deberiaRegistrarseUnPacienteYObtenerElIdCorrespondiente(){

        pacienteService = new PacienteService(new PacienteDaoH2());
        domicilioService = new DomicilioService(new DomicilioDaoH2());

        Domicilio domicilio = new Domicilio("18 de julio", 2345, "Montevideo", "Montevideo");
        Paciente paciente = new Paciente("Nombre", "Apellido", 123456, LocalDate.of(2023, 05, 02), domicilio);

        Paciente pacienteRegistrado = pacienteService.registrarPaciente(paciente);

        assertNotNull(pacienteRegistrado.getId());
        assertNotNull(pacienteRegistrado.getDomicilio());
    }


    @Test
    void deberiaRetornarseUnaListaNoVaciaDePacientesEnH2(){
        pacienteService = new PacienteService(new PacienteDaoH2());
        domicilioService = new DomicilioService(new DomicilioDaoH2());
        Domicilio domicilio = new Domicilio("18 de julio", 2345, "Montevideo", "Montevideo");
        Paciente paciente = new Paciente("Nombre", "Apellido", 123456, LocalDate.of(2023, 05, 02), domicilio);

        Paciente pacienteRegistrado = pacienteService.registrarPaciente(paciente);
        assertFalse(pacienteService.listarPacientes().isEmpty());
    }

}