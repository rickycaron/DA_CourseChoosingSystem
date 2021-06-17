package com.a20da10.service.spring;

import com.a20da10.Entity.spring.StudentEntity;

public interface LoginOutAndRegisterService {
    public Boolean StudentAuthentication(String email,String password);
    public Integer getStudentIdByEmail(String email);
    public StudentEntity register(StudentEntity newStudent);
}
