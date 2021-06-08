package com.a20da10.service.spring;

import com.a20da10.Entity.spring.StudentEntity;
import com.a20da10.dao.spring.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class StudentSelfServiceImpl implements StudentSelfService {

    private int StudentId;

    @Autowired
    private StudentDao studentDao;
    @Override
    @Transactional
    public StudentEntity getBasicInfo() {
        return studentDao.getStudentEntity(StudentId);

//         null;
    }

    public int getStudentId() {
        return StudentId;
    }

    public void setStudentId(int studentId) {
        StudentId = studentId;
    }


}
