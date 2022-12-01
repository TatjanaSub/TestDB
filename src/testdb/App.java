/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testdb;

import entity.GroupName;
import entity.Student;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


/**
 *
 * @author pupil
 * 
 */
class App {
    private List<Student> students;
    private List<GroupName> groupNames;
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("TestDBPU");
    private EntityManager em = emf.createEntityManager();
    private EntityTransaction tx = em.getTransaction();

    public App() {
        try {
            students = em.createQuery("SELECT s FROM Student s").getResultList();
        } catch (Exception e) {
            System.out.println("Запись в базе данных отсутствует");
            students = new ArrayList<>();
            groupNames = new ArrayList<>();
        }

    }
    
    public void run(){
        tx.begin();
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            if(student.getId() == null){
     //           groupname.setGname("JKTV21");
    //            groupname.setYear(2021);
                //em.persist(groupName);
                student.setFirstname("Ivan");
                student.setLastname("Ivanov");
                student.setDay(1);
                student.setMonth(1);
                student.setYear(2000);
                student.setGroupname(new GroupName());
                student.getGroupname().setGname("JKTV21");
                student.getGroupname().setYear(2021);
                em.persist(student);

                GroupName groupName = student.getGroupname();
                student = new Student();
                student.setFirstname("Peter");
                student.setLastname("Tamme");
                student.setDay(1);
                student.setMonth(1);
                student.setYear(2000);
                student.setGroupname(groupName);
               // student.getGroupname().setGname("JKTV22");
                //student.getGroupname().setYear(2022);
                em.persist(student);

            }else{
                student.setFirstname("Zachar");
                student.getGroupname().setGname("JKTV22");
                student.getGroupname().setYear(2022);
                em.merge(student);
            }
        }
        tx.commit();
    }
}
