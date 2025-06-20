/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tareascrud.persistencia;

import com.mycompany.tareascrud.logica.Alumno;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.mycompany.tareascrud.logica.Tarea;
import com.mycompany.tareascrud.persistencia.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author marth
 */
public class AlumnoJpaController implements Serializable {

    public AlumnoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public AlumnoJpaController(){
        emf = Persistence.createEntityManagerFactory("tareasCrudJPA");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Alumno alumno) {
        if (alumno.getListaTareas() == null) {
            alumno.setListaTareas(new ArrayList<Tarea>());
        }
        if (alumno.getListaTareasParticipa() == null) {
            alumno.setListaTareasParticipa(new ArrayList<Tarea>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Tarea> attachedListaTareas = new ArrayList<Tarea>();
            for (Tarea listaTareasTareaToAttach : alumno.getListaTareas()) {
                listaTareasTareaToAttach = em.getReference(listaTareasTareaToAttach.getClass(), listaTareasTareaToAttach.getId());
                attachedListaTareas.add(listaTareasTareaToAttach);
            }
            alumno.setListaTareas(attachedListaTareas);
            List<Tarea> attachedListaTareasParticipa = new ArrayList<Tarea>();
            for (Tarea listaTareasParticipaTareaToAttach : alumno.getListaTareasParticipa()) {
                listaTareasParticipaTareaToAttach = em.getReference(listaTareasParticipaTareaToAttach.getClass(), listaTareasParticipaTareaToAttach.getId());
                attachedListaTareasParticipa.add(listaTareasParticipaTareaToAttach);
            }
            alumno.setListaTareasParticipa(attachedListaTareasParticipa);
            em.persist(alumno);
            for (Tarea listaTareasTarea : alumno.getListaTareas()) {
                Alumno oldPropietarioOfListaTareasTarea = listaTareasTarea.getPropietario();
                listaTareasTarea.setPropietario(alumno);
                listaTareasTarea = em.merge(listaTareasTarea);
                if (oldPropietarioOfListaTareasTarea != null) {
                    oldPropietarioOfListaTareasTarea.getListaTareas().remove(listaTareasTarea);
                    oldPropietarioOfListaTareasTarea = em.merge(oldPropietarioOfListaTareasTarea);
                }
            }
            for (Tarea listaTareasParticipaTarea : alumno.getListaTareasParticipa()) {
                Alumno oldPropietarioOfListaTareasParticipaTarea = listaTareasParticipaTarea.getPropietario();
                listaTareasParticipaTarea.setPropietario(alumno);
                listaTareasParticipaTarea = em.merge(listaTareasParticipaTarea);
                if (oldPropietarioOfListaTareasParticipaTarea != null) {
                    oldPropietarioOfListaTareasParticipaTarea.getListaTareasParticipa().remove(listaTareasParticipaTarea);
                    oldPropietarioOfListaTareasParticipaTarea = em.merge(oldPropietarioOfListaTareasParticipaTarea);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Alumno alumno) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Alumno persistentAlumno = em.find(Alumno.class, alumno.getId());
            List<Tarea> listaTareasOld = persistentAlumno.getListaTareas();
            List<Tarea> listaTareasNew = alumno.getListaTareas();
            List<Tarea> listaTareasParticipaOld = persistentAlumno.getListaTareasParticipa();
            List<Tarea> listaTareasParticipaNew = alumno.getListaTareasParticipa();
            List<Tarea> attachedListaTareasNew = new ArrayList<Tarea>();
            for (Tarea listaTareasNewTareaToAttach : listaTareasNew) {
                listaTareasNewTareaToAttach = em.getReference(listaTareasNewTareaToAttach.getClass(), listaTareasNewTareaToAttach.getId());
                attachedListaTareasNew.add(listaTareasNewTareaToAttach);
            }
            listaTareasNew = attachedListaTareasNew;
            alumno.setListaTareas(listaTareasNew);
            List<Tarea> attachedListaTareasParticipaNew = new ArrayList<Tarea>();
            for (Tarea listaTareasParticipaNewTareaToAttach : listaTareasParticipaNew) {
                listaTareasParticipaNewTareaToAttach = em.getReference(listaTareasParticipaNewTareaToAttach.getClass(), listaTareasParticipaNewTareaToAttach.getId());
                attachedListaTareasParticipaNew.add(listaTareasParticipaNewTareaToAttach);
            }
            listaTareasParticipaNew = attachedListaTareasParticipaNew;
            alumno.setListaTareasParticipa(listaTareasParticipaNew);
            alumno = em.merge(alumno);
            for (Tarea listaTareasOldTarea : listaTareasOld) {
                if (!listaTareasNew.contains(listaTareasOldTarea)) {
                    listaTareasOldTarea.setPropietario(null);
                    listaTareasOldTarea = em.merge(listaTareasOldTarea);
                }
            }
            for (Tarea listaTareasNewTarea : listaTareasNew) {
                if (!listaTareasOld.contains(listaTareasNewTarea)) {
                    Alumno oldPropietarioOfListaTareasNewTarea = listaTareasNewTarea.getPropietario();
                    listaTareasNewTarea.setPropietario(alumno);
                    listaTareasNewTarea = em.merge(listaTareasNewTarea);
                    if (oldPropietarioOfListaTareasNewTarea != null && !oldPropietarioOfListaTareasNewTarea.equals(alumno)) {
                        oldPropietarioOfListaTareasNewTarea.getListaTareas().remove(listaTareasNewTarea);
                        oldPropietarioOfListaTareasNewTarea = em.merge(oldPropietarioOfListaTareasNewTarea);
                    }
                }
            }
            for (Tarea listaTareasParticipaOldTarea : listaTareasParticipaOld) {
                if (!listaTareasParticipaNew.contains(listaTareasParticipaOldTarea)) {
                    listaTareasParticipaOldTarea.setPropietario(null);
                    listaTareasParticipaOldTarea = em.merge(listaTareasParticipaOldTarea);
                }
            }
            for (Tarea listaTareasParticipaNewTarea : listaTareasParticipaNew) {
                if (!listaTareasParticipaOld.contains(listaTareasParticipaNewTarea)) {
                    Alumno oldPropietarioOfListaTareasParticipaNewTarea = listaTareasParticipaNewTarea.getPropietario();
                    listaTareasParticipaNewTarea.setPropietario(alumno);
                    listaTareasParticipaNewTarea = em.merge(listaTareasParticipaNewTarea);
                    if (oldPropietarioOfListaTareasParticipaNewTarea != null && !oldPropietarioOfListaTareasParticipaNewTarea.equals(alumno)) {
                        oldPropietarioOfListaTareasParticipaNewTarea.getListaTareasParticipa().remove(listaTareasParticipaNewTarea);
                        oldPropietarioOfListaTareasParticipaNewTarea = em.merge(oldPropietarioOfListaTareasParticipaNewTarea);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = alumno.getId();
                if (findAlumno(id) == null) {
                    throw new NonexistentEntityException("The alumno with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Alumno alumno;
            try {
                alumno = em.getReference(Alumno.class, id);
                alumno.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The alumno with id " + id + " no longer exists.", enfe);
            }
            List<Tarea> listaTareas = alumno.getListaTareas();
            for (Tarea listaTareasTarea : listaTareas) {
                listaTareasTarea.setPropietario(null);
                listaTareasTarea = em.merge(listaTareasTarea);
            }
            List<Tarea> listaTareasParticipa = alumno.getListaTareasParticipa();
            for (Tarea listaTareasParticipaTarea : listaTareasParticipa) {
                listaTareasParticipaTarea.setPropietario(null);
                listaTareasParticipaTarea = em.merge(listaTareasParticipaTarea);
            }
            em.remove(alumno);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Alumno> findAlumnoEntities() {
        return findAlumnoEntities(true, -1, -1);
    }

    public List<Alumno> findAlumnoEntities(int maxResults, int firstResult) {
        return findAlumnoEntities(false, maxResults, firstResult);
    }

    private List<Alumno> findAlumnoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Alumno.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Alumno findAlumno(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Alumno.class, id);
        } finally {
            em.close();
        }
    }

    public int getAlumnoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Alumno> rt = cq.from(Alumno.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
