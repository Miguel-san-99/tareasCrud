package com.mycompany.tareascrud;
import com.mycompany.tareascrud.logica.Alumno;
import com.mycompany.tareascrud.logica.Controladora;
import java.util.ArrayList;
import java.util.Date;

public class TareasCrud {

    public static void main(String[] args) {
        Controladora control = new Controladora();
        
        /*Alumno alu = new Alumno(3, "Miguel", "Sanchez", new Date());
        
        alu.setNombre("Rick");
        
        control.editarAlumno(alu);*/
        
        Alumno alu = control.traerAlumno(1);
        
        System.out.println("Alumno: " + alu.toString());
        
        ArrayList<Alumno> listaAlumno = control.traerListaAlumno();
        
        for (Alumno alumno : listaAlumno){
            System.out.println("Alumno: " + alumno);
        }
    }
}