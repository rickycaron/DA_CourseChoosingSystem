package com.a20da10.service.spring;

import com.a20da10.Entity.spring.CourseEntity;
import com.a20da10.Entity.spring.StudentEntity;
import com.a20da10.Entity.spring.TextMessageEntity;

import java.util.List;

public interface StudentSelfService {
    public StudentEntity getBasicInfo();


    public void setStudentId(int i);

    public int getStudentId();
    public void subscribeCourse(CourseEntity courseEntity);


    public List<TextMessageEntity> getAllTextMessageById();
    public CourseEntity cancelCourse(StudentEntity studentEntity,Integer courseId);



}
