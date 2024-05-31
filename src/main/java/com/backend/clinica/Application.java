package com.backend.clinica;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.backend.clinica.dbconnection.H2Connection.crearTablas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class Application {

    private static final Logger LOGGER = LogManager.getLogger(Application.class);
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        crearTablas();

    }
}
