/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

/**
 *
 * @author pupil
 */
@Entity
public class GroupName {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String gname;
    private int year;
    @OneToMany
    @JoinTable(
            name="group_students",
            joinColumns = @JoinColumn(name = "student_fk"),
            inverseJoinColumns = @JoinColumn(name = "group_fk") 
    )
    private List<Student> students = new ArrayList<>();

    public GroupName() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "GroupName{" 
                + "id=" + id 
                + ", gname=" + gname 
                + ", year=" + year 
                + '}';
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

   
    
}
