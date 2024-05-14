package com.LisandroAndia.pruebaTecnicaCertant.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "consultorios")
public class Consultorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private int numeroConsultorio;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumeroConsultorio() {
        return numeroConsultorio;
    }

    public void setNumeroConsultorio(int numeroConsultorio) {
        this.numeroConsultorio = numeroConsultorio;
    }

    public Consultorio() {
    }

    public Consultorio(int id, int numeroConsultorio) {
        this.id = id;
        this.numeroConsultorio = numeroConsultorio;
    }
}
