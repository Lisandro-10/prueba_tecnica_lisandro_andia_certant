package com.LisandroAndia.pruebaTecnicaCertant.models;

import jakarta.persistence.*;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "profesionales")
public class Profesional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String nombre;
    @Column
    private String apellido;

    @ManyToOne
    @JoinColumn(name = "especialidad_id")
    private Especialidad especialidad;

    @OneToMany(mappedBy = "profesional")
    List<HorariosAtencion> horariosAtencions = new ArrayList<HorariosAtencion>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    public List<String> getHorariosAtencions() {
        List<String> horariosString = new ArrayList<String>();
        for (HorariosAtencion horario : horariosAtencions) {
            horariosString.add(horario.toString());
        }
        return horariosString;
    }

    public void setHorariosAtencions(List<HorariosAtencion> horariosAtencions) {
        this.horariosAtencions = horariosAtencions;
    }

    public Profesional() {
    }

    public Profesional(int id, String nombre, String apellido, Especialidad especialidad, List<HorariosAtencion> horariosAtencions) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.especialidad = especialidad;
        this.horariosAtencions = horariosAtencions;
    }
}
