package com.a20da10.Entity.ejb;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "course", schema = "school", catalog = "")
public class EJBCourseEntity {
    @Id
    private int courseId;
    private String name;
    private Integer instructorId;


    @Column(name = "courseId", nullable = false)
    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 95)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "instructorId", nullable = true)
    public Integer getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(Integer instructorId) {
        this.instructorId = instructorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EJBCourseEntity that = (EJBCourseEntity) o;
        return courseId == that.courseId && Objects.equals(name, that.name) && Objects.equals(instructorId, that.instructorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, name, instructorId);
    }
}
