package com.a20da10.SOAP;

import com.a20da10.Entity.ejb.EJBInstructorEntity;
import com.a20da10.dao.ejb.InstructorDaoImpl;
import org.springframework.stereotype.Component;

import javax.jws.WebService;
import java.util.List;

@WebService
@Component
public class Soap { //This class is used to generate wsdl files and SOAP java files

    private InstructorDaoImpl instructorDaoImpl = new InstructorDaoImpl();

    public List<EJBInstructorEntity> getByName(String firsName, String lastName) {
        instructorDaoImpl.initEntityManager();
        return instructorDaoImpl.getByName(firsName, lastName);
    }

    public EJBInstructorEntity getByEmail(String email) {
        instructorDaoImpl.initEntityManager();
        return instructorDaoImpl.getByEmail(email);
    }

    public List<EJBInstructorEntity> getAll() {
        instructorDaoImpl.initEntityManager();
        return instructorDaoImpl.getAll();
    }

}
