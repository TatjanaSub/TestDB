/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testdb;

import entity.GroupName;
import entity.GroupStudents;
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
    private List<GroupStudents> listGgroupStudents;
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("TestDBPU");
    private EntityManager em = emf.createEntityManager();
    private EntityTransaction tx = em.getTransaction();

    public App() {
        try {
           students = em.createQuery("SELECT s FROM Student s").getResultList();
           groupNames = em.createQuery("SELECT gn FROM GroupName gn").getResultList();
           listGgroupStudents = em.createQuery("SELECT gs FROM GroupStudents gs").getResultList();
        } catch (Exception e) {
            System.out.println("Запись в базе данных отсутствует");
            students = new ArrayList<>();
            groupNames = new ArrayList<>();
            listGgroupStudents = new ArrayList<>();
        }

    }
    
    public void run(){
        tx.begin();
        
        if(students.isEmpty()){
            Student student = new Student();
            student.setFirstname("Ivan");
            student.setLastname("Ivanov");
            student.setDay(1);
            student.setMonth(1);
            student.setYear(2000);
            em.persist(student);
            
            GroupName groupName = new GroupName();
            groupName.setGname("JKTV21");
            groupName.setYear(2021);
            em.persist(groupName);

            GroupStudents groupStudents = new GroupStudents();
            groupStudents.setStudent(student);
            groupStudents.setGroup(groupName);
            em.persist(groupStudents);
            
            student = new Student();
            student.setFirstname("Peter");
            student.setLastname("Tamme");
            student.setDay(1);
            student.setMonth(1);
            student.setYear(2000);
            em.persist(student);
            
            groupName = new GroupName();
            groupName.setGname("JKTV22");
            groupName.setYear(2022);
            em.persist(groupName);
            
            groupStudents = new GroupStudents();
            groupStudents.setStudent(student);
            groupStudents.setGroup(groupName);
            em.persist(groupStudents);
        }
        tx.commit();
        
//        List<GroupStudents> groupStudents = em.createQuery("SELECT gs FROM GroupStudents gs WHERE gs.group.gname = 'JKTV21'").getResultList();
        List<GroupStudents> groupStudents = em.createQuery("SELECT gs FROM GroupStudents gs").getResultList();
        for (int i = 0; i < groupStudents.size(); i++) {
            GroupStudents gs = groupStudents.get(i);
            System.out.printf("Student: %s %s, group: %s %n"
                    ,gs.getStudent().getFirstname()
                    ,gs.getStudent().getLastname()
                    ,gs.getGroup().getGname()
            );
        }
    }
}
