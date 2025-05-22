package com.mycompany.tareascrud;
import com.mycompany.tareascrud.logica.Alumno;
import com.mycompany.tareascrud.logica.Controladora;
import com.mycompany.tareascrud.logica.Tarea;
import java.util.ArrayList;
import java.util.Date;

public class TareasCrud {

    public static void main(String[] args) {
        
        Controladora control = new Controladora();
        
        ArrayList<Tarea> listaTareas = new ArrayList();
        Alumno alu = new Alumno(6, "Miguel", "Sanchez", new Date(), listaTareas);
        control.crearAlumno(alu);
        
        Tarea tarea1 = new Tarea(3, "Proyecto final", "Realizar proyecto final", new Date(), alu);
        Tarea tarea2 = new Tarea(4, "Tarea 1", "Realizar tarea 1", new Date(), alu);
        
        control.crearTarea(tarea1);
        control.crearTarea(tarea2);
            
        listaTareas.add(tarea1);
        listaTareas.add(tarea2);
        
        alu.setListaTareas(listaTareas);
        control.editarAlumno(alu);
        
        
        
        
        /*alu.setNombre("Rick");
        
        control.editarAlumno(alu);
        
        Alumno alu = control.traerAlumno(3);
        
        System.out.println("Alumno: " + alu.toString());
        
        ArrayList<Alumno> listaAlumno = control.traerListaAlumno();
        
        for (Alumno alumno : listaAlumno){
            System.out.println("Alumno: " + alumno);
        }*/
        
    }
}