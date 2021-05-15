package com.a20da10.Entity.ejb;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

public class CourseEntity {
    private Long id;
    private int courseId;
    private String courseName;
    private String instructorId;



    @Id //primary key
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @Column(name = "courseId", nullable = false)
    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }


    @Basic
    @Column(name = "courseName", nullable = true, length = 85)
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Basic
    @Column(name = "instructorId", nullable = true, length = 85)
    public String getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(String instructorId) {
        this.instructorId = instructorId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseEntity that = (CourseEntity) o;
        return courseId == that.courseId && Objects.equals(courseName, that.courseName) && Objects.equals(instructorId, that.instructorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, courseName, instructorId);
    }
}

