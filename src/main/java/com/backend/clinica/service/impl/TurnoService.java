package com.backend.clinica.service.impl;

import com.backend.clinica.dto.entrada.PacienteEntradaDto;
import com.backend.clinica.dto.entrada.TurnoEntradaDto;
import com.backend.clinica.dto.salida.PacienteSalidaDto;
import com.backend.clinica.dto.salida.TurnoSalidaDto;
import com.backend.clinica.entity.Odontologo;
import com.backend.clinica.entity.Paciente;
import com.backend.clinica.entity.Turno;
import com.backend.clinica.repository.TurnoRepository;
import com.backend.clinica.service.ITurnoService;
import com.backend.clinica.utils.JsonPrinter;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;


@Service
public class TurnoService implements ITurnoService {

    private final Logger LOGGER = LoggerFactory.getLogger(PacienteService.class);
    private final TurnoRepository turnoRepository;
    private final OdontologoService odontologoService;
    private final PacienteService pacienteService;
    private final ModelMapper modelMapper;
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public TurnoService(TurnoRepository turnoRepository, OdontologoService odontologoService, PacienteService pacienteService, ModelMapper modelMapper, EntityManager entityManager) {
        this.turnoRepository = turnoRepository;
        this.odontologoService = odontologoService;
        this.pacienteService = pacienteService;
        this.modelMapper = modelMapper;
this.entityManager=entityManager;
        //configureMapping();
    }

    @Override
    @Transactional
    public TurnoSalidaDto registrarTurno(TurnoEntradaDto turnoEntradaDto) {

        Turno turnoRegistrado = new Turno();
        turnoRegistrado.setFechaYHora(turnoEntradaDto.getFechaYHora());
        Paciente paciente = modelMapper.map(pacienteService.buscarPorId(turnoEntradaDto.getOdontologoId()), Paciente.class);
        Odontologo odontologo = modelMapper.map(odontologoService.buscarPorId(turnoEntradaDto.getOdontologoId()), Odontologo.class);

        odontologo = entityManager.merge(odontologo);
        paciente = entityManager.merge(paciente);

        turnoRegistrado.setPaciente(paciente);
        turnoRegistrado.setOdontologo(odontologo);

        Turno turnoguardar=turnoRepository.save(turnoRegistrado);
        TurnoSalidaDto turnoSalidaDto = modelMapper.map(turnoguardar, TurnoSalidaDto.class);
        return turnoSalidaDto;
    }
    @Override
    public TurnoSalidaDto buscarPorId(Long Id){
        Turno turnoBuscado = turnoRepository.findById(Id).orElse(null);
        TurnoSalidaDto turnoEncontrado = null;

        if (turnoBuscado != null){
            turnoEncontrado = modelMapper.map(turnoBuscado, TurnoSalidaDto.class);
            LOGGER.info("Turno encontrado: {}", JsonPrinter.toString(turnoEncontrado));
        } else LOGGER.error("No se ha encontrado el turno con id {}", Id);

        return turnoEncontrado;
    }
    @Override
    public List<TurnoSalidaDto> listarTurnos() {
        List<TurnoSalidaDto> turnos = turnoRepository.findAll()
                .stream()
                .map(turno -> modelMapper.map(turno, TurnoSalidaDto.class))
                .toList();
        LOGGER.info("Listado de todos los turnos: {}", turnos);

        return turnos;
    }

    @Override
    public void eliminarTurno(Long id) {
        if(buscarPorId(id) != null){
            turnoRepository.deleteById(id);
            LOGGER.warn("Se ha eliminado el turno con id {}", id);
        }  else {

            LOGGER.error("No se encontro el turno con id {}", id);
        }


    }

    @Override
    public TurnoSalidaDto actualizarTurno(TurnoEntradaDto turnoEntradaDto, Long id) {
        Turno turnoRegistrado = turnoRepository.findById(id).orElse(null);
        if (turnoRegistrado == null) {
            return null;
        }

        turnoRegistrado.setFechaYHora(turnoEntradaDto.getFechaYHora());
        Odontologo odontologo = modelMapper.map(odontologoService.buscarPorId(turnoEntradaDto.getOdontologoId()), Odontologo.class);
        if (odontologo == null) {
            LOGGER.warn("Odontólogo no encontrado");
        }

        Paciente paciente = modelMapper.map(pacienteService.buscarPorId(turnoEntradaDto.getPacienteId()), Paciente.class);
        if (paciente == null) {
            LOGGER.warn("Paciente no encontrado");
        }
        turnoRegistrado.setOdontologo(odontologo);
        turnoRegistrado.setPaciente(paciente);
        turnoRegistrado = turnoRepository.save(turnoRegistrado);

        TurnoSalidaDto turnoSalidaDto = modelMapper.map(turnoRepository.save(turnoRegistrado), TurnoSalidaDto.class);
        return turnoSalidaDto;
    }


  /*  private void configureMapping(){
        modelMapper.typeMap(TurnoEntradaDto.class, Turno.class)
                .addMappings(mapper -> {
                    mapper.map(TurnoEntradaDto::getPacienteId, Turno::setPaciente);
                    mapper.map(TurnoEntradaDto::getOdontologoId, Turno::setOdontologo);
                });

        modelMapper.typeMap(Turno.class, TurnoSalidaDto.class)
                .addMappings(mapper -> {
                    mapper.map(Turno::getPaciente, TurnoSalidaDto::setPaciente);
                    mapper.map(Turno::getOdontologo, TurnoSalidaDto::setOdontologo);
                });
    }*/

}
