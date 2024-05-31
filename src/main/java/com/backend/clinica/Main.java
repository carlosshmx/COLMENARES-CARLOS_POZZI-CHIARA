package com.backend.clinica;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.backend.clinica.repository.dbconnection.H2Connection.crearTablas;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class Main {

    private static final Logger LOGGER = LogManager.getLogger(Main.class);
    public static void main(String[] args) {
        crearTablas();

    }
}
