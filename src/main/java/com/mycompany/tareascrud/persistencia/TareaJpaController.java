/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tareascrud.persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.mycompany.tareascrud.logica.Alumno;
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
public class TareaJpaController implements Serializable {

    public TareaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public TareaJpaController(){
        emf = Persistence.createEntityManagerFactory("tareasCrudJPA");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tarea tarea) {
        if (tarea.getParticipantes() == null) {
            tarea.setParticipantes(new ArrayList<Alumno>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Alumno propietario = tarea.getPropietario();
            if (propietario != null) {
                propietario = em.getReference(propietario.getClass(), propietario.getId());
                tarea.setPropietario(propietario);
            }
            List<Alumno> attachedParticipantes = new ArrayList<Alumno>();
            for (Alumno participantesAlumnoToAttach : tarea.getParticipantes()) {
                participantesAlumnoToAttach = em.getReference(participantesAlumnoToAttach.getClass(), participantesAlumnoToAttach.getId());
                attachedParticipantes.add(participantesAlumnoToAttach);
            }
            tarea.setParticipantes(attachedParticipantes);
            em.persist(tarea);
            if (propietario != null) {
                propietario.getListaTareas().add(tarea);
                propietario = em.merge(propietario);
            }
            for (Alumno participantesAlumno : tarea.getParticipantes()) {
                participantesAlumno.getListaTareasParticipa().add(tarea);
                participantesAlumno = em.merge(participantesAlumno);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tarea tarea) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tarea persistentTarea = em.find(Tarea.class, tarea.getId());
            Alumno propietarioOld = persistentTarea.getPropietario();
            Alumno propietarioNew = tarea.getPropietario();
            List<Alumno> participantesOld = persistentTarea.getParticipantes();
            List<Alumno> participantesNew = tarea.getParticipantes();
            if (propietarioNew != null) {
                propietarioNew = em.getReference(propietarioNew.getClass(), propietarioNew.getId());
                tarea.setPropietario(propietarioNew);
            }
            List<Alumno> attachedParticipantesNew = new ArrayList<Alumno>();
            for (Alumno participantesNewAlumnoToAttach : participantesNew) {
                participantesNewAlumnoToAttach = em.getReference(participantesNewAlumnoToAttach.getClass(), participantesNewAlumnoToAttach.getId());
                attachedParticipantesNew.add(participantesNewAlumnoToAttach);
            }
            participantesNew = attachedParticipantesNew;
            tarea.setParticipantes(participantesNew);
            tarea = em.merge(tarea);
            if (propietarioOld != null && !propietarioOld.equals(propietarioNew)) {
                propietarioOld.getListaTareas().remove(tarea);
                propietarioOld = em.merge(propietarioOld);
            }
            if (propietarioNew != null && !propietarioNew.equals(propietarioOld)) {
                propietarioNew.getListaTareas().add(tarea);
                propietarioNew = em.merge(propietarioNew);
            }
            for (Alumno participantesOldAlumno : participantesOld) {
                if (!participantesNew.contains(participantesOldAlumno)) {
                    participantesOldAlumno.getListaTareasParticipa().remove(tarea);
                    participantesOldAlumno = em.merge(participantesOldAlumno);
                }
            }
            for (Alumno participantesNewAlumno : participantesNew) {
                if (!participantesOld.contains(participantesNewAlumno)) {
                    participantesNewAlumno.getListaTareasParticipa().add(tarea);
                    participantesNewAlumno = em.merge(participantesNewAlumno);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = tarea.getId();
                if (findTarea(id) == null) {
                    throw new NonexistentEntityException("The tarea with id " + id + " no longer exists.");
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
            Tarea tarea;
            try {
                tarea = em.getReference(Tarea.class, id);
                tarea.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tarea with id " + id + " no longer exists.", enfe);
            }
            Alumno propietario = tarea.getPropietario();
            if (propietario != null) {
                propietario.getListaTareas().remove(tarea);
                propietario = em.merge(propietario);
            }
            List<Alumno> participantes = tarea.getParticipantes();
            for (Alumno participantesAlumno : participantes) {
                participantesAlumno.getListaTareasParticipa().remove(tarea);
                participantesAlumno = em.merge(participantesAlumno);
            }
            em.remove(tarea);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tarea> findTareaEntities() {
        return findTareaEntities(true, -1, -1);
    }

    public List<Tarea> findTareaEntities(int maxResults, int firstResult) {
        return findTareaEntities(false, maxResults, firstResult);
    }

    private List<Tarea> findTareaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tarea.class));
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

    public Tarea findTarea(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tarea.class, id);
        } finally {
            em.close();
        }
    }

    public int getTareaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tarea> rt = cq.from(Tarea.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
