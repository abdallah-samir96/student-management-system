package com.bank.boubyan.repository.impl;

import com.bank.boubyan.model.Student;
import com.bank.boubyan.repository.StudentDao;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

@RequestScoped
public class StudentDaoImpl implements StudentDao {

    private EntityManagerFactory emf;
    private EntityManager em;

    @PostConstruct
    public void init() {
        emf = Persistence.createEntityManagerFactory("student-management-system");
        em = emf.createEntityManager();
    }

    @Override
    public Student findByEmail(String email) {
        TypedQuery<Student> query =
                em.createQuery("SELECT s FROM Student s WHERE s.email = :email", Student.class);
        query.setParameter("email", email);
        return query.getSingleResult();
    }
}
