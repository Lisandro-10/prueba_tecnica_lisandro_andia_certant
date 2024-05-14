package com.LisandroAndia.pruebaTecnicaCertant.repositories;

import com.LisandroAndia.pruebaTecnicaCertant.models.Profesional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfesionalesRepository extends JpaRepository<Profesional, Integer> {

    Profesional findById(int id);
}
