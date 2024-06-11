package com.backend.clinica.repository;

import com.backend.clinica.entity.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OdontologoRepository extends JpaRepository<Odontologo, Long> {
    //@Query("SELECT p FROM Paciente p WHERE p.dni = ?1") HQL
    //@Query(value = "SELECT * FROM PACIENTES WHERE DNI = ?1", nativeQuery = true) SQL
    Odontologo findByMatricula(int matricula);
    Odontologo findByMatriculaAndNombre(int matricula, String nombre);

}