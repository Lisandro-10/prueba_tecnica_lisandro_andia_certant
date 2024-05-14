package com.LisandroAndia.pruebaTecnicaCertant.services;

import com.LisandroAndia.pruebaTecnicaCertant.models.Paciente;
import com.LisandroAndia.pruebaTecnicaCertant.repositories.PacientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService {

    @Autowired
    private PacientesRepository pacientesRepository;

    public List<Paciente> listAll(){
        return pacientesRepository.findAll();
    }

    public void save(Paciente paciente){
        pacientesRepository.save(paciente);
    }
}
