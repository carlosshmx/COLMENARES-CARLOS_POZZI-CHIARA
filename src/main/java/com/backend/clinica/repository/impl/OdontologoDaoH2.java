package com.backend.clinica.repository.impl;

import com.backend.clinica.entity.Odontologo;
import com.backend.clinica.repository.IDao;
import com.backend.clinica.dbconnection.H2Connection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDaoH2 implements IDao<Odontologo> {

    private final Logger LOGGER = LogManager.getLogger(OdontologoDaoH2.class);
    @Override
    public Odontologo registrar(Odontologo odontologo) {
        Connection connection = null;
        Odontologo odontologoRegistrado = null;

        try{
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO ODONTOLOGOS(MATRICULA, NOMBRE, APELLIDO) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, odontologo.getMatricula());
            preparedStatement.setString(2, odontologo.getNombre());
            preparedStatement.setString(3, odontologo.getApellido());
            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            odontologoRegistrado = new Odontologo(odontologo.getMatricula(),odontologo.getNombre(),odontologo.getApellido());

            while (resultSet.next()){
                odontologoRegistrado.setId(resultSet.getLong("id"));
            }
            connection.commit();
            LOGGER.info("Se ha registrado un odontologo " + odontologoRegistrado);

        }catch (Exception exc){
            LOGGER.error(exc.getMessage());
            exc.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                    LOGGER.info("Tuvimos un problema al registrar el odontologo");
                    LOGGER.error(exc.getMessage());
                    exc.printStackTrace();
                } catch (SQLException exception) {
                    LOGGER.error(exception.getMessage());
                    exception.printStackTrace();
                }
            }
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                LOGGER.error("No se pudo cerrar la conexion: " + e.getMessage());
            }

        }
        return odontologoRegistrado;
    }

    @Override
    public Odontologo buscarPorId(Long id) {
        Odontologo odontologoBuscado = null;
        Connection connection = null;

        try{
            connection = H2Connection.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ODONTOLOGOS WHERE ID = ?");
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                odontologoBuscado = new Odontologo(resultSet.getLong(1), resultSet.getInt(2), resultSet.getString(3),  resultSet.getString(4));
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

        if(odontologoBuscado == null) LOGGER.error("No se ha encontrado el odontologo con id: " + id);
        else LOGGER.info("Se ha encontrado el odontologo: " + odontologoBuscado);

        return odontologoBuscado;
    }


    @Override
    public List<Odontologo> listarTodos() {
        Connection connection = null;
        List<Odontologo> odontologos = new ArrayList<>();

        try {
            connection = H2Connection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ODONTOLOGOS");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Odontologo odontologo = new Odontologo(resultSet.getLong("id"),resultSet.getInt("matricula"), resultSet.getString("nombre"), resultSet.getString("apellido"));

                odontologos.add(odontologo);
            }

        }catch (Exception e){
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (Exception ex) {
                LOGGER.error("Ha ocurrido un error al intentar cerrar la bdd. " + ex.getMessage());
                ex.printStackTrace();
            }
        }

        LOGGER.info("Listado de odontologos obtenido " + odontologos);

        return odontologos;
    }
}
