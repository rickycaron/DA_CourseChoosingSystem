package com.a20da10.dao.spring;

import com.a20da10.Entity.spring.CourseEntity;
import com.a20da10.Entity.spring.StudentEntity;

import java.util.List;

public interface StudentDao {
    public void addStudent(StudentEntity studentEntity);
    public StudentEntity getStudentEntity(Integer studentId);
    public List<StudentEntity> getAllStudents();
    public void subscribeCourse(StudentEntity studentEntity,CourseEntity courseEntity);

}
