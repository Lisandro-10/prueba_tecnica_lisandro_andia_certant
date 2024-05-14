package com.LisandroAndia.pruebaTecnicaCertant.services;

import com.LisandroAndia.pruebaTecnicaCertant.models.HorariosAtencion;
import com.LisandroAndia.pruebaTecnicaCertant.models.Profesional;
import com.LisandroAndia.pruebaTecnicaCertant.repositories.HorariosAtencionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HorariosAtencionService {

    @Autowired
    private HorariosAtencionRepository horariosAtencionRepository;

    public List<HorariosAtencion> listAll(int profesional_id) {
        if (profesional_id != 0){
            return horariosAtencionRepository.findAllByProfesionalId(profesional_id);
        }
        return horariosAtencionRepository.findAll();
    }

    public HorariosAtencion findOne(Profesional profesional, String diaSemana, int franjaHoraria){
        return horariosAtencionRepository.findByDiaSemanaAndProfesionalAndFranjaHoraria(diaSemana, profesional, franjaHoraria);
    }
}
