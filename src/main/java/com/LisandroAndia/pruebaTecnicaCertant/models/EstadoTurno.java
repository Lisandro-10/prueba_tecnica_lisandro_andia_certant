package com.LisandroAndia.pruebaTecnicaCertant.models;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "EstadoTurno")
public class EstadoTurno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String nombreEstadoTurno;

    @OneToMany(mappedBy = "estadoTurno")
    private List<Turno> turnos = new ArrayList<Turno>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreEstadoTurno() {
        return nombreEstadoTurno;
    }

    public void setNombreEstadoTurno(String nombreEstadoTurno) {
        this.nombreEstadoTurno = nombreEstadoTurno;
    }

    public EstadoTurno(int id, String nombreEstadoTurno, List<Turno> turnos) {
        this.id = id;
        this.nombreEstadoTurno = nombreEstadoTurno;
        this.turnos = turnos;
    }

    public EstadoTurno() {
    }
}
