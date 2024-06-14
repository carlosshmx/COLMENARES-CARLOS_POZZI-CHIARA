package com.backend.clinica.dto.entrada;


import com.backend.clinica.dto.salida.OdontologoSalidaDto;
import com.backend.clinica.dto.salida.PacienteSalidaDto;
import com.backend.clinica.entity.Odontologo;
import com.backend.clinica.entity.Paciente;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class TurnoEntradaDto {
    @NotNull(message = "Debe especificarse la fecha Y hora de turno")
    private LocalDateTime fechaYHora;
    @Valid
    @NotNull(message = "Debe especificarse el Id del odontologo")
    private Long odontologoId;
    @NotNull(message = "Debe especificarse el Id del paciente")
    private Long pacienteId;
    public TurnoEntradaDto() {
    }

    public TurnoEntradaDto(Long pacienteId, Long odontologoId, LocalDateTime fechaYHora) {
        this.pacienteId = pacienteId;
        this.odontologoId = odontologoId;
        this.fechaYHora = fechaYHora;
    }

    public Long getPacienteId() {
        return pacienteId;
    }

    public Long getOdontologoId() {
        return odontologoId;
    }

    public LocalDateTime getFechaYHora() {
        return fechaYHora;
    }

    public void setPaciente(Long paciente) {
        this.pacienteId = paciente;
    }

    public void setOdontologo(Long odontologo) {
        this.odontologoId = odontologo;
    }

    public void setFechaYHora(LocalDateTime fechaYHora) {
        this.fechaYHora = fechaYHora;
    }

    @Override
    public String toString() {
        return "TurnoEntradaDto{" +
                "paciente=" + pacienteId +
                ", odontologo=" + odontologoId +
                ", fechaYHora=" + fechaYHora +
                '}';
    }
}
