package com.a20da10.Entity.ejb;
import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "instructors", schema = "sys", catalog = "")
public class InstructorEntity {
    private Long id;
    private int instructorId;
    private String firstName;
    private String lastName;
    private String instructorNumber;
    private String password;
    private String email;

    @Id //primary key
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @Column(name = "instructorId", nullable = false)
    public int getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(int instructorID) {
        this.instructorId = instructorId;
    }

    @Basic
    @Column(name = "firstName", nullable = true, length = 45)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "lastName", nullable = true, length = 45)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "email", nullable = true, length = 85)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "instructorNumber", nullable = true, length = 85)
    public String getInstructorNumber() {
        return instructorNumber;
    }

    public void setInstructorNumber(String instructorNumber) {
        this.instructorNumber = instructorNumber;
    }

    @Basic
    @Column(name = "password", nullable = true, length = 85)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InstructorEntity that = (InstructorEntity) o;
        return instructorId == that.instructorId && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(instructorId, firstName, lastName, instructorNumber, email, password);
    }
}
