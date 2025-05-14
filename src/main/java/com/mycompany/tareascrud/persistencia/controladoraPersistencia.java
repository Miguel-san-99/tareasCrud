package com.mycompany.tareascrud.persistencia;
import com.mycompany.tareascrud.logica.Alumno;
import com.mycompany.tareascrud.logica.Tarea;
import com.mycompany.tareascrud.persistencia.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class controladoraPersistencia {
    AlumnoJpaController aluJpa = new AlumnoJpaController();
    TareaJpaController tareaJpa = new TareaJpaController();

    public void crearAlumno(Alumno alumno) {
        aluJpa.create(alumno);
    }

    public void eliminarAlumno(int id) {
        try {
            aluJpa.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(controladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void editarAlumno(Alumno alumno){
        try {
            aluJpa.edit(alumno);
        } catch (Exception ex) {
            Logger.getLogger(controladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Alumno traerAlumno(int id) {
        return aluJpa.findAlumno(id);
    }
    
    public ArrayList<Alumno> traerListaAlumno() {
        
        List<Alumno> lista = aluJpa.findAlumnoEntities();
        
        ArrayList<Alumno> listaAlumno = new ArrayList<Alumno>(lista);
        
        return listaAlumno;
    }
    
    public void crearTarea(Tarea tarea) {
         tareaJpa.create(tarea);
    }

    public void eliminarTarea(int id) {
        try {
            tareaJpa.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(controladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void editarTarea(Tarea tarea){
        try {
            tareaJpa.edit(tarea);
        } catch (Exception ex) {
            Logger.getLogger(controladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Tarea traerTarea(int id) {
        return tareaJpa.findTarea(id);
    }

    public ArrayList<Tarea> traerListaTarea() {
        
        List<Tarea> lista = tareaJpa.findTareaEntities();
        
        ArrayList<Tarea> listaTarea = new ArrayList<Tarea>(lista);
        
        return listaTarea;
    }


}