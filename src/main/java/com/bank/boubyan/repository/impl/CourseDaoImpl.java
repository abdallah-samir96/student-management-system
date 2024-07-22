package com.bank.boubyan.repository.impl;

import com.bank.boubyan.model.Course;
import com.bank.boubyan.repository.CourseDao;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

@RequestScoped
public class CourseDaoImpl implements CourseDao {

    private EntityManagerFactory emf;
    private EntityManager em;
    @PostConstruct
    public void init() {
        emf = Persistence.createEntityManagerFactory("student-management-system");
        em = emf.createEntityManager();
    }
    @Override
    public List<Course> getAll() {
        try {
            TypedQuery<Course> query = em
                    .createQuery("SELECT c FROM Course c", Course.class);
            return query.getResultList();
        } finally {
            em.close();
            emf.close();
        }
    }
}
