
package com.a20da10.service.spring;

import com.a20da10.Entity.spring.CourseEntity;
import com.a20da10.Entity.spring.StudentEntity;
import com.a20da10.Entity.spring.TextMessageEntity;

import java.util.List;

public interface StudentGeneralService {
    public StudentEntity getSingleStudent(Integer id);
    public List<StudentEntity> getAllStudent();
    public void saveStudent(StudentEntity studentEntity);
    public void updateStudent(StudentEntity studentEntity);
    public void deleteStudent(Integer id);
    public void persistTextMessge(Integer senderId,Integer receriverId,String txt);
    public List<CourseEntity> getAllCourses();
    public CourseEntity getCourseById(Integer courseId);


}