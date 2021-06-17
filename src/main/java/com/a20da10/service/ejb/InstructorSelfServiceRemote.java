package com.a20da10.service.ejb;

import com.a20da10.Entity.ejb.EJBInstructorEntity;
import com.a20da10.Entity.spring.CourseEntity;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface InstructorSelfServiceRemote {
    public EJBInstructorEntity getMyInfo ();
    public List<CourseEntity> getCoursesOfMine ();
    public void insertInstructor(String firstName, String lasttName, String email, String password, String instructorNumber);
    public void updateInstructor(EJBInstructorEntity instructorEntity);
    public void deleteInstructorByInsId(int insId);
    public void addNewCourse(CourseEntity courseEntity);
    public void deleteCourse(int courseId);
    public void updateCourseInfo(CourseEntity courseEntity);
    public void setInsId(int id);
    public int getInsId();
}
