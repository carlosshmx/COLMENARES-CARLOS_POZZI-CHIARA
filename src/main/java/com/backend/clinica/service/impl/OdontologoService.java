package com.backend.clinica.service.impl;

import com.backend.clinica.dto.entrada.OdontologoEntradaDto;
import com.backend.clinica.dto.salida.OdontologoSalidaDto;
import com.backend.clinica.entity.Odontologo;
import com.backend.clinica.repository.IDao;
import com.backend.clinica.service.IOdontologoService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OdontologoService implements IOdontologoService {
    private final Logger LOGGER = LoggerFactory.getLogger(OdontologoService.class);
    private IDao<Odontologo> odontologoIDao;
    private final ModelMapper modelMapper;

    public OdontologoService(IDao<Odontologo> odontologoIDao, ModelMapper modelMapper){
        this.odontologoIDao = odontologoIDao;
        this.modelMapper = modelMapper;
        configureMapping();
    }
    @Override
    public OdontologoSalidaDto registrarOdontologo(OdontologoEntradaDto odontologoEntradaDto) {
        LOGGER.info("OdontologoEntradaDto: "+ odontologoEntradaDto);
        Odontologo odontologo = modelMapper.map(odontologoEntradaDto, Odontologo.class);
        LOGGER.info("OdontologoEntidad: "+ odontologo);
        //Odontologo odontologoRegistrado = odontologoIDao.registrar(odontologo);
        OdontologoSalidaDto odontologoSalidaDto = modelMapper.map(odontologoIDao.registrar(odontologo), OdontologoSalidaDto.class);
        LOGGER.info("OdontologoSalidaDto: "+ odontologoSalidaDto);
        return odontologoSalidaDto;
    }
    @Override
    public Odontologo buscarPorId(Long Id){
        return odontologoIDao.buscarPorId(Id);
    }

    @Override
    public List<OdontologoSalidaDto> listarOdontologos() {
        List<OdontologoSalidaDto> odontologos = odontologoIDao.listarTodos()
                .stream()
                .map(odontologo -> modelMapper.map(odontologo, OdontologoSalidaDto.class))
                .toList();
        LOGGER.info("Listado de todos los odontologos: {}", odontologos);

        return odontologos;
    }

    private void configureMapping(){
        modelMapper.typeMap(OdontologoEntradaDto.class, Odontologo.class)
                .addMappings(mapper -> {mapper.map(OdontologoEntradaDto::getNombre, Odontologo::setNombre);
                                        mapper.map(OdontologoEntradaDto::getApellido, Odontologo::setApellido);
                                        mapper.map(OdontologoEntradaDto::getMatricula, Odontologo::setMatricula);});
        modelMapper.typeMap(Odontologo.class, OdontologoSalidaDto.class)
                .addMappings(mapper -> {
                    mapper.map(Odontologo::getNombre, OdontologoSalidaDto::setNombre);
                    mapper.map(Odontologo::getApellido, OdontologoSalidaDto::setApellido);
                    mapper.map(Odontologo::getMatricula, OdontologoSalidaDto::setMatricula);});
    }
}

