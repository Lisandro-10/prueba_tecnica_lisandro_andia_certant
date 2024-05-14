package com.LisandroAndia.pruebaTecnicaCertant.repositories;

import com.LisandroAndia.pruebaTecnicaCertant.models.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacientesRepository extends JpaRepository<Paciente, Integer> {
}
