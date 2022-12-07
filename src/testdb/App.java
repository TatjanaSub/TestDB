/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testdb;

import entity.GroupName;

import entity.Student;
import java.util.ArrayList;
import java.util.Arrays;
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
        students = em.createQuery("SELECT s FROM Student s").getResultList();
        groupNames = em.createQuery("SELECT gn FROM GroupName gn").getResultList();
    }
    
    public void run(){
        
        if(students.isEmpty()){
            try {
                tx.begin();
                GroupName groupName = new GroupName();
                groupName.setGname("JKTV");
                groupName.setYear(2021);
                em.persist(groupName);

                Student student = new Student();
                student.setFirstname("Ivan");
                student.setLastname("Ivanov");
                student.setDay(1);
                student.setMonth(1);
                student.setYear(2000);
                student.setGrup(groupName);
                em.persist(student);

                groupName.getStudents().add(student);
                em.merge(groupName);

                student = new Student();
                student.setFirstname("Simon");
                student.setLastname("Simonov");
                student.setDay(10);
                student.setMonth(10);
                student.setYear(2010);
                student.setGrup(groupName);
                em.persist(student);

                groupName.getStudents().add(student);
                em.merge(groupName);

                student = new Student();
                student.setFirstname("Sergei");
                student.setLastname("Petunin");
                student.setDay(10);
                student.setMonth(9);
                student.setYear(1999);
                student.setGrup(groupName);
                em.persist(student);

                groupName.getStudents().add(student);
                em.merge(groupName);

                //====================
                groupName = new GroupName();
                groupName.setGname("JKTV");
                groupName.setYear(2022);
                em.persist(groupName);

                student = new Student();
                student.setFirstname("Peter");
                student.setLastname("Tamme");
                student.setDay(1);
                student.setMonth(1);
                student.setYear(2001);
                student.setGrup(groupName);
                em.persist(student);

                groupName.getStudents().add(student);
                em.merge(groupName);

                tx.commit();
            } catch (Exception e) {
                System.out.println("Добавить студента не удалось (повторение фамилии)");
            }
        }
        System.out.println("Students:");
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            System.out.printf("%d. %s %s, %d. Group: %s%n"
                    ,i+1
                    ,student.getFirstname()
                    ,student.getLastname()
                    ,student.getYear()
                    ,student.getGrup()
            );
        }
        System.out.println("Groups:");
           for (int i = 0; i < groupNames.size(); i++) {
               GroupName group = groupNames.get(i);
               System.out.printf("%d. %s-%d. Students: %s%n"
                       ,i+1
                       ,group.getGname()
                       ,group.getYear()
                       ,Arrays.toString(group.getStudents().toArray())
            );
        }  
        
    }
}
