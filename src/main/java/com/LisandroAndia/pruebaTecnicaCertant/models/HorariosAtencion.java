package com.LisandroAndia.pruebaTecnicaCertant.models;

import jakarta.persistence.*;

@Entity
@Table(name = "horario_atencion")
public class HorariosAtencion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private int franjaHoraria;

    @Column
    private String diaSemana;

    @ManyToOne
    @JoinColumn(name = "profesional_id")
    private Profesional profesional;

    @ManyToOne
    @JoinColumn(name = "consultorio_id")
    private Consultorio consultorio;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    public int getFranjaHoraria() {
        return franjaHoraria;
    }

    public void setFranjaHoraria(int franjaHoraria) {
        this.franjaHoraria = franjaHoraria;
    }

    public Profesional getProfesional() {
        return profesional;
    }

    public void setProfesional(Profesional profesional) {
        this.profesional = profesional;
    }

    public Consultorio getConsultorio() {
        return consultorio;
    }

    public void setConsultorio(Consultorio consultorio) {
        this.consultorio = consultorio;
    }

    public HorariosAtencion() {
    }

    public HorariosAtencion(int id, int franjaHoraria, String diaSemana, Profesional profesional, Consultorio consultorio) {
        this.id = id;
        this.franjaHoraria = franjaHoraria;
        this.diaSemana = diaSemana;
        this.profesional = profesional;
        this.consultorio = consultorio;
    }

    @Override
    public String toString() {
        return diaSemana + " a las " + franjaHoraria;
    }

}
