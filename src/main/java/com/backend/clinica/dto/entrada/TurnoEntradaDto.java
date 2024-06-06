package com.backend.clinica.dto.entrada;


import com.backend.clinica.entity.Odontologo;
import com.backend.clinica.entity.Paciente;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class TurnoEntradaDto {
    @NotBlank(message = "Debe especificarse un paciente")
    private Paciente paciente;
    @NotBlank(message = "Debe especificarse un odontologo")
    private Odontologo odontologo;
    @NotNull(message = "Debe especificarse la fecha Y hora de turno")
    private LocalDateTime fechaYHora;
    @Valid
    private OdontologoEntradaDto odontologoEntradaDto;
    private PacienteEntradaDto pacienteEntradaDto;
    public TurnoEntradaDto() {
    }

    public TurnoEntradaDto(PacienteEntradaDto pacienteEntradaDto, OdontologoEntradaDto odontologoEntradaDto, LocalDateTime fechaYHora) {
        this.pacienteEntradaDto = pacienteEntradaDto;
        this.odontologoEntradaDto = odontologoEntradaDto;
        this.fechaYHora = fechaYHora;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public Odontologo getOdontologo() {
        return odontologo;
    }

    public LocalDateTime getFechaYHora() {
        return fechaYHora;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public void setOdontologo(Odontologo odontologo) {
        this.odontologo = odontologo;
    }

    public void setFechaYHora(LocalDateTime fechaYHora) {
        this.fechaYHora = fechaYHora;
    }

    @Override
    public String toString() {
        return "TurnoEntradaDto{" +
                "paciente=" + paciente +
                ", odontologo=" + odontologo +
                ", fechaYHora=" + fechaYHora +
                '}';
    }
}
