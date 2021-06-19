package com.a20da10.service.ejb;

import com.a20da10.Entity.ejb.EJBInstructorEntity;
import com.a20da10.dao.ejb.InstructorDao;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless(name = "AccountServiceImpl")
public class AccountServiceImpl implements AccountServiceLocal, AccountServiceRemote{

    @Inject
    private InstructorDao instructorDao;

    @Override
    public Boolean InstructorAuthentication(String email, String password) {
        EJBInstructorEntity instructorEntity = instructorDao.getByEmail(email);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode(password));
        return passwordEncoder.matches(password, instructorEntity.getPassword());
    }

    @Override
    public Integer getInstructorIdByEmail(String email) {
        EJBInstructorEntity instructorEntity = instructorDao.getByEmail(email);
        return instructorEntity.getInstructorId();
    }

    @Override
    public EJBInstructorEntity register(EJBInstructorEntity newInstructor) {
        instructorDao.create(newInstructor);
        return newInstructor;
    }
}
