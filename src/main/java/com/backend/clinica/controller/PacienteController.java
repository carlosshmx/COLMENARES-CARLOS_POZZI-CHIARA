package com.backend.clinica.controller;

import com.backend.clinica.dto.entrada.PacienteEntradaDto;
import com.backend.clinica.dto.salida.PacienteSalidaDto;

import com.backend.clinica.service.IPacienteService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

    private IPacienteService pacienteService;

    public PacienteController(IPacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    //POST
    @PostMapping("/registrar")
    public PacienteSalidaDto registrarPaciente(@RequestBody @Valid PacienteEntradaDto pacienteEntradaDto){
        return pacienteService.registrarPaciente(pacienteEntradaDto);
    }

    //GET
    @GetMapping("/listar")
    public List<PacienteSalidaDto> listarPacientes(){
        return pacienteService.listarPacientes();
    }

}

