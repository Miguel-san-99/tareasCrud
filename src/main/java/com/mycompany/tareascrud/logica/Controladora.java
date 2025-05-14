package com.mycompany.tareascrud.logica;
import com.mycompany.tareascrud.persistencia.controladoraPersistencia;

public class Controladora {
    controladoraPersistencia controlPersis = new controladoraPersistencia();
    
    public void crearAlumno(Alumno alumno){
        controlPersis.crearAlumno(alumno);
    }
    
    public void eliminarAlumno(int id){
        controlPersis.eliminarAlumno(id);
    }
    
    public void editarAlumno(Alumno alumno){
        controlPersis.editarAlumno(alumno);
    }
    
    public Alumno traerAlumno(int id){
        return controlPersis.traerAlumno(id);
    }
    
    public void crearTarea(Tarea tarea){
        controlPersis.crearTarea(tarea);
    }
    
    public void eliminarTarea(int id){
        controlPersis.eliminarTarea(id);
    }
    
    public void editarTarea(Tarea tarea){
        controlPersis.editarTarea(tarea);
    }
    
}
