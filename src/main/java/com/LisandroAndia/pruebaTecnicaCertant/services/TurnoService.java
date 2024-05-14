package com.LisandroAndia.pruebaTecnicaCertant.services;

import com.LisandroAndia.pruebaTecnicaCertant.models.*;
import com.LisandroAndia.pruebaTecnicaCertant.repositories.TurnosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class TurnoService {

    @Autowired
    private TurnosRepository turnosRepository;

    public List<Turno> listAll(String filtro) {
        if (filtro != null){
            return turnosRepository.findAll(filtro);
        }
        return turnosRepository.findAll();
    }

    public List<Turno> findByIdPaciente(int id) {
        return turnosRepository.findAllByPacienteId(id);
    }

    public Turno findById(int id){
        return turnosRepository.findById(id);
    }

    public List<Turno> findByFechaHora(LocalDate fecha, LocalTime hora, EstadoTurno estado) {
        return turnosRepository.findByFechaAndHoraInicioTurnoAndEstadoTurno(fecha, hora, estado);
    }

    public void save(Turno turno) {
        turnosRepository.save(turno);
    }
}
