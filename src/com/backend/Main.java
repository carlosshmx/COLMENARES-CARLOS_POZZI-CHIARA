package com.backend;


import org.apache.log4j.Logger;

import java.sql.*;

import static com.backend.repository.dbconnection.H2Connection.crearTablas;


public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class);
    public static void main(String[] args) {
        crearTablas();

    }
}
