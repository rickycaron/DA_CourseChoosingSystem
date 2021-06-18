package com.a20da10.service.spring;

import com.a20da10.Entity.spring.CourseEntity;
import com.a20da10.Entity.spring.StudentEntity;
import com.a20da10.Entity.spring.TextMessageEntity;
import com.a20da10.dao.spring.CourseDao;
import com.a20da10.dao.spring.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class StudentSelfServiceImpl implements StudentSelfService {

    private int StudentId;

    @Autowired
    private StudentDao studentDao;


    @Autowired
    private CourseDao courseDao;

    @Override
    @Transactional
    public StudentEntity getBasicInfo() {
        return studentDao.getStudentEntity(StudentId);

//         null;
    }

    public int getStudentId() {
        return StudentId;
    }

    @Override
    @Transactional
    public void subscribeCourse(CourseEntity courseEntity) {
        studentDao.subscribeCourse(getBasicInfo(),courseEntity);
    }

    @Override
    @Transactional
    public List<TextMessageEntity> getAllTextMessageById() {
        return null;
    }

    @Override
    @Transactional
    public CourseEntity cancelCourse(StudentEntity studentEntity, Integer courseId) {
        studentDao.cancleCourse(getBasicInfo(),courseDao.getCourseEntity(courseId));
        return courseDao.getCourseEntity(courseId);
    }

    @Override
    public CourseEntity getCourseEntity(Integer courseId) {
        return courseDao.getCourseEntity(courseId);
    }


    public void setStudentId(int studentId) {
        StudentId = studentId;
    }


}
