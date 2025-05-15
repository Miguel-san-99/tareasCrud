package com.mycompany.tareascrud.logica;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Tarea implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Basic
    private String nombre;
    private String descripcion;
    @Temporal(TemporalType.DATE)
    private Date fechaEntrega;
    
    @ManyToOne
    private Alumno propietario;

    public Tarea() {
    }

    public Tarea(int id, String nombre, String descripcion, Date fechaEntrega) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaEntrega = fechaEntrega;
    }

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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    @Override
    public String toString() {
        return "Tarea{" + "id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", fechaEntrega=" + fechaEntrega + '}';
    }
    
}
