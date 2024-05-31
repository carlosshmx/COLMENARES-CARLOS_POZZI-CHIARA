package com.backend.clinica.service.impl;

import com.backend.clinica.entity.Domicilio;
import com.backend.clinica.repository.IDao;
import com.backend.clinica.service.IDomicilioService;

import java.util.List;

public class DomicilioService implements IDomicilioService {
    private IDao<Domicilio> domicilioIDao;

    public DomicilioService(IDao<Domicilio> domicilioIDao){this.domicilioIDao = domicilioIDao;}

    @Override
    public  Domicilio registrarDomicilio(Domicilio domicilio){return domicilioIDao.registrar(domicilio);}

    @Override
    public Domicilio buscarPorId(Long id) {return domicilioIDao.buscarPorId(id);}

    @Override
    public List<Domicilio> listarDomicilios(){return  domicilioIDao.listarTodos();}
}
