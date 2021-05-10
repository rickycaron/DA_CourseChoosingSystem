package com.a20da10.service.spring;

import com.a20da10.dao.spring.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;


@Service
public class GetStudentGrade {

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private WebApplicationContext springIOC;

    public int getaveragegrade(){
        studentDao.getAllStudents();
        System.out.println(springIOC);
        return 15;
    }
}
