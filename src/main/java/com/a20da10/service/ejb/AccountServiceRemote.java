package com.a20da10.service.ejb;

import com.a20da10.Entity.ejb.EJBInstructorEntity;

import javax.ejb.Remote;

@Remote
public interface AccountServiceRemote {
    public Boolean InstructorAuthentication(String email,String password);
    public Integer getInstructorIdByEmail(String email);
    public EJBInstructorEntity register(EJBInstructorEntity newInstructor);
}
