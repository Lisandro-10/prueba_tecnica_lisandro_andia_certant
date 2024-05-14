package com.LisandroAndia.pruebaTecnicaCertant.services;

import com.LisandroAndia.pruebaTecnicaCertant.models.Especialidad;
import com.LisandroAndia.pruebaTecnicaCertant.repositories.EspecialidadesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EspecialidadService {

    @Autowired
    private EspecialidadesRepository especialidadesRepository;

    public List<Especialidad> listAll() {
        return especialidadesRepository.findAll();
    }
}
