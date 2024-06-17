package com.backend;

import com.backend.clinica.service.impl.PacienteService;

class PacienteServiceTest {

    private PacienteService pacienteService;
  //  private DomicilioService domicilioService;
/*
    @Test
    void deberiaRegistrarseUnPacienteYObtenerElIdCorrespondiente(){

        pacienteService = new PacienteService(new PacienteDaoH2(), new ModelMapper());
        domicilioService = new DomicilioService(new DomicilioDaoH2());

        DomicilioEntradaDto domicilio = new DomicilioEntradaDto("18 de julio", 2345, "Montevideo", "Montevideo");
        PacienteEntradaDto pacienteEntradaDto = new PacienteEntradaDto("Nombre", "Apellido", 123456, LocalDate.of(2023, 05, 02), domicilio);

        PacienteSalidaDto pacienteRegistrado = pacienteService.registrarPaciente(pacienteEntradaDto);

        assertNotNull(pacienteRegistrado.getId());
        assertNotNull(pacienteRegistrado.getDomicilioSalidaDto());
    }



    @Test
    void deberiaRetornarseUnaListaNoVaciaDePacientesEnH2(){
        pacienteService = new PacienteService(new PacienteDaoH2(), new ModelMapper());
        domicilioService = new DomicilioService(new DomicilioDaoH2());
        DomicilioEntradaDto domicilio = new DomicilioEntradaDto("18 de julio", 2345, "Montevideo", "Montevideo");
        PacienteEntradaDto pacienteEntradaDto = new PacienteEntradaDto("Nombre", "Apellido", 123456, LocalDate.of(2023, 05, 02), domicilio);

        PacienteSalidaDto pacienteRegistrado = pacienteService.registrarPaciente(pacienteEntradaDto);
        assertFalse(pacienteService.listarPacientes().isEmpty());
    }
*/
}