package com.LisandroAndia.pruebaTecnicaCertant.repositories;

import com.LisandroAndia.pruebaTecnicaCertant.models.HorariosAtencion;
import com.LisandroAndia.pruebaTecnicaCertant.models.Profesional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HorariosAtencionRepository extends JpaRepository<HorariosAtencion, Integer> {

    @Query("SELECT h FROM HorariosAtencion h WHERE h.profesional.id = :profesionalId")
    List<HorariosAtencion> findAllByProfesionalId(@Param("profesionalId") int profesionalId);

    HorariosAtencion findByDiaSemanaAndProfesionalAndFranjaHoraria(String diaSemana, Profesional profesional, int franjaHoraria);
}
