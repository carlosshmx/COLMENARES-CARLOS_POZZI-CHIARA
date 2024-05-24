package com.backend.clinica.repository.impl;

import com.backend.clinica.entity.Odontologo;
import com.backend.clinica.repository.IDao;

import java.util.ArrayList;
import java.util.List;
public class OdontologoDaoArrayList implements IDao<Odontologo> {
    List<Odontologo> Odontologos = new ArrayList<>();

    public OdontologoDaoArrayList() {
        Odontologo odontologo1 = new Odontologo(51095995 ,"Chiara", "Pozzi" );
        Odontologo odontologo2 = new Odontologo(51096006 ,"Carlos", "Colmenares" );
        Odontologo odontologo3 = new Odontologo(43233345 ,"Diego", "Perez" );
        Odontologo odontologo4 = new Odontologo(97959494 ,"Juana", "Fulana" );

        Odontologos.add(odontologo1);
        Odontologos.add(odontologo2);
        Odontologos.add(odontologo3);
        Odontologos.add(odontologo4);
    }

    @Override
    public Odontologo registrar(Odontologo odontologo) {
        Long id = Long.valueOf (Odontologos.size()+1);
        Odontologo odontologoRegistrado = new Odontologo(id, odontologo.getMatricula(), odontologo.getNombre(), odontologo.getApellido());
        return odontologoRegistrado;
    }

    @Override
    public List<Odontologo> listarTodos() {
        return Odontologos;
    }
}
