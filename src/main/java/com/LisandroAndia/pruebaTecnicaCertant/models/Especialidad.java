package com.LisandroAndia.pruebaTecnicaCertant.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "especialidades")
public class Especialidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String nombreEspecialidad;

    @OneToMany(mappedBy = "especialidad", cascade = CascadeType.ALL)
    private List<Profesional> profesionales = new ArrayList<Profesional>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreEspecialidad() {
        return nombreEspecialidad;
    }

    public void setNombreEspecialidad(String nombreEspecialidad) {
        this.nombreEspecialidad = nombreEspecialidad;
    }

    public List<Profesional> getProfesionales() {
        return profesionales;
    }

    public void setProfesionales(List<Profesional> profesionales) {
        this.profesionales = profesionales;
    }

    public Especialidad(int id, String nombreEspecialidad, List<Profesional> profesionales, List<Turno> turnos) {
        this.id = id;
        this.nombreEspecialidad = nombreEspecialidad;
        this.profesionales = profesionales;
    }

    public Especialidad() {
    }
}
