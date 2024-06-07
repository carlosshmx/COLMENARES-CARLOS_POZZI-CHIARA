package com.backend.clinica.repository.impl;

import com.backend.clinica.dbconnection.H2Connection;
import com.backend.clinica.entity.Domicilio;
import com.backend.clinica.repository.impl.DomicilioDaoH2;
import com.backend.clinica.entity.Paciente;
import com.backend.clinica.repository.IDao;
import com.backend.clinica.service.impl.DomicilioService;
import com.backend.clinica.service.impl.OdontologoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Component
public class PacienteDaoH2 implements IDao<Paciente> {
    private final Logger LOGGER = LogManager.getLogger(PacienteDaoH2.class);

    private DomicilioDaoH2 domicilioDaoH2;
    private DomicilioService domicilioService;

    @Override
    public Paciente registrar(Paciente paciente) {
        Connection connection = null;
        Paciente pacienteRegistrado = null;

        try{
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);

            domicilioDaoH2 = new DomicilioDaoH2();
            Domicilio nuevoDomicilio = domicilioDaoH2.registrar(paciente.getDomicilio());

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO PACIENTES (NOMBRE, APELLIDO, DNI, FECHA, DOMICILIO_ID) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, paciente.getNombre());
            preparedStatement.setString(2, paciente.getApellido());
            preparedStatement.setInt(3, paciente.getDni());
            preparedStatement.setDate(4, Date.valueOf(paciente.getFechaIngreso()));
            preparedStatement.setLong(5, nuevoDomicilio.getId());
            preparedStatement.execute();

            pacienteRegistrado = new Paciente(paciente.getNombre(), paciente.getApellido(), paciente.getDni(), paciente.getFechaIngreso(), nuevoDomicilio);


            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while(resultSet.next()) {
                pacienteRegistrado.setId(resultSet.getLong("id"));
            }

            connection.commit();

            LOGGER.info("Se ha registrado el paciente: " + pacienteRegistrado);



        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                    LOGGER.info("Tuvimos un problema");
                    LOGGER.error(e.getMessage());
                    e.printStackTrace();
                } catch (SQLException exception) {
                    LOGGER.error(exception.getMessage());
                    exception.printStackTrace();
                }
            }
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                LOGGER.error("No se pudo cerrar la conexion: " + ex.getMessage());
            }
        }


        return pacienteRegistrado;
    }

    @Override
    public Paciente buscarPorId(Long id) {
        Paciente pacienteBuscado = null;
        Connection connection = null;

        try{
            connection = H2Connection.getConnection();


            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM PACIENTES WHERE ID = ?");
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Long domicilioId= resultSet.getLong("domicilio_id");
                domicilioService = new DomicilioService(new DomicilioDaoH2());
                Domicilio domicilio = domicilioService.buscarPorId(domicilioId);
                pacienteBuscado = new Paciente(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3),  resultSet.getInt(4),  resultSet.getDate(5).toLocalDate(), domicilio);
            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                LOGGER.error("Ha ocurrido un error al intentar cerrar la bdd. " + ex.getMessage());
                ex.printStackTrace();
            }
        }

        if(pacienteBuscado == null) LOGGER.error("No se ha encontrado el paciente con id: " + id);
        else LOGGER.info("Se ha encontrado el odontologo: " + pacienteBuscado);

        return pacienteBuscado;
    }

    @Override
    public List<Paciente> listarTodos() {

        Connection connection = null;
        List<Paciente> pacientes = new ArrayList<>();
        try{
            connection = H2Connection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM PACIENTES");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){

                Paciente paciente = crearObjetoPaciente(resultSet);
                pacientes.add(paciente);
            }


        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();

        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                LOGGER.error("Ha ocurrido un error al intentar cerrar la bdd. " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        LOGGER.info("Listado de todos los pacientes: " + pacientes);

        return pacientes;
    }


    private Paciente crearObjetoPaciente(ResultSet resultSet) throws SQLException {

        Domicilio domicilio = new DomicilioDaoH2().buscarPorId(resultSet.getLong("DOMICILIO_ID"));

        return new Paciente(resultSet.getLong("id"), resultSet.getString("nombre"), resultSet.getString("apellido"), resultSet.getInt("dni"), resultSet.getDate("fecha").toLocalDate(), domicilio);
    }


}
