package com.a20da10.Entity.spring;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "course", schema = "school")
public class CourseEntity {

    @Id
    private int courseId;
    private String name;
    private Integer instructorId;
    private CourseTypeEnum type;

    public List<StudentEntity> getStudentEntities() {
        return studentEntities;
    }

    public void setStudentEntities(List<StudentEntity> studentEntities) {
        this.studentEntities = studentEntities;
    }

    @JsonIgnoreProperties("courseEntities")
    @ManyToMany(fetch = FetchType.EAGER,
                cascade = {CascadeType.PERSIST,CascadeType.MERGE,
                CascadeType.DETACH,CascadeType.REFRESH})
    @JoinTable(
            name = "course_student",
            joinColumns = @JoinColumn(name = "courseId"),
            inverseJoinColumns = @JoinColumn(name = "studentId")
    )
    private List<StudentEntity> studentEntities;


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
        CourseEntity that = (CourseEntity) o;
        return courseId == that.courseId && Objects.equals(name, that.name) && Objects.equals(instructorId, that.instructorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, name, instructorId);
    }

    public void addStudent(StudentEntity studentEntity){
        if (studentEntities==null){
            studentEntities=new ArrayList<>();
        }
        studentEntities.add(studentEntity);
    }

    public CourseTypeEnum getType() {
        return type;
    }

    public void setType(CourseTypeEnum type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "CourseEntity{" +
                "courseId=" + courseId +
                ", name='" + name + '\'' +
                ", instructorId is=" + instructorId +
                '}';
    }
}
