package com.a20da10.service.spring;

import com.a20da10.dao.spring.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class GetStudentGrade {

    @Autowired
    private StudentDao studentDao;

    public int getaveragegrade(){
        studentDao.getCustomers();
        return 15;
    }
}
