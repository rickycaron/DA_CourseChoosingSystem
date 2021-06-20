package com.a20da10.service.ejb;

import com.a20da10.Entity.ejb.EJBInstructorEntity;

import javax.ejb.Local;

@Local
public interface AccountServiceLocal{
        Boolean InstructorAuthentication(String email,String password);
        Integer getInstructorIdByEmail(String email);
        EJBInstructorEntity register(EJBInstructorEntity newInstructor);
}
