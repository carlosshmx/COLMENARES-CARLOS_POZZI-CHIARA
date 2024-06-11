package com.backend.clinica.service.impl;

import com.backend.clinica.dto.entrada.OdontologoEntradaDto;
import com.backend.clinica.dto.salida.OdontologoSalidaDto;
import com.backend.clinica.entity.Odontologo;
import com.backend.clinica.repository.IDao;
import com.backend.clinica.repository.OdontologoRepository;
import com.backend.clinica.service.IOdontologoService;
import com.backend.clinica.utils.JsonPrinter;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OdontologoService implements IOdontologoService {
    private final Logger LOGGER = LoggerFactory.getLogger(OdontologoService.class);
    private final OdontologoRepository odontologoRepository;
    private final ModelMapper modelMapper;

    public OdontologoService(OdontologoRepository odontologoRepository, ModelMapper modelMapper){
        this.odontologoRepository = odontologoRepository;
        this.modelMapper = modelMapper;
        configureMapping();
    }
    @Override
    public OdontologoSalidaDto registrarOdontologo(OdontologoEntradaDto odontologoEntradaDto) {
        LOGGER.info("OdontologoEntradaDto: "+ odontologoEntradaDto);
        Odontologo odontologo = modelMapper.map(odontologoEntradaDto, Odontologo.class);
        LOGGER.info("OdontologoEntidad: "+ odontologo);
        //Odontologo odontologoRegistrado = odontologoIDao.registrar(odontologo);
        OdontologoSalidaDto odontologoSalidaDto = modelMapper.map(odontologoRepository.save(odontologo), OdontologoSalidaDto.class);
        LOGGER.info("OdontologoSalidaDto: "+ odontologoSalidaDto);
        return odontologoSalidaDto;
    }
    @Override
    public OdontologoSalidaDto buscarPorId(Long id){
        Odontologo odontologoBuscado = odontologoRepository.findById(id).orElse(null);
        OdontologoSalidaDto odontologoEncontrado = null;

        if (odontologoBuscado != null){
            odontologoEncontrado = modelMapper.map(odontologoBuscado, OdontologoSalidaDto.class);
            LOGGER.info("Paciente encontrado: {}", JsonPrinter.toString(odontologoEncontrado));
        } else LOGGER.error("No se ha encontrado el odontologo con id {}", id);

        return odontologoEncontrado;
    }

    @Override
    public List<OdontologoSalidaDto> listarOdontologos() {
        List<OdontologoSalidaDto> odontologos = odontologoRepository.findAll()
                .stream()
                .map(odontologo -> modelMapper.map(odontologo, OdontologoSalidaDto.class))
                .toList();
        LOGGER.info("Listado de todos los odontologos: {}", odontologos);

        return odontologos;
    }

    public OdontologoSalidaDto actualizarOdontologo(OdontologoEntradaDto odontologoEntradaDto, Long id) {

        Odontologo odontologoRecibido = modelMapper.map(odontologoEntradaDto, Odontologo.class);
        Odontologo odontologoToUpdate = odontologoRepository.findById(id).orElse(null);
        OdontologoSalidaDto odontologoSalidaDto = null;

        if(odontologoToUpdate != null){

            odontologoRecibido.setId(odontologoToUpdate.getId());
            odontologoToUpdate = odontologoRecibido;


            odontologoRepository.save(odontologoToUpdate);
            odontologoSalidaDto = modelMapper.map(odontologoToUpdate, OdontologoSalidaDto.class);
            LOGGER.warn("Odontologo actualizado: {}", JsonPrinter.toString(odontologoSalidaDto));

        } else {
            LOGGER.error("No fue posible actualizar el odontologo porque no se encuentra en nuestra base de datos");
            //lanzar excepcion
        }

        return odontologoSalidaDto;
    }

    @Override
    public void eliminarOdontologo(Long id) {
        if(buscarPorId(id) != null){
            odontologoRepository.deleteById(id);
            LOGGER.warn("Se ha eliminado el odontologo con id {}", id);
        }  else {
        LOGGER.error("No se pudo eliminar el odontologo. No se ha encontrado el odontologo con id {}", id);

        }


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

