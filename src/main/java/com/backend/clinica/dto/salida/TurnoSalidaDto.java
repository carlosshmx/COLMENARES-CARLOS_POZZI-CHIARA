package com.backend.clinica.dto.salida;

import com.backend.clinica.entity.Odontologo;
import com.backend.clinica.entity.Paciente;

import java.time.LocalDateTime;

public class TurnoSalidaDto {
    private Long id;
    private PacienteSalidaDto paciente;
    private OdontologoSalidaDto odontologo;
    private LocalDateTime fechaYHora;

    public TurnoSalidaDto(Long id, PacienteSalidaDto paciente, OdontologoSalidaDto odontologo, LocalDateTime fechaYHora) {
        this.id = id;
        this.paciente = paciente;
        this.odontologo = odontologo;
        this.fechaYHora = fechaYHora;
    }

    public Long getId() {
        return id;
    }

    public PacienteSalidaDto getPaciente() {
        return paciente;
    }

    public OdontologoSalidaDto getOdontologo() {
        return odontologo;
    }

    public LocalDateTime getFechaYHora() {
        return fechaYHora;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPaciente(PacienteSalidaDto paciente) {
        this.paciente = paciente;
    }

    public void setOdontologo(OdontologoSalidaDto odontologo) {
        this.odontologo = odontologo;
    }

    public void setFechaYHora(LocalDateTime fechaYHora) {
        this.fechaYHora = fechaYHora;
    }

    @Override
    public String toString() {
        return "TurnoSalidaDto{" +
                "id=" + id +
                ", paciente=" + paciente +
                ", odontologo=" + odontologo +
                ", fechaYHora=" + fechaYHora +
                '}';
    }
}
