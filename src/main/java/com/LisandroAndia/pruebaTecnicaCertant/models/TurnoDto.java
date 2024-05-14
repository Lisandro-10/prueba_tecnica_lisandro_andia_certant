package com.LisandroAndia.pruebaTecnicaCertant.models;


import java.time.LocalDate;
import java.time.LocalTime;

public class TurnoDto {

    private LocalDate fechaTurno;
    private LocalTime horaInicioTurno;
    private Profesional profesional;
    private String nombreProfesional;
    private String nombrePaciente;
    private Paciente paciente;



    public String getNombreProfesional() {
        return nombreProfesional;
    }

    public void setNombreProfesional(String nombreProfesional) {
        this.nombreProfesional = nombreProfesional;
    }

    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public LocalDate getFechaTurno() {
        return fechaTurno;
    }

    public void setFechaTurno(LocalDate fechaTurno) {
        this.fechaTurno = fechaTurno;
    }

    public Profesional getProfesional() {
        return profesional;
    }

    public void setProfesional(Profesional profesional) {
        this.profesional = profesional;
    }

    public LocalTime getHoraInicioTurno() {
        return horaInicioTurno;
    }

    public void setHoraInicioTurno(LocalTime horaInicioTurno) {
        this.horaInicioTurno = horaInicioTurno;
    }
}
