package com.a20da10.dao.ejb;

import com.a20da10.Entity.ejb.EJBInstructorEntity;
import com.a20da10.Entity.spring.CourseEntity;

import java.util.List;

public interface InstructorDao {
    void create(EJBInstructorEntity entity);
    void updateIns(EJBInstructorEntity instructorEntity);
    List<EJBInstructorEntity> getByName(String firsName, String lastName);
    EJBInstructorEntity getById(int id);
    EJBInstructorEntity getByEmail(String email);
    List<EJBInstructorEntity> getAll();
    void deleteById(int id);
    List<CourseEntity> findCoursesByInsId(int insId);
    CourseEntity findCoursesByCourseId(int courseId);
    void updateCourseInfo(CourseEntity courseEntity);
    void addNewCourse(CourseEntity courseEntity);
    void deleteCourse(int courseId);
    void initEntityManager();

}
