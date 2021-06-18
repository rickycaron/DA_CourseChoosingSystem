package com.a20da10.Entity.ejb;

import com.a20da10.Entity.spring.CourseEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "instructor", schema = "school")
public class EJBInstructorEntity implements Serializable {
    public EJBInstructorEntity(String firstName, String lasttName, String email, String password, String instructorNumber) {
        this.firstName = firstName;
        this.lastName = lasttName;
        this.email = email;
        this.password = password;
        this.instructorNumber = instructorNumber;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int instructorId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String instructorNumber;
    @OneToMany(targetEntity = CourseEntity.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "instructorId", referencedColumnName = "instructorId")
    private List<CourseEntity> courses;

    public EJBInstructorEntity() {

    }
    @Column(name = "instructorId", nullable = false)
    public int getInstructorId() {
        return instructorId;
    }
    public void setInstructorId(int insId){this.instructorId = insId;}

    @Basic
    @Column(name = "firstName", nullable = false, length = 45)
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String name) {
        this.firstName = name;
    }

    @Basic
    @Column(name = "lastName", nullable = false, length = 45)
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "email", nullable = false, length = 95)
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "instructorNumber", nullable = false, length = 45)
    public String getInstructorNumber (){return instructorNumber;}
    public void setInstructorNumber(String insNumber){this.instructorNumber = insNumber;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EJBInstructorEntity that = (EJBInstructorEntity) o;
        return Objects.equals(instructorId, that.instructorId) && Objects.equals(firstName, that.firstName)
                && Objects.equals(lastName, that.lastName)
                && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(instructorId, firstName, lastName, email);
    }

    @Basic
    @Column(name = "password", nullable = false, length = 95)
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

}
