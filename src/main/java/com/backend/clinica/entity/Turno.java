package com.backend.clinica.entity;
import lombok.Data;

import java.time.LocalDateTime;
import javax.persistence.*;

@Data
@Entity
@Table(name = "TURNOS")
public class Turno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "odontologo_id")
    private Odontologo odontologo;
    @Column (length = 20)
    private LocalDateTime fechaYHora;

    public Turno(Long id, Paciente paciente, Odontologo odontologo, LocalDateTime fechaYHora) {
        this.id = id;
        this.paciente = paciente;
        this.odontologo = odontologo;
        this.fechaYHora = fechaYHora;
    }

    public Turno(Paciente paciente, Odontologo odontologo, LocalDateTime fechaYHora) {
        this.paciente = paciente;
        this.odontologo = odontologo;
        this.fechaYHora = fechaYHora;
    }
    public Turno() {
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Odontologo getOdontologo() {
        return odontologo;
    }

    public void setOdontologo(Odontologo odontologo) {
        this.odontologo = odontologo;
    }

    public LocalDateTime getFechaYHora() {
        return fechaYHora;
    }

    public void setFechaYHora(LocalDateTime fechayHora) {
        this.fechaYHora = fechayHora;
    }

    @Override
    public String toString() {
        return "Id: " + id + " - Paciente: " + paciente + " - Odontologo: " + odontologo + " - Fecha: " + fechaYHora;
    }

}


