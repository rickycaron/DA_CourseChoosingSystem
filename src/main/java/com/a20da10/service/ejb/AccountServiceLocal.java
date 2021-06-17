package com.a20da10.service.ejb;

import com.a20da10.Entity.ejb.EJBInstructorEntity;

import javax.ejb.Local;

@Local
public interface AccountServiceLocal{
        public Boolean InstructorAuthentication(String email,String password);
        public Integer getInstructorIdByEmail(String email);
        public EJBInstructorEntity register(EJBInstructorEntity newInstructor);
}
