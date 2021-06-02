package com.a20da10.service.ejb;

import com.a20da10.Entity.ejb.EJBInstructorEntity;
import com.a20da10.Entity.spring.CourseEntity;

import java.util.List;

public interface InstructorGenServiceLocal {
    public List<EJBInstructorEntity> getInstructorByName(String firsName, String lastName);
    public EJBInstructorEntity getInstructorByInsId(int insId);
    public List<EJBInstructorEntity> getAllInstructors();
    public void insertInstructor(String firstName, String lasttName, String email, String password);
    public void updateInstructor(int id, String firstName, String lasttName, String email);
    public void deleteInstructorByInsId(int insId);
    public List<CourseEntity> getCoursesByInsId (int insId);
}
