package com.a20da10.service.ejb;

import com.a20da10.Entity.ejb.EJBInstructorEntity;
import com.a20da10.Entity.spring.CourseEntity;

import javax.ejb.Local;
import java.util.List;

@Local
public interface InstructorGenServiceLocal {
    List<EJBInstructorEntity> getInstructorByName(String firsName, String lastName);
    EJBInstructorEntity getInstructorByInsId(int insId);
    List<EJBInstructorEntity> getAllInstructors();
    List<CourseEntity> getCoursesByInsId (int insId);
    CourseEntity getCourseById (int courseid);
}
