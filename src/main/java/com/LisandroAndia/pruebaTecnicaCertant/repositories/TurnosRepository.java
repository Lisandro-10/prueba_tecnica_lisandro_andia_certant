package com.LisandroAndia.pruebaTecnicaCertant.repositories;

import com.LisandroAndia.pruebaTecnicaCertant.models.EstadoTurno;
import com.LisandroAndia.pruebaTecnicaCertant.models.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface TurnosRepository extends JpaRepository<Turno, Integer> {

    @Query(value = "SELECT * FROM turnos t WHERE t.paciente_id = :id", nativeQuery = true)
    List<Turno> findAllByPacienteId(int id);

    @Query("select t from Turno t where" +
            " concat( t.nombreProfesional, t.especialidad)" +
            " like %?1%")
    List<Turno> findAll(String filtro);

    List<Turno> findByFechaAndHoraInicioTurnoAndEstadoTurno(LocalDate fecha, LocalTime horaInicioTurno, EstadoTurno estadoTurno);

    Turno findById(int id);

}
