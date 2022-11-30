/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testdb;

import entity.Student;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


/**
 *
 * @author pupil
 */
class App {
    private Student student;
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("TestDBPU");
    private EntityManager em = emf.createEntityManager();
    private EntityTransaction tx = em.getTransaction();

    public App() {
        try {
            student = (Student) em.createQuery("SELECT s FROM Student s").getSingleResult();
        } catch (Exception e) {
            System.out.println("Запись в базе данных отсутствует");
            student = new Student();
        }

    }
    
    public void run(){
        tx.begin();
        if(student.getId() == null){
            student.setFirstname("Ivan");
            student.setLastname("Ivanov");
            student.setDay(1);
            student.setMonth(1);
            student.setYear(2000);
            em.persist(student);
        }else{
            student.setFirstname("Zachar");
            em.merge(student);
        }
        tx.commit();
    }
}
