package com.LisandroAndia.pruebaTecnicaCertant.services;

import com.LisandroAndia.pruebaTecnicaCertant.models.Profesional;
import com.LisandroAndia.pruebaTecnicaCertant.repositories.ProfesionalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfesionalService {

    @Autowired
    private ProfesionalesRepository profesionalesRepository;

    public List<Profesional> listAll(){
        return profesionalesRepository.findAll();
    }

    public Profesional findOneById(int id){
        return profesionalesRepository.findById(id);
    }

    public Profesional findOneByName(int id){
        return profesionalesRepository.findById(id);
    }

}
