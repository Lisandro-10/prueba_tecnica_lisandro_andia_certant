package com.LisandroAndia.pruebaTecnicaCertant.services;

import com.LisandroAndia.pruebaTecnicaCertant.models.EstadoTurno;
import com.LisandroAndia.pruebaTecnicaCertant.repositories.EstadoTurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstadoTurnoService {

    @Autowired
    private EstadoTurnoRepository estadoTurnoRepository;

    public EstadoTurno findOne(String nombreEstado){
        return estadoTurnoRepository.findByNombreEstadoTurno(nombreEstado);
    }

}
