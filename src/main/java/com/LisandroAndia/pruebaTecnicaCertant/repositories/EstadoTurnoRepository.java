package com.LisandroAndia.pruebaTecnicaCertant.repositories;

import com.LisandroAndia.pruebaTecnicaCertant.models.EstadoTurno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoTurnoRepository extends JpaRepository<EstadoTurno, Integer> {

    EstadoTurno findByNombreEstadoTurno(String nombreEstadoTurno);
}
