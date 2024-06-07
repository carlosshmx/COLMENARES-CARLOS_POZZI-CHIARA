package com.backend.clinica.service.impl;

import com.backend.clinica.dto.entrada.PacienteEntradaDto;
import com.backend.clinica.dto.salida.PacienteSalidaDto;
import com.backend.clinica.entity.Paciente;
import com.backend.clinica.repository.IDao;
import com.backend.clinica.repository.PacienteRepository;
import com.backend.clinica.service.IPacienteService;
import com.backend.clinica.utils.JsonPrinter;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService implements IPacienteService {
    private final Logger LOGGER = LoggerFactory.getLogger(PacienteService.class);
    private final PacienteRepository pacienteRepository;
    private final ModelMapper modelMapper;

    public PacienteService(PacienteRepository pacienteRepository, ModelMapper modelMapper) {
        this.pacienteRepository = pacienteRepository;
        this.modelMapper = modelMapper;
        configureMapping();
    }

    @Override
    public PacienteSalidaDto registrarPaciente(PacienteEntradaDto pacienteEntradaDto) {
        //logica de negocio
        //mapeo de dto a entidad
        LOGGER.info("PacienteEntradaDto: " + pacienteEntradaDto);
        Paciente paciente = modelMapper.map(pacienteEntradaDto, Paciente.class);
        LOGGER.info("PacienteEntidad: " + paciente);
        //Paciente pacienteRegistrado = pacienteIDao.registrar(paciente);
        //mapeo de entidad a dto
        PacienteSalidaDto pacienteSalidaDto = modelMapper.map(pacienteRepository.save(paciente), PacienteSalidaDto.class);
        LOGGER.info("PacienteSalidaDto: " + pacienteSalidaDto);
        return pacienteSalidaDto;
    }

    @Override
    public PacienteSalidaDto buscarPorId(Long Id){
        Paciente pacienteBuscado = pacienteRepository.findById(Id).orElse(null);
        PacienteSalidaDto pacienteEncontrado = null;

        if (pacienteBuscado != null){
            pacienteEncontrado = modelMapper.map(pacienteBuscado, PacienteSalidaDto.class);
            LOGGER.info("Paciente encontrado: {}", JsonPrinter.toString(pacienteEncontrado));
        } else LOGGER.error("No se ha encontrado el paciente con id {}", Id);

        return pacienteEncontrado;
    }

    @Override
    public List<PacienteSalidaDto> listarPacientes() {
        //mapeo de lista de entidades a lista de dtos
        List<PacienteSalidaDto> pacientes = pacienteRepository.findAll()
                .stream()
                .map(paciente -> modelMapper.map(paciente, PacienteSalidaDto.class))
                .toList();
        LOGGER.info("Listado de todos los pacientes: {}", pacientes);

        return pacientes;
    }

    @Override
    public void eliminarPaciente(Long id) {
        if(buscarPorId(id) != null){
            pacienteRepository.deleteById(id);
            LOGGER.warn("Se ha eliminado el paciente con id {}", id);
        } // else {
        //lanzar excepcion

        //}


    }

    @Override
    public PacienteSalidaDto actualizarPaciente(PacienteEntradaDto pacienteEntradaDto, Long id) {

        Paciente pacienteRecibido = modelMapper.map(pacienteEntradaDto, Paciente.class);
        Paciente pacienteAActualizar = pacienteRepository.findById(id).orElse(null);
        PacienteSalidaDto pacienteSalidaDto = null;

        if(pacienteAActualizar != null){

            pacienteRecibido.setId(pacienteAActualizar.getId());
            pacienteRecibido.getDomicilio().setId(pacienteAActualizar.getDomicilio().getId());
            pacienteAActualizar = pacienteRecibido;

            //pacienteAActualizar.setNombre(pacienteRecibido.getNombre());
            //pacienteAActualizar.setApellido(pacienteRecibido.getApellido());
            //pacienteAActualizar.setDni(pacienteRecibido.getDni());
            //pacienteAActualizar.setFechaIngreso(pacienteRecibido.getFechaIngreso());
            //pacienteAActualizar.getDomicilio().setNumero(pacienteRecibido.getDomicilio().getNumero());
            //pacienteAActualizar.getDomicilio().setLocalidad(pacienteRecibido.getDomicilio().getLocalidad());
            //pacienteAActualizar.getDomicilio().setProvincia(pacienteRecibido.getDomicilio().getProvincia());

            pacienteRepository.save(pacienteAActualizar);
            pacienteSalidaDto = modelMapper.map(pacienteAActualizar, PacienteSalidaDto.class);
            LOGGER.warn("Paciente actualizado: {}", JsonPrinter.toString(pacienteSalidaDto));

        } else {
            LOGGER.error("No fue posible actualizar el paciente porque no se encuentra en nuestra base de datos");
            //lanzar excepcion
        }

        return pacienteSalidaDto;
    }



    private void configureMapping(){
        modelMapper.typeMap(PacienteEntradaDto.class, Paciente.class)
                .addMappings(mapper -> mapper.map(PacienteEntradaDto::getDomicilioEntradaDto, Paciente::setDomicilio));
        modelMapper.typeMap(Paciente.class, PacienteSalidaDto.class)
                .addMappings(mapper -> mapper.map(Paciente::getDomicilio, PacienteSalidaDto::setDomicilioSalidaDto));
    }
}
